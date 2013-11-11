package automath.type.visitor.processor;

import automath.inference.VariableAssignment;
import automath.type.Expression;
import automath.type.Predicate;
import automath.type.Type;
import automath.type.Variable;
import automath.type.visitor.ExpressionVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Looks through the targets in a VariableAssignment, determines whether they are compatible (ie don't have
 * conflicting names) and then assigns a compatible binding if they are.
 */
public class VariableAssignmentBindingProcessor implements ExpressionVisitor.Processor {
    private final Map<String, Predicate> variableNameToBinding = new HashMap<String, Predicate>();
    public Map<String, Predicate> getVariableNameToBinding() { return variableNameToBinding; }

    public boolean processBinding(Type assignedType) {
        return new ExpressionVisitor(this).visit(assignedType);
    }

    /**
     * Returns true if this variable can be bound consistently with the current VariableAssignment
     * @param type
     * @return
     */
    @Override
    public boolean process(Type type) {
        if (!(type instanceof Variable)) return true;
        return processVariable((Variable) type);
    }

    public boolean processVariable(Variable variable) {
        if (!variable.isBound()) return true;
        if (!variableNameToBinding.containsKey(variable.getName())) {
            variableNameToBinding.put(variable.getName(), variable.getBinding());
            return true;
        }
        return ExpressionComparisonProcessor.equal(variable.getBinding(), variableNameToBinding.get(variable.getName()));
    }
}
