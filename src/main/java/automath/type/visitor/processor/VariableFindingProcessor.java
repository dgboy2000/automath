package automath.type.visitor.processor;

import automath.inference.VariableAssignment;
import automath.type.Type;
import automath.type.Variable;
import automath.type.visitor.ExpressionVisitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Finds variables.
 */
public class VariableFindingProcessor implements ExpressionVisitor.Processor {
    private final Map<String, Variable> nameToVariable = new HashMap<String, Variable>();
    public Map<String, Variable> getNameToVariable() { return nameToVariable; }

    @Override
    public boolean process(Type type) {
        if (type instanceof Variable) processVariable((Variable) type);
        return true;
    }

    private void processVariable(Variable variable) {
        nameToVariable.put(variable.getName(), variable);
    }

    public static Map<String, Variable> getTargetVariablesIn(VariableAssignment variableAssignment) {
        VariableFindingProcessor processor = new VariableFindingProcessor();
        ExpressionVisitor visitor = new ExpressionVisitor(processor);

        for (Type target : variableAssignment.values()) {
            visitor.visit(target);
        }

        return processor.getNameToVariable();
    }
}
