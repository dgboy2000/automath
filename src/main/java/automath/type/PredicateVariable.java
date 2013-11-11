package automath.type;

import automath.type.visitor.processor.ExpressionComparisonProcessor;
import automath.util.Mappable;

/**
 * Represents a higher-order variable
 */
public class PredicateVariable extends Predicate implements Variable {
    private Predicate boundTo = null;
    @Override public Predicate getBinding() { return boundTo; }
    @Override public void bindTo(Predicate predicate) { boundTo = predicate; }
    @Override public boolean isBound() { return boundTo != null; }

    public PredicateVariable(String name) {
        this.setName(name);
    }

    public PredicateVariable(PredicateVariable var) {
        this(var.getName());
        bindTo(var.getBinding());
    }

    @Override
    public Class getType() {
        return Predicate.class;
    }

    @Override
    public boolean isTypeAssignableFrom(Type otherType) {
        return otherType instanceof Predicate;
    }

    @Override
    public boolean isAssignableFrom(Type otherType) {
        if (isBound()) {
            if (otherType instanceof PredicateVariable) {
                PredicateVariable otherVariable = (PredicateVariable) otherType;
                return getName().equals(otherVariable.getName()) &&
                        otherVariable.isBound() &&
                        ExpressionComparisonProcessor.equal(getBinding(), otherVariable.getBinding());
            } else if (otherType instanceof Predicate) {
                Predicate otherPredicate = (Predicate) otherType;
                return ExpressionComparisonProcessor.equal(getBinding(), otherPredicate);
            } else return false;
        } else {
            return otherType instanceof Predicate;
        }

    }

    @Override
    public int hashCode() {
        int code = getName().hashCode();
        return code;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || !otherObject.getClass().equals(getClass())) return false;
        PredicateVariable otherVariable = (PredicateVariable) otherObject;
        if (getAssumptions().size() != otherVariable.getAssumptions().size()) return false;
        if (!isBound()) return !otherVariable.isBound();
        return getName().equals(otherVariable.getName());
    }

    @Override
    public PredicateVariable clone() {
        return new PredicateVariable(this);
    }

    @Override
    public int compareTo(Object otherObject) {
        if (otherObject instanceof Mappable) return -((Mappable) otherObject).compareTo(this);
        int comparison = super.compareTo(otherObject);
        if (comparison != 0) return comparison;
        Variable otherVariable = (Variable) otherObject;
        if (this.isBound() && !otherVariable.isBound()) return 1;
        if (!this.isBound() && otherVariable.isBound()) return -1;
        return 0;
    }

    @Override
    public String toString() { return getName(); }
}
