package automath.type.visitor.processor;

import automath.type.*;
import automath.type.visitor.ExpressionAlignmentVisitor;
import automath.inference.VariableAssignment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Processor to see if one expression can become
 * otherExpression with a variable assignment
 */
public class VariableAssignmentProcessor implements ExpressionAlignmentVisitor.TypeProcessor {
    private final VariableAssignment variableAssignment = new VariableAssignment();
    public VariableAssignment getVariableAssignment() { return variableAssignment; }

    @Override
    public boolean process(Type type, Type otherType) {
        if (type instanceof Variable) return processVariable((Variable) type, otherType);
        if (type instanceof Expression) return processExpression((Expression) type, otherType);
        return type.isAssignableFrom(otherType);
    }

    public boolean processVariable(Variable variable, Type otherType) {
        if (variableAssignment.containsKey(variable)) {
            if (variableAssignment.get(variable).isAssignableFrom(otherType) &&
                    variable.getName().equals(otherType.getName())) {
                // TODO: fix this check for when two variable assignments are compatible
                variableAssignment.put(variable, otherType);
                return true;
            }
            else if (!otherType.isAssignableFrom(variableAssignment.get(variable))) return false;
            return false;
        }

        // TODO: fix this check for if a bound variable is being aligned with what it's assigned to
        if (variable.isBound()) {
            if (!(otherType instanceof Variable)) return false;
            Variable otherVariable = (Variable) otherType;

            return variable.getName().equals(otherVariable.getName()) &&
                    otherVariable.getBinding() == variable.getBinding();
        }
        if (!variable.isTypeAssignableFrom(otherType)) return false;

        variableAssignment.put(variable, otherType);

        return true;
    }

    public boolean processExpression(Expression expression, Type otherType) {
        return otherType instanceof Expression;
    }

