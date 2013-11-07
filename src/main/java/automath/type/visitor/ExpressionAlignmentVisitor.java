package automath.type.visitor;

import automath.type.Expression;
import automath.type.Type;
import automath.type.Variable;

/**
 * Visit every node (type instance) in an expression in parallel with another expression.
 * Supports joint processing
 */
public class ExpressionAlignmentVisitor extends BaseTypeVisitor {
    public interface TypeProcessor {
        /*
         * Processes the visited nodes and returns true to continue, false to terminate early
         */
        public boolean process(Type typeA, Type typeB);
    }

    protected final TypeProcessor processor;

    public ExpressionAlignmentVisitor(TypeProcessor processor) {
        this.processor = processor;
    }

    /*
     * Returns true if a variable assignement can turn this.expression into otherExpression.
     * Computes the VariableAssignment along the way.
     */
    public boolean visitFromExpression(Expression expression, Type otherType) {
        if (!expression.getClass().isAssignableFrom(otherType.getClass())) return false;
        Expression otherExpression = (Expression) otherType;

        int numChildren = expression.getChildren().size();
        if (numChildren != otherExpression.getChildren().size()) return false;

        for (int childInd=0; childInd < numChildren; ++childInd) {
            if (!visit(expression.getChild(childInd), otherExpression.getChild(childInd))) return false;
        }

        return processor.process(expression, otherExpression);
    }

    public boolean visit(Type type, Type otherType) {
        if (!isFirstTimeSeeing(type)) return true;

        if (type instanceof Variable) return processor.process(type, otherType);
        return type instanceof Expression ?
                visitFromExpression((Expression) type, otherType) :
                processor.process(type, otherType);
    }
}