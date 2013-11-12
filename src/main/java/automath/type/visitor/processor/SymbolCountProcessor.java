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
    private final boolean includeAssumptions;

    private int symbolCount = 0;
    public int getSymbolCount() { return symbolCount; }
    public void resetSymbolCount() { this.symbolCount = 0; }

    public SymbolCountProcessor() { this.includeAssumptions = false; }
    public SymbolCountProcessor(boolean includeAssumptions) {
        this.includeAssumptions = includeAssumptions;
    }

    @Override
    public boolean process(Type type) {
        if ((type instanceof Variable) || !(type instanceof Expression))
            ++symbolCount;
        return true;
    }

    public static int countSymbolsIn_static(Expression expression) {
        return new SymbolCountProcessor().countSymbolsIn(expression);
    }

    public int countSymbolsIn(Expression expression) {
        resetSymbolCount();
        new ExpressionVisitor(this).visit(expression);

        int symbolCount = 0;
        symbolCount += this.getSymbolCount();

        // TODO: move this logic to a new visitor, ExpressionAndAssumptionVisitor
        if (this.includeAssumptions && (expression instanceof Predicate)) {
            Predicate predicate = (Predicate) expression;
            int assumptionCount = predicate.getAssumptions().size();
            for (Mappable<Predicate> assumption : predicate.getAssumptions()) {
                if (new ExpressionComparisonProcessor(true).equal(assumption.getRawObject(), predicate)) continue;//assumptionCount += symbolCount; // Avoid infinite recursion
                else assumptionCount += countSymbolsIn(assumption.getRawObject());
            }
            symbolCount += assumptionCount;
        }

        return symbolCount;
    }
}