    public static VariableAssignment intersect(VariableAssignment va1, VariableAssignment va2) {
      /*
Clone one of the VA’s
Need to both make sure the variables in the targets are consistent, and to resolve conflicts between Variables
Assume a variable name may only appear with one meaning in an expression, and that both VA’s being merged satisfy this
For each variable name that appears in a target on both sides
If at least one VA has that var unbound, assign a new name to an unbound var for all targets on that side.
If both bound, make sure the bindings are to the same predicate. For now check this by label. In the future, maybe check with a Processor. If bindings differ, fail.
Add each of the other VA’s pairs that have different variable names. (Do I need to check that variables hash based on name only, not binding?)
For each conflicting pair in the other VA:
Try to generate a variable assignment that maps one to the other.
If unsuccessful, fail/return null
Apply the variable assignment to every target of the variable assignment that can be assigned from the other.
       */

        VariableAssignment va1copy = va1;
        VariableAssignment va2copy = va2;

        Map<String, Variable> targetVars1 = VariableFindingProcessor.getTargetVariablesIn(va1);
        Map<String, Variable> targetVars2 = VariableFindingProcessor.getTargetVariablesIn(va2);

        Set<String> allTargetVarNames = new HashSet<String>();
        allTargetVarNames.addAll(targetVars1.keySet());
        allTargetVarNames.addAll(targetVars2.keySet());

        Set<String> commonVarNames = new HashSet<String>();
        commonVarNames.addAll(targetVars1.keySet());
        commonVarNames.retainAll(targetVars2.keySet());

        for (String commonVarName : commonVarNames) {
//            For each variable name that appears in a target on both sides
            Variable var1 = targetVars1.get(commonVarName);
            Variable var2 = targetVars2.get(commonVarName);

            // Create a variable assignment that renames this variable to an unused name
            Variable renamedVar = getVariableWithNewName(commonVarName, allTargetVarNames);
            String newName = renamedVar.getName();
            allTargetVarNames.add(newName);
            VariableAssignment renaming = new VariableAssignment();

            if (!var1.isBound()) {
//            If at least one VA has that var unbound, assign a new name to an unbound var for all targets on that side.
                renaming.put(targetVars1.get(commonVarName), renamedVar);
                va1copy = va1copy.composedWith(renaming);
            } else if (!var2.isBound()) {
                renaming.put(targetVars2.get(commonVarName), renamedVar);
                va2copy = va2copy.composedWith(renaming);
            } else {
//                If both bound, make sure the bindings are to the same predicate. For now check this by label. In the future, maybe check with a Processor. If bindings differ, fail.
                String bindingLabel1 = var1.getBinding().getLabel();
                String bindingLabel2 = var2.getBinding().getLabel();
                if (!bindingLabel1.equalsIgnoreCase(bindingLabel2)) return null;
            }

        }


//        Add each of the other VA’s pairs that have different variable names. (Do I need to check that variables hash based on name only, not binding?)
        Map<String, Variable> sourceVars1 = new HashMap<String, Variable>();
        Map<String, Variable> sourceVars2 = new HashMap<String, Variable>();
        for (Variable var : va1.keySet()) sourceVars1.put(var.getName(), var);
        for (Variable var : va2.keySet()) sourceVars2.put(var.getName(), var);

        VariableAssignment intersection = (VariableAssignment) va1copy.clone();

        Set<String> newNames2 = new HashSet<String>();
        newNames2.addAll(sourceVars2.keySet());
        newNames2.removeAll(sourceVars1.keySet());
        for (String newVar2 : newNames2) {
            Variable var2 = sourceVars2.get(newVar2);
            intersection.put(var2, va2copy.get(var2));
        }

        commonVarNames.clear();
        commonVarNames.addAll(sourceVars1.keySet());
        commonVarNames.retainAll(sourceVars2.keySet());
        for (String commonVarName : commonVarNames) {
//            For each conflicting pair in the other VA:
//            Try to generate a variable assignment that maps one to the other.
//            If unsuccessful, fail/return null
            Variable var1 = sourceVars1.get(commonVarName);
            Variable var2 = sourceVars2.get(commonVarName);

            Type target1 = va1copy.get(var1);
            Type target2 = va2copy.get(var2);

            // TODO: verify that we should include assumptions in this equality
            if ((target1 instanceof Expression) && (target2 instanceof Expression) &&
                    new ExpressionComparisonProcessor(true).equal((Expression) target1, (Expression) target2)) continue;
            // TODO: verify that checking name of number variables is enough, don't need to compare bindings if both bound
            if (!(target1 instanceof Expression) && !(target2 instanceof Expression) &&
                    target1.equals(target2)) continue;

            VariableAssignment change1to2 = getTransformingVariableAssignment(target1, target2);
            if (change1to2 != null && change1to2.size() > 0) {
                //            Apply the variable assignment to every target of the variable assignment that can be assigned from the other.
                intersection = intersection.composedWith(change1to2);
                continue;
            }

            VariableAssignment change2to1 = getTransformingVariableAssignment(target2, target1);
            if (change2to1 != null && change2to1.size() > 0) {
                //            Apply the variable assignment to every target of the variable assignment that can be assigned from the other.
                intersection = intersection.composedWith(change2to1);
                continue;
            }

            return null;
        }

        return intersection;
    }

    private static Variable getVariableWithNewName(String oldName, Set<String> allCurrentNames) {
        Character firstChar = oldName.charAt(0);

        int chOffset;
        String newName = null;
        for (chOffset = 0; chOffset < 26; ++chOffset) {
            newName = String.valueOf((char) (firstChar+chOffset));
            if (!allCurrentNames.contains(newName)) break;
        }

        if (chOffset == 26) throw new RuntimeException("Couldn't find a new name; all characters are used");

        if (Character.isUpperCase(firstChar)) return new PredicateVariable(newName);
        else return new NumberVariable(newName);
    }

    private static VariableAssignment getTransformingVariableAssignment(Type sourceType, Type targetType) {
        VariableAssignmentProcessor processor = new VariableAssignmentProcessor();
        if (!new ExpressionAlignmentVisitor(processor).visit(sourceType, targetType)) return null;
        return processor.getVariableAssignment();
    }

    public static VariableAssignment compose(VariableAssignment variableAssignment, VariableAssignment otherAssignment) {
        VariableAssignment composition = new VariableAssignment();

        for (Map.Entry<Variable, Type> varTargetPair : variableAssignment.entrySet()) {
            composition.put(varTargetPair.getKey(), otherAssignment.applyTo(varTargetPair.getValue()));
        }

        return composition;
    }
}
