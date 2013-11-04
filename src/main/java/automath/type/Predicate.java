package automath.type;

import automath.util.ExpressionHashProcessor;
import automath.util.ExpressionVisitor;
import automath.util.VariableBindingProcessor;

import java.util.*;

/**
 * Expression with a boolean value (true / false)
 */
public class Predicate extends Expression {
    private String label;
    public String getLabel() { return label; }
    public void setLabel(String value) { this.label = value; }

    private final List<Predicate> assumptions = new ArrayList<Predicate>();
    public List<Predicate> getAssumptions() { return assumptions; }
    public boolean containsAssumption(Predicate assumption) {
        for (Predicate containedAssumption : assumptions)
            if (containedAssumption.equals(assumption)) return true;
        return false;
    }

    public static Predicate EMPTY = new Predicate() {
        @Override
        public boolean isAssignableFrom(Type dontCare) {
            return true;
        }
    };

    public Predicate() {}

    public Predicate(Type... children) { super(children); }

    /*
     * Does this predicate consist of 2 predicates and the 'AND' operator?
     */
    public boolean isCompoundPredicate() {
        return getChildren().size() == 3 &&
                (getChild(0) instanceof Predicate) &&
                (getChild(1) instanceof Operator) &&
                getChild(1) == Operator.AND &&
                (getChild(2) instanceof Predicate);
    }
    public Predicate getLhs() { return (Predicate) getChild(0); }
    public Predicate getRhs() { return (Predicate) getChild(2); }

    /**
     * Assumptions are the only predicates that contain themselves as assumptions
     * @return
     */
    public boolean isAssumption() {
        return getAssumptions().size() > 0 && getAssumptions().contains(this);
    }

    /**
     * Generates an predicate that is a reduction of this by the specified assumption
     * @param assumptionToReduce
     * @return
     */
    public Predicate getReductionBy(Predicate assumptionToReduce) {
        Predicate reduced = new Predicate(
                assumptionToReduce.clone(),
                Operator.IMPLIES,
                this.clone()
        );
        reduced.getAssumptions().addAll(this.getAssumptions());
        if (!reduced.getAssumptions().remove(assumptionToReduce))
            throw new RuntimeException("Tried to reduce predicate by non-assumption");

        new ExpressionVisitor(VariableBindingProcessor.unbindingProcessor(assumptionToReduce)).visit(reduced);

        return reduced;
    }

    /**
     * Return a copy of this predicate with all unbound variables bound by the assumption.
     * @return
     */
    public Predicate asAssumption() {
        Predicate assumption = (Predicate) this.clone();
        assumption.getAssumptions().addAll(this.getAssumptions());
        assumption.getAssumptions().add(assumption);

        new ExpressionVisitor(VariableBindingProcessor.bindingProcessor(assumption)).visit(assumption);

        return assumption;
    }

    /**
     * Hash a predicate by it's expression and the hashes of it's assumptions
     * @return
     */
    @Override
    public int hashCode() {
        int code = ExpressionHashProcessor.hash(this);
        for (Predicate assumption : this.assumptions) {
            if (assumption == this) continue; // Avoid infinite recursion
            int assumptionCode = assumption.hashCode();
            code += assumptionCode * assumptionCode * assumptionCode;
        }
        // TODO: include assumptions checks in .equals

        return code;
    }

    /**
     * Can this Type be a predicate?
     * @param type
     * @return
     */
    public static boolean isPredicate(Type type) {
        return (type instanceof Predicate) || (
                (type instanceof Variable) &&
                ((Variable) type).isTypeAssignableFrom(Predicate.EMPTY)
        );
    }

    /**
     * Two predicates are equal if the underlying expressions are equal and they
     * depend on the same assumptions.
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Predicate) || !super.equals(object)) return false;
        if (this == object) return true;
        Predicate predicate = (Predicate) object;
        return getAssumptionLabelsWithoutSelf().equals(predicate.getAssumptionLabelsWithoutSelf());
    }

    private Set<String> getAssumptionLabelsWithoutSelf() {
        Set<String> assumptionLabels = new HashSet<String>();
        for (Predicate assumption : getAssumptions()) {
            String label = assumption.getLabel();
            if (label == null) throw new RuntimeException("Encountered assumption with null label");
            if (label.equals(this.getLabel())) continue;
            assumptionLabels.add(label);
        }
        return assumptionLabels;
    }
}
