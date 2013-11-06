package automath.util;

import automath.type.Expression;
import automath.type.Predicate;
import automath.type.Type;
import automath.type.Variable;

/**
 * Count the number of terminal symbols in an expression
 */
public class SymbolCountProcessor implements ExpressionVisitor.Processor {
    private int symbolCount = 0;
    public int getSymbolCount() { return symbolCount; }

    @Override
    public boolean process(Type type) {
        if ((type instanceof Variable) || !(type instanceof Expression))
            ++symbolCount;
        return true;
    }

    public static int countSymbolsIn(Expression expression) {
        int symbolCount = 0;
        SymbolCountProcessor processor = new SymbolCountProcessor();
        new ExpressionVisitor(processor).visit(expression);
        symbolCount += processor.getSymbolCount();

        if (expression instanceof Predicate) {
            Predicate predicate = (Predicate) expression;
            int assumptionCount = predicate.getAssumptions().size();
            for (Predicate assumption : predicate.getAssumptions()) {
                if (assumption.equals(predicate)) assumptionCount += symbolCount; // Avoid infinite recursion
                else assumptionCount += countSymbolsIn(assumption);
            }
            symbolCount += assumptionCount;
        }

        return symbolCount;
    }
}
