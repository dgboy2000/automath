package automath.util;

import automath.type.Operator;
import automath.type.Predicate;
import automath.type.Type;

/**
 * Simplify expressions in place
 */
public class ExpressionSimplificationProcessor implements ExpressionVisitor.Processor {
    public static void process(Predicate predicate) {
        new ExpressionVisitor(new ExpressionSimplificationProcessor()).visit(predicate);
    }

    @Override
    public boolean process(Type type) {
        if (type instanceof Predicate) return processPredicate((Predicate) type);
        return true;
    }

    private boolean processPredicate(Predicate predicate) {
        if (predicate.getLabel() != null && predicate.getLabel().length() > 0) {
            throw new IllegalArgumentException("Won't operate on predicate already in corpus");
        }

        processDoubleNegation(predicate);
        return true;
    }

    /**
     * TODO: Fix this hack that processes triple negatives into single negatives
     * Can't do ordinary double negatives because could be trying to make a predicate
     * become a pred var which doesn't work. Need nodes to know their parents to fix.
     * @param predicate
     */
    private void processDoubleNegation(Predicate predicate) {
        if (predicate.getChildren().size() > 0 && predicate.getChild(0) == Operator.NOT) {
            Predicate childPred = (Predicate) predicate.getChild(1);
            if (childPred.getChildren().size() > 0 && childPred.getChild(0) == Operator.NOT) {
                Predicate grandchildPred = (Predicate) predicate.getChild(1);
                if (grandchildPred.getChildren().size() > 0 && grandchildPred.getChild(0) == Operator.NOT) {
                    predicate.becomeCloneOf(grandchildPred);
                }
            }
        }
    }
}
