package automath.type.visitor.processor;

import automath.type.Predicate;
import automath.type.Type;
import automath.type.Variable;
import automath.type.visitor.ExpressionVisitor;

import java.util.HashSet;
import java.util.Set;

/**
 * Bind and unbind variables in predicates
 */
public class VariableBindingProcessor implements ExpressionVisitor.Processor {
    private Predicate predicateToBind;
    private final Set<Predicate> predicateToUnbindFrom = new HashSet<Predicate>();

    /**
     * Create a visitor and run the processor to bind variables in the specified predicate
     */
    public static void bind(Predicate predicateToBind) {
        VariableBindingProcessor processor = new VariableBindingProcessor();
        processor.predicateToBind = predicateToBind;
        new ExpressionVisitor(processor).visit(predicateToBind);
    }

    public static VariableBindingProcessor unbindingProcessor(Predicate predicateToUnbind) {
        VariableBindingProcessor processor = new VariableBindingProcessor();
        processor.predicateToUnbindFrom.add(predicateToUnbind);
        return processor;
    }



    @Override
    public boolean process(Type type) {
        if (type instanceof Variable) {
            Variable variable = (Variable) type;
            if (predicateToUnbindFrom.contains(variable.getBinding())) {
                variable.bindTo(null);
            } else if (predicateToBind != null && !variable.isBound()) {
                variable.bindTo(predicateToBind);
            }
        }
        return true;
    }
}
