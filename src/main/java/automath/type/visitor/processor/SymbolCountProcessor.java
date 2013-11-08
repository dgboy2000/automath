package automath.type.visitor.processor;

import automath.type.Expression;
import automath.type.Predicate;
import automath.type.Type;
import automath.type.Variable;
import automath.type.visitor.ExpressionVisitor;
import automath.util.Mappable;

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

        // TODO: move this logic to a new visitor, ExpressionAndAssumptionVisitor
        if (expression instanceof Predicate) {
            Predicate predicate = (Predicate) expression;
            int assumptionCount = predicate.getAssumptions().size();
            for (Mappable<Predicate> assumption : predicate.getAssumptions()) {
                if (ExpressionEqualityProcessor.equal(assumption.getRawObject(), predicate)) assumptionCount += symbolCount; // Avoid infinite recursion
                else assumptionCount += countSymbolsIn(assumption.getRawObject());
            }
            symbolCount += assumptionCount;
        }

        return symbolCount;
    }
}
