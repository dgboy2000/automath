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
public class ExpressionHashProcessor implements ExpressionVisitor.Processor {
    private int hashCode = 0;
    private void next(int hashCode) { this.hashCode = this.hashCode * this.hashCode * this.hashCode + hashCode; }
    private void next(boolean bool) { if (bool) next(100); else next(200); }

    @Override
    public int hashCode() { return hashCode; }

    public boolean process(Type type) {
        if (type instanceof Expression) return processExpression((Expression) type);
        if (type instanceof Variable) return processVariable((Variable) type);

        next(type.hashCode());
        return true;
    }

    public boolean processVariable(Variable variable) {
        next(variable.getType().hashCode());
        next(variable.isBound());
        return true;
    }

    public boolean processExpression(Expression expression) {
        next(expression.getChildren().size());
        return true;
    }

    public static int hash(Expression expression) {
        ExpressionHashProcessor hashProcessor = new ExpressionHashProcessor();
        new ExpressionVisitor(hashProcessor).visit(expression);
        return hashProcessor.hashCode();
    }
}
