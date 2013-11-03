package automath.util;

import automath.type.Expression;
import automath.type.Type;
import automath.type.Variable;

/**
 * Generate a cryptographically insecure hash of an expression to facilitate
 * lookup in a knowledge corpus.
 *
 * TODO: take into account variables that appear in multiple places with the same name
 */
public class ExpressionHashProcessor implements ExpressionVisitor.TypeProcessor {
    private int hashCode = 0;
    private void next(int hashCode) { this.hashCode = this.hashCode * this.hashCode * this.hashCode + hashCode; }
    private void next(boolean bool) { if (bool) next(100); else next(200); }

    @Override
    public int hashCode() { return hashCode; }

    public boolean process(Type type, Type otherType) {
        if (type instanceof Expression) return processExpression((Expression) type, otherType);
        if (type instanceof Variable) return processVariable((Variable) type, otherType);

        next(type.hashCode());
        return true;
    }

    public boolean processVariable(Variable variable, Type otherType) {
        next(variable.getType().hashCode());
        next(variable.isBound());
        return true;
    }

    public boolean processExpression(Expression expression, Type otherType) {
        next(expression.getChildren().size());
        return true;
    }

    public static int hash(Expression expression) {
        ExpressionHashProcessor hashProcessor = new ExpressionHashProcessor();

        // TODO: write a visitor that goes to a single tree without needing to visit 2 copies in parallel
        new ExpressionVisitor(hashProcessor).visit(expression, expression);
        return hashProcessor.hashCode();
    }
}
