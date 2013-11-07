package automath.type;

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
        return getType().isAssignableFrom(otherClass) |
                (otherClass.isAssignableFrom(Expression.class) && NaturalNumber.class.isAssignableFrom(getType()));
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
        Variable otherVariable = (Variable) otherObject;
        if (!isBound()) return !otherVariable.isBound();
        return getName().equals(otherVariable.getName());
    }

    @Override
    public NumberVariable clone() {
        return new NumberVariable(this);
    }
}
