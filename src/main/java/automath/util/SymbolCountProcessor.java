package automath.util;

import automath.type.Expression;
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
        SymbolCountProcessor processor = new SymbolCountProcessor();
        new ExpressionVisitor(processor).visit(expression);
        return processor.getSymbolCount();
    }
}
