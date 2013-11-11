package automath.type;

import automath.type.visitor.processor.ExpressionComparisonProcessor;

public class NumberVariable extends BaseType implements Variable {
    private Predicate boundTo = null; // Predicate this variable is bound to
    @Override public Predicate getBinding() { return boundTo; }
    @Override public void bindTo(Predicate predicate) { boundTo = predicate; }
    @Override public boolean isBound() { return boundTo != null; }

    @Override public Class getType() { return NaturalNumber.class; }

    public NumberVariable(NumberVariable var) {
        super(var.getName());
        bindTo(var.getBinding());
    }
    public NumberVariable(String name) {
        super(name);
    }

    public boolean isTypeAssignableFrom(Type otherType) {
        Class otherClass = (otherType instanceof Variable) ? ((Variable) otherType).getType() : otherType.getClass();
        // TODO: implement a real type-aware system.
        // This assumes that non-predicate expressions are natural numbers
        return getType().isAssignableFrom(otherClass) ||
                (otherClass.isAssignableFrom(Expression.class) && NaturalNumber.class.isAssignableFrom(getType()));
    }

    @Override
    public boolean isAssignableFrom(Type otherType) {
        if (otherType instanceof Predicate) return false;
        if (isBound()) {
            if (otherType instanceof NumberVariable) {
                NumberVariable otherVariable = (NumberVariable) otherType;
                return getName().equals(otherVariable.getName()) &&
                        otherVariable.isBound() &&
                        ExpressionComparisonProcessor.equal(getBinding(), otherVariable.getBinding());
            } else {
                // TODO: want to be able to assign an expression to a number variable
                return false;
            }
        } else {
            return (otherType instanceof Number) ||
                    (otherType instanceof NumberVariable) ||
                    (otherType instanceof Expression);
        }

    }

    @Override
    public int hashCode() {
        int code = getName().hashCode();
        return code;
    }

    /*
    Same problem with cyclic assumption dependencies.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null || otherObject.getClass() != getClass()) return false;
        NumberVariable otherVariable = (NumberVariable) otherObject;
        if (!isBound()) return !otherVariable.isBound();
        return getName().equals(otherVariable.getName());
    }

    @Override
    public int compareTo(Object otherObject) {
        int comparison = super.compareTo(otherObject);
        if (comparison != 0) return comparison;
        Variable otherVariable = (Variable) otherObject;
        if (this.isBound() && !otherVariable.isBound()) return 1;
        if (!this.isBound() && otherVariable.isBound()) return 0;
        return 0;
    }

    @Override
    public NumberVariable clone() {
        return new NumberVariable(this);
    }
}
