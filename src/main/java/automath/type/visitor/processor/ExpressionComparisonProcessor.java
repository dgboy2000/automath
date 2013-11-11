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
    public final boolean includeAssumptions_;

    public ExpressionComparisonProcessor(boolean includeAssumptions) {
        this.includeAssumptions_ = includeAssumptions;
    }

    public boolean equal(Expression expA, Expression expB) {
        return compare(expA, expB) == 0;
    }

    public int compare(Expression expA, Expression expB) {
        return new ExpressionComparisonVisitor(this).visit(expA, expB);
    }

    @Override
    public boolean includeAssumptions() {
        return includeAssumptions_;
    }

    @Override
    public int process(Type type, Type otherType) {
        if (type instanceof Operator) return processOperator((Operator) type, otherType);
        if (type instanceof Predicate) return processPredicate((Predicate) type, otherType);
        return ((BaseType) type).compareTo(otherType);
    }

    private int processPredicate(Predicate predicate, Type otherType) {
        int comparison = predicate.compareTo(otherType);
        if (comparison != 0) return comparison;

        if (includeAssumptions()) {
            Predicate otherPredicate = (Predicate) otherType; // BaseType comparison is unequal if different classes
            return predicate.getAssumptions().size() - otherPredicate.getAssumptions().size();
        }
        return 0;
    }

    private int processOperator(Operator operator, Type otherType) {
        if (otherType instanceof Operator) return operator.compareTo((Operator) otherType);
        return operator.getClass().getName().compareTo(otherType.getClass().getName());
    }
}
