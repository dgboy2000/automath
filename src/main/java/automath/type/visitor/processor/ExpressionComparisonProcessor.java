package automath.type.visitor.processor;

import automath.inference.VariableAssignment;
import automath.type.*;
import automath.type.visitor.ExpressionComparisonVisitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Processor to determine whether two expressions are "practically equal"
 * which means equal up to renaming unbound variables and cloning nodes.
 */
public class ExpressionComparisonProcessor implements ExpressionComparisonVisitor.TypeProcessor {
    public static boolean equal(Expression expA, Expression expB) {
        return compare(expA, expB) == 0;
    }

    public static int compare(Expression expA, Expression expB) {
        return new ExpressionComparisonVisitor(new ExpressionComparisonProcessor()).visit(expA, expB);
    }

    @Override
    public int process(Type type, Type otherType) {
        if (type instanceof Operator) {
            Operator operator = (Operator) type;
            if (otherType instanceof Operator) return operator.compareTo((Operator) otherType);
            return operator.getClass().getName().compareTo(otherType.getClass().getName());
        }
        return ((BaseType) type).compareTo(otherType);
    }
}
