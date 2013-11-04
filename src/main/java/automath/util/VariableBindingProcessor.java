package automath.util;

import automath.type.Predicate;
import automath.type.Type;
import automath.type.Variable;

import java.util.HashSet;
import java.util.Set;

/**
 * Bind and unbind variables in predicates
 */
public class VariableBindingProcessor implements ExpressionVisitor.Processor {
    private Predicate predicateToBind;
    private final Set<Predicate> predicatesToUnbind = new HashSet<Predicate>();

    public static VariableBindingProcessor bindingProcessor(Predicate predicateToBind) {
        VariableBindingProcessor processor = new VariableBindingProcessor();
        processor.predicateToBind = predicateToBind;
        return processor;
    }

    public static VariableBindingProcessor unbindingProcessor(Predicate predicateToUnbind) {
        VariableBindingProcessor processor = new VariableBindingProcessor();
        processor.predicatesToUnbind.add(predicateToUnbind);
        return processor;
    }

    @Override
    public boolean process(Type type) {
        if (type instanceof Variable) {
            Variable variable = (Variable) type;
            if (predicatesToUnbind.contains(variable.getBinding())) {
                variable.bindTo(null);
            } else if (predicateToBind != null && !variable.isBound()) {
                variable.bindTo(predicateToBind);
            }
        }
        return true;
    }
}
