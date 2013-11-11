package automath.type.visitor.processor;

import automath.type.Expression;
import automath.type.Type;
import automath.type.Variable;
import automath.type.visitor.ExpressionAlignmentVisitor;
import automath.inference.VariableAssignment;

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
            return (otherType instanceof Variable) &&
                    ((Variable) otherType).getBinding() == variable.getBinding();
        }
        if (!variable.isTypeAssignableFrom(otherType)) return false;

        variableAssignment.put(variable, otherType);

        return true;
    }

    public boolean processExpression(Expression expression, Type otherType) {
        return otherType instanceof Expression;
    }
}
