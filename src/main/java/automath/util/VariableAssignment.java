package automath.util;

import automath.type.Expression;
import automath.type.Predicate;
import automath.type.Type;
import automath.type.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a mapping from variables to targets, for example in an expression.
 */
public class VariableAssignment extends HashMap<Variable, Type> implements ExpressionAlignmentVisitor.TypeProcessor {
    @Override
    public Type put(Variable variable, Type type) {
        if (type instanceof Predicate) {
            type = ((Predicate) type).clone();
            ((Predicate) type).setLabel(null);
        }
        return super.put(variable, type);
    }

    public <T extends Expression> T applyTo(T expression) {
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
        throw new IllegalArgumentException("Can only apply variable assignments to Types or Expressions");
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
            Type thisTarget = this.get(variable);
            Type otherTarget = variableTypeEntry.getValue();
            if (!this.containsKey(variable)) {
                intersection.put(variable, variableTypeEntry.getValue());
            } else if (thisTarget.isAssignableFrom(otherTarget)) {
                intersection.put(variable, otherTarget);
            } else if (!otherTarget.isAssignableFrom(thisTarget)) {
                return null; // Variable assignments are not compatible
            }
        }

        return intersection;
    }
}
