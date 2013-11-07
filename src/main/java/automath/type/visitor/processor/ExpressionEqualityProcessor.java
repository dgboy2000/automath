package automath.type.visitor.processor;

import automath.inference.VariableAssignment;
import automath.type.*;
import automath.type.visitor.ExpressionEqualityVisitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Processor to determine whether two expressions are "practically equal"
 * which means equal up to renaming unbound variables and cloning nodes.
 */
public class ExpressionEqualityProcessor implements ExpressionEqualityVisitor.TypeProcessor {
    // Need to track assignment of unbound variables to make sure they are consistent
    private final VariableAssignment thisToOther = new VariableAssignment();
    private final Set<Variable> seenOtherVariables = new HashSet<Variable>();

    public static boolean equal(Expression expA, Expression expB) {
        return new ExpressionEqualityVisitor(new ExpressionEqualityProcessor()).visit(expA, expB);
    }

    @Override
    public boolean process(Type type, Type otherType) {
        if (type == null || otherType == null) return false;
        if ((type instanceof Variable) && (otherType instanceof Variable)) {
            return processVariable((Variable) type, (Variable) otherType);
        }
        return type.equals(otherType);
    }

    public boolean processVariable(Variable variable, Variable otherVariable) {
        if (thisToOther.containsKey(variable)) {
            return thisToOther.get(variable).getName().equals(otherVariable.getName());
        } else if (seenOtherVariables.contains(otherVariable)) {
            return false;
        }

        thisToOther.put(variable, otherVariable);
        seenOtherVariables.add(otherVariable);

        return true;
    }
}
