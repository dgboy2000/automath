package automath.type.visitor;

import automath.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Needs to visit an expression as well as all assumptions and bindings.
 * Order of visiting: nodes, bindings, assumptions.
 * Preorder numbering
 */
public class ExpressionEqualityVisitor extends BaseTypeVisitor {
    // Order of visiting the other expression
    protected final Map<String, Integer> idToOtherVisitOrder = new HashMap<String, Integer>();
    public int getOtherVisitIndexOf(BaseType baseType) {
        return idToOtherVisitOrder.get(baseType.getId());
    }
    private Integer nextOtherNodeCount() {
        return idToOtherVisitOrder.size() + 1;
    }

    protected boolean isFirstTimeSeeingOther(Type type) {
        if (!(type instanceof BaseType)) return true;
        BaseType baseType = (BaseType) type;

        if (idToOtherVisitOrder.containsKey(baseType.getId())) return false;
        idToOtherVisitOrder.put(baseType.getId(), nextOtherNodeCount());

        return true;
    }

    public interface TypeProcessor {
        /*
         * Processes the visited nodes and returns true if equal, false otherwise
         */
        public boolean process(Type typeA, Type typeB);
    }

    protected final TypeProcessor processor;

    public ExpressionEqualityVisitor(TypeProcessor processor) {
        this.processor = processor;
    }

    public boolean visitFromPredicate(Predicate predicate, Type otherType) {
        if (!visitFromExpression(predicate, otherType)) return false;
        return visitAssumptions(predicate, (Predicate) otherType);
    }

    public boolean visitFromExpression(Expression expression, Type otherType) {
        if (otherType == null || expression.getClass() != otherType.getClass()) return false;
        Expression otherExpression = (Expression) otherType;

        if (!processor.process(expression, otherExpression)) return false;

        int numChildren = expression.getChildren().size();
        if (numChildren != otherExpression.getChildren().size()) return false;

        for (int childInd=0; childInd < numChildren; ++childInd) {
            if (!visit(expression.getChild(childInd), otherExpression.getChild(childInd))) return false;
        }

        return true;
    }

    /*
     * Returns true if the expressions are "equal in practice",
     * which is equality up to renaming unbound variables and cloning objects
     */
    public boolean visit(Type type, Type otherType) {
        if (type instanceof Operator) return type.equals(otherType);

        boolean firstTimeThis = isFirstTimeSeeing(type);
        boolean firstTimeOther = isFirstTimeSeeingOther(otherType);

        // This is type-safe because operators are always considered "first time seeing"
        if (!firstTimeThis && !firstTimeOther) return getVisitIndexOf((BaseType) type) == getOtherVisitIndexOf((BaseType) otherType);
        if (!firstTimeThis || !firstTimeOther) return false;

        if (type instanceof PredicateVariable) return visitFromPredicateVariable((PredicateVariable) type, otherType);
        if (type instanceof Variable) return visitFromVariable((Variable) type, otherType);
        if (type instanceof Predicate) return visitFromPredicate((Predicate) type, otherType);
        if (type instanceof Expression) return visitFromExpression((Expression) type, otherType);
        return processor.process(type, otherType);
    }

    private boolean visitFromPredicateVariable(PredicateVariable predicateVariable, Type otherType) {
        if (!visitFromVariable(predicateVariable, otherType)) return false;
        return visitAssumptions(predicateVariable, (PredicateVariable) otherType);
    }

    private boolean visitFromVariable(Variable variable, Type otherType) {
        if (!processor.process(variable, otherType)) return false;
        if (!variable.isBound()) return !((Variable) otherType).isBound();
        return visit(variable.getBinding(), ((Variable) otherType).getBinding());
    }

    private boolean visitAssumptions(Predicate predicate, Predicate otherPredicate) {
        int numAssumptions = predicate.getAssumptions().size();
        if (numAssumptions != otherPredicate.getAssumptions().size()) return false;

        for (int i=0; i<numAssumptions; ++i) {
            if (!visit(predicate.getAssumption(i), otherPredicate.getAssumption(i))) {
                return false;
            }
        }
        return true;
    }
}
