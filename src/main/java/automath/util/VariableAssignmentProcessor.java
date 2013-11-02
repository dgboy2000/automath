package automath.util;

import automath.type.Expression;
import automath.type.Type;
import automath.type.Variable;

/*
 * Processor to see if one expression can become
 * otherExpression with a variable assignment
 */
public class VariableAssignmentProcessor implements ExpressionVisitor.TypeProcessor {
    private final VariableAssignment variableAssignment = new VariableAssignment();
    public VariableAssignment getVariableAssignment() { return variableAssignment; }

    public boolean process(Type type, Type otherType) {
        if (type instanceof Expression) return processExpression((Expression) type, otherType);
        if (type instanceof Variable) return processVariable((Variable) type, otherType);
        return type.isAssignableFrom(otherType);
    }

    public boolean processVariable(Variable variable, Type otherType) {
        // TODO: could match even if this variable is already mapped
        if (variableAssignment.containsKey(variable)) return false;

        // TODO: take into account bound v non-bound too
        if (!variable.isTypeAssignableFrom(otherType)) return false;

        variableAssignment.put(variable, otherType);

        return true;
    }

    public boolean processExpression(Expression expression, Type otherType) {
        return otherType instanceof Expression;
    }
}
