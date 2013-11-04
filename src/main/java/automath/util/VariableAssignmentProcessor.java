package automath.util;

import automath.type.Expression;
import automath.type.Type;
import automath.type.Variable;

/*
 * Processor to see if one expression can become
 * otherExpression with a variable assignment
 */
public class VariableAssignmentProcessor implements ExpressionAlignmentVisitor.TypeProcessor {
    private final VariableAssignment variableAssignment = new VariableAssignment();
    public VariableAssignment getVariableAssignment() { return variableAssignment; }

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

        // TODO: take into account bound v non-bound too
        if (!variable.isTypeAssignableFrom(otherType)) return false;

        variableAssignment.put(variable, otherType);

        return true;
    }

    public boolean processExpression(Expression expression, Type otherType) {
        return otherType instanceof Expression;
    }
}
