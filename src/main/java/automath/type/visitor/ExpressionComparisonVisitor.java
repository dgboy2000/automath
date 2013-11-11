package automath.type.visitor;

import automath.inference.VariableAssignment;
import automath.type.*;
import automath.util.Mappable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Needs to visit an expression as well as all assumptions and bindings.
 * Order of visiting: nodes, bindings, assumptions.
 * Preorder numbering
 */
public class ExpressionComparisonVisitor extends BaseTypeVisitor {
    // Need to track assignment of unbound variables to make sure they are consistent
    private final Map<String, Integer> thisToOther = new HashMap<String, Integer>();
    private final Map<String, Integer> otherToThis = new HashMap<String, Integer>();

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
        public int process(Type typeA, Type typeB);
    }

    protected final TypeProcessor processor;

    public ExpressionComparisonVisitor(TypeProcessor processor) {
        this.processor = processor;
    }

    public int visitFromPredicate(Predicate predicate, Type otherType) {
        int comparison = visitFromExpression(predicate, otherType);
        if (comparison != 0) return comparison;
        return visitAssumptions(predicate, (Predicate) otherType);
    }

    public int visitFromExpression(Expression expression, Type otherType) {
        Expression otherExpression = (Expression) otherType;

        int numChildren = expression.getChildren().size();
        for (int childInd=0; childInd < numChildren; ++childInd) {
            int comparison = visit(expression.getChild(childInd), otherExpression.getChild(childInd));
            if (comparison != 0) return comparison;
        }

        return 0;
    }

    /*
     * Returns the integer result of comparing the two types.
     * <0: less than
     * 0: equal
     * >0: greater than
     */
    public int visit(Type type, Type otherType) {
        int comparison = processor.process(type, otherType);
        if (comparison != 0) return comparison;

        boolean firstTimeThis = isFirstTimeSeeing(type);
        boolean firstTimeOther = isFirstTimeSeeingOther(otherType);

        // This is type-safe because operators are always considered "first time seeing"
        if (!firstTimeThis && !firstTimeOther) return getVisitIndexOf((BaseType) type) - getOtherVisitIndexOf((BaseType) otherType);
        if (!firstTimeThis) return -1;
        if (!firstTimeOther) return 1;

        if (type instanceof PredicateVariable) return visitFromPredicateVariable((PredicateVariable) type, otherType);
        if (type instanceof Variable) return visitFromVariable((Variable) type, otherType);
        if (type instanceof Predicate) return visitFromPredicate((Predicate) type, otherType);
        if (type instanceof Expression) return visitFromExpression((Expression) type, otherType);
        return 0;
    }

    private int visitFromPredicateVariable(PredicateVariable predicateVariable, Type otherType) {
        int comparison = visitFromPredicate(predicateVariable, (PredicateVariable) otherType);
        if (comparison != 0) return comparison;
        return visitFromVariable(predicateVariable, otherType);
    }

    private int visitFromVariable(Variable variable, Type otherType) {
        Variable otherVariable = (Variable) otherType;
        if (thisToOther.containsKey(variable.getName()) && !otherToThis.containsKey(otherVariable.getName())) {
            return -1;
        } else if (otherToThis.containsKey(otherVariable.getName()) && !thisToOther.containsKey(variable.getName())) {
            return 1;
        }

        if (thisToOther.containsKey(variable.getName())) {
            int comparison = thisToOther.get(variable.getName()) - otherToThis.get(otherVariable.getName());
            if (comparison != 0) return comparison;
        }

        thisToOther.put(variable.getName(), idToOtherVisitOrder.get(otherVariable.getId()));
        otherToThis.put(otherVariable.getName(), idToVisitOrder.get(variable.getId()));

        return variable.isBound() && otherVariable.isBound() ?
                visit(variable.getBinding(), otherVariable.getBinding()) : 0;
    }

    private int visitAssumptions(Predicate predicate, Predicate otherPredicate) {
        Iterator<Mappable<Predicate>> thisIterator = predicate.getAssumptions().iterator();
        Iterator<Mappable<Predicate>> otherIterator = otherPredicate.getAssumptions().iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            Mappable<Predicate> assumption = thisIterator.next();
            Mappable<Predicate> otherAssumption = otherIterator.next();
            int comparison = visit(assumption.getRawObject(), otherAssumption.getRawObject());
            if (comparison != 0) return comparison;
        }

        if (thisIterator.hasNext() || otherIterator.hasNext()) {
            throw new RuntimeException("Type check should have ensured same number of assumptions");
        }

        return 0;
    }
}
