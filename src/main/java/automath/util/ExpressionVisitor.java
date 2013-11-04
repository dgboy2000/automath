package automath.util;

import automath.type.Expression;
import automath.type.Type;

/**
 * Visit and process every node in an expression.
 */
public class ExpressionVisitor {
    public interface Processor {
        /**
         * Processes the visited nodes and returns true to continue, false to terminate early
         */
        public boolean process(Type type);
    }

    protected final Processor processor;

    public ExpressionVisitor(Processor processor) {
        this.processor = processor;
    }

    public boolean visitExpression(Expression expression) {
        for (Type child : expression.getChildren()) {
            if (!visit(child)) return false;
        }

        return processor.process(expression);
    }

    public boolean visit(Type type) {
        return type instanceof Expression ?
                visitExpression((Expression) type) :
                processor.process(type);
    }
}
