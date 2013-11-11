package automath.type.visitor.processor;

import automath.inference.VariableAssignment;
import automath.type.Predicate;
import automath.type.PredicateVariable;
import automath.type.Type;
import automath.type.Variable;
import automath.type.visitor.ExpressionVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 11/8/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class VariableBindingCheckerProcessor implements ExpressionVisitor.Processor {
    private final Map<String, Predicate> varNameToBinding = new HashMap<String, Predicate>();
    public Map<String, Predicate> getVarNameToBinding() { return varNameToBinding; }

    /**
     * Return the variable assignment applied the consequent,
     * with any binding conflicts resolved by renaming where possible.
     * Returns null if impossible.
     * @return
     */
    public Predicate resolveAssignmentBindingConflicts(Predicate consequent, VariableAssignment variableAssignment) {
        Map<String, Predicate> consequentVariableBindings = new HashMap<String, Predicate>();
        for (Map.Entry<Variable, Type> curVarAssignment : variableAssignment.entrySet()) {
            Type curTarget = curVarAssignment.getValue();
            if (!(curTarget instanceof Predicate)) continue;
            Predicate curPredicate = (Predicate) curTarget;    // TODO: need to handle number variables / expressions too

            Map<String, Predicate> curBindings = getBindingsIn(curPredicate);//
            for (Map.Entry<String, Predicate> nameAndBinding : curBindings.entrySet()) {
                String name = nameAndBinding.getKey();

                if (!consequentVariableBindings.containsKey(name)) {
                    consequentVariableBindings.put(name, nameAndBinding.getValue());
                    continue;
                }

                Predicate oldBinding = consequentVariableBindings.get(name);
                Predicate newBinding = nameAndBinding.getValue();
                if (oldBinding == null) {
                    consequentVariableBindings.put(name, newBinding);
                    continue;
                }
                if (newBinding == null) continue;
                if (new ExpressionComparisonProcessor(true).equal(oldBinding, newBinding)) continue;

//                renameVariableInPredicate(name, curPredicate);
            }
        }

        return null;
    }

    /**
     * Return a map from variable name to binding, or null if unbound
     * @param predicate
     * @return
     */
    public static Map<String, Predicate> getBindingsIn(Predicate predicate) {
        VariableBindingCheckerProcessor processor = new VariableBindingCheckerProcessor();
        new ExpressionVisitor(processor).visit(predicate);
        return processor.getVarNameToBinding();
    }


    @Override
    public boolean process(Type type) {
        if (!(type instanceof Variable)) return true;
        Variable variable = (Variable) type;
        String name = type.getName();
        Predicate binding = variable.getBinding();

        if (!varNameToBinding.containsKey(name)) {
            varNameToBinding.put(name, binding);
            return true;
        }
        if (!variable.isBound()) return true;

        Predicate prevBinding = varNameToBinding.get(name);
        if (prevBinding == null) {
            varNameToBinding.put(name, binding);
            return true;
        }

        return new ExpressionComparisonProcessor(true).equal(binding, prevBinding);
    }
}
