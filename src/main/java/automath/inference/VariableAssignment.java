package automath.inference;

import automath.type.*;
import automath.type.visitor.ExpressionAlignmentVisitor;
import automath.type.visitor.processor.ExpressionComparisonProcessor;
import automath.type.visitor.processor.VariableAssignmentBindingProcessor;
import automath.type.visitor.processor.VariableBindingCheckerProcessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a mapping from variables to targets, for example in an expression.
 */
public class VariableAssignment extends HashMap<Variable, Type> implements ExpressionAlignmentVisitor.TypeProcessor {
    public VariableAssignment() { super(); }
    public VariableAssignment(VariableAssignment variableAssignment) {
        super(variableAssignment);
    }

    @Override
    public Type put(Variable variable, Type type) {
        if (type instanceof Predicate) {
            type = ((Predicate) type).clone();
            ((Predicate) type).setLabel(null);
        }
        return super.put(variable, type);
    }

    public <T extends Expression> T applyTo(T expression) {
        // Top-level variables must be handled separately from the visitor, which only looks at children
        if ((expression instanceof PredicateVariable) && expression.getChildren().size() == 0) {
            PredicateVariable predicateVariable = (PredicateVariable) expression;
            if (!this.containsKey(predicateVariable)) return (T) predicateVariable.clone();
            Expression result = (Expression) this.get(predicateVariable);
            if (!predicateVariable.isAssignableFrom(result)) throw new RuntimeException();
            return (T) result;
        }

        T result = (T) expression.clone();

        new ExpressionAlignmentVisitor(this).visit(expression, result);
        return result;
    }

    public Type applyTo(Variable variable) {
        Variable result = (Variable) variable.clone();

        new ExpressionAlignmentVisitor(this).visit(variable, result);
        return result;
    }

    public Type applyTo(Type type) {
        if (type instanceof Expression) return (Expression) applyTo((Expression) type);
        if (type instanceof Variable) return applyTo((Variable) type);
        throw new IllegalArgumentException("Can only apply variable assignments to Variables or Expressions");
    }

    public boolean process(Type sourceType, Type destType) {
        // Can't modify args, so work on parent expressions to see if any children are variables
        // Assumes destType is a clone of sourceType;
        if (!(sourceType instanceof Expression)) return true;
        Expression sourceExpr = (Expression) sourceType;
        Expression destExpr = (Expression) destType;

        int numChildren = sourceExpr.getChildren().size();
        for (int childInd = 0; childInd < numChildren; ++childInd) {
            Type child = sourceExpr.getChild(childInd);
            if ((child instanceof Variable) && this.containsKey(child)) {
                destExpr.setChild(childInd, this.get(child));
            }
        }

        return true;
    }

    public VariableAssignment intersect(VariableAssignment variableAssignment) {
        VariableAssignment intersection = (VariableAssignment) this.clone();

        for (Map.Entry<Variable, Type> variableTypeEntry: variableAssignment.entrySet()) {
            Variable variable = variableTypeEntry.getKey();
            Type otherTarget = variableTypeEntry.getValue();

            if (!this.containsKey(variable)) {
                intersection.put(variable, otherTarget);
                continue; // Still need to handle conflicts between targets
            }

            /*
            Is it a bug to assign one unbound variable name to another here?
            There may be other targets in the assignment that depend on the variable name being replaced
            But each variable being assigned is independent. The name of an unbound variable doesn't
            matter as long as it is renamed everywhere it's supposed to be the same thing.
            If you're bound and assignable from otherTarget, that means you're both bound to the same
            thing and fine to replace.
            If you're unbound, that means otherTarget is of the right type to assign, in which case it's
            fine to replace the binding with something else.
             */
            Type thisTarget = this.get(variable);
            if ((thisTarget instanceof Variable) && thisTarget.isAssignableFrom(otherTarget)) {
                intersection.put(variable, otherTarget);
            } else if ((otherTarget instanceof Variable) && otherTarget.isAssignableFrom(thisTarget)) {
                intersection.put(variable, thisTarget);
            } else if ((thisTarget instanceof Expression) && (otherTarget instanceof Expression) &&
                    ExpressionComparisonProcessor.equal((Expression) thisTarget, (Expression) otherTarget)) {
                continue;
            } else if (!(thisTarget instanceof Expression) && !(otherTarget instanceof Expression) &&
                    thisTarget.equals(otherTarget)) {
                continue;
            } else {
                return null;
            }
        }

        // handle conflicts between naming of targets
        VariableAssignment rawIntersection = (VariableAssignment) intersection.clone();
        Map<String, Predicate> allVarNamesToBindings = new HashMap<String, Predicate>();
        for (Map.Entry<Variable, Type> variableTypeEntry: rawIntersection.entrySet()) {
            /*
            For every target:
                Get the variables & bindings that appear in the target
                For each variable/binding:
                    If first time seeing this variable name, save and continue
                    Else if variable is unbound:
                        If first sighting unbound, continue
                        Else rename this variable to open name
                    Else if first sighting is unbound, change previous sightings to new name
                    Else if first sighting is bound to same thing, continue
                    Else return null since 2 conflicting bindings
             */

            // TODO: target probably should be allowed to be Expression too
            Variable variable = variableTypeEntry.getKey();
            Type target = variableTypeEntry.getValue();
            Map<String, Predicate> curVarNameToBinding = new HashMap<String, Predicate>();
            if (target instanceof Variable) curVarNameToBinding.put(target.getName(), ((Variable) target).getBinding());
            else if (target instanceof Predicate) curVarNameToBinding.putAll(VariableBindingCheckerProcessor.getBindingsIn((Predicate) target));
            for (Map.Entry<String, Predicate> nameAndBinding : curVarNameToBinding.entrySet()) {
                String varName = nameAndBinding.getKey();
                Predicate binding = nameAndBinding.getValue();
                if (!allVarNamesToBindings.containsKey(varName)) allVarNamesToBindings.put(varName, binding);
                else if (binding == null) {
                    if (allVarNamesToBindings.get(varName) == null) continue;
                    else {
                        // TODO: rename this variable to open name
                        return null;
//                        String newVarName = getNewVarName();
//                        Type newTarget = renameVarToOpenName(varName, newVarName, target);
//                        intersection.put(variable, newTarget);
                    }
                } else if (allVarNamesToBindings.get(varName) == null) {
                    // TODO: change previous sightings to new name
                    return null;
                } else if (ExpressionComparisonProcessor.equal(binding, allVarNamesToBindings.get(varName))) continue;
                else return null;
            }
        }
        return intersection;
    }

    /**
     * Modifies the variable assignment to be consistent if possible, and returns true on success/false otherwise
     * @return
     */
    public boolean makeConsistent() {
        VariableAssignmentBindingProcessor processor = new VariableAssignmentBindingProcessor();
        for (Map.Entry<Variable, Type> assignment : this.entrySet()) {
            if (!processor.processBinding(assignment.getValue())) return false;
        }
        processor.getVariableNameToBinding();
        return true;
    }
}
