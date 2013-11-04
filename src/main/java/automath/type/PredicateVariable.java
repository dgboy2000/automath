package automath.type;

/**
 * Represents a higher-order variable
 */
public class PredicateVariable extends Predicate implements Variable {
    private Predicate boundTo = null;
    @Override public Predicate getBinding() { return boundTo; }
    @Override public void bindTo(Predicate predicate) { boundTo = predicate; }
    @Override public boolean isBound() { return boundTo != null; }

    private String name;
    @Override public String getName() { return name; }

    public PredicateVariable(String name) {
        this.name = name;
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
    public PredicateVariable clone() {
        return new PredicateVariable(this);
    }

    @Override
    public String toString() { return getName(); }
}
