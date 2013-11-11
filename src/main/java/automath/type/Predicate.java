package automath.type;

import automath.type.visitor.processor.ExpressionHashProcessor;
import automath.type.visitor.ExpressionVisitor;
import automath.type.visitor.processor.VariableBindingProcessor;
import automath.util.Mappable;

import java.util.*;

/**
 * Expression with a boolean value (true / false)
 */
public class Predicate extends Expression {
    private String label;
    public String getLabel() { return label; }
    public void setLabel(String value) { this.label = value; }

    // TODO: need to implement more stuff to make Mappable navigable?
    private final NavigableSet<Mappable<Predicate>> assumptions = new TreeSet<Mappable<Predicate>>();
    public NavigableSet<Mappable<Predicate>> getAssumptions() { return assumptions; }
    public void addAssumption(Predicate assumption) { assumptions.add(new Mappable<Predicate>(assumption)); }
    public void addAssumption(Mappable<Predicate> assumption) { assumptions.add(assumption); }

    public static final Predicate TRUE = new Predicate() {
        @Override
        public boolean isAssignableFrom(Type dontCare) {
            return true;
        }
    };
    public static final Predicate FALSE = new Predicate() {
        @Override
        public boolean isAssignableFrom(Type dontCare) {
            return false;
        }
    };
    public static final Predicate CONTRADICTION = new Predicate(
            new PredicateVariable("A"),
            Operator.AND,
            new Predicate(Operator.NOT, new PredicateVariable("A"))
    );


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
        assumption.getAssumptions().clear(); // An assumption only assumes itself
        assumption.getAssumptions().addAll(this.getAssumptions());
        assumption.addAssumption(new Mappable<Predicate>(assumption));

        VariableBindingProcessor.bind(assumption);
        return assumption;
    }

    /**
     * Hash a predicate by its expression and the hashes of its assumptions
     * @return
     */
    @Override
    public int hashCode() {
        int code = ExpressionHashProcessor.hash(this);
        for (Mappable<Predicate> assumption : this.assumptions) {
            if (assumption.getRawObject() == this) continue; // Avoid infinite recursion
            int assumptionCode = assumption.hashCode();
            code += assumptionCode * assumptionCode * assumptionCode;
        }
        // TODO: include assumptions checks in .equals

        return code;
    }

    @Override
    public int compareTo(Object otherObject) {
        if (otherObject instanceof Mappable) return -((Mappable) otherObject).compareTo(this);
        int comparison = super.compareTo(otherObject);
        if (comparison != 0) return comparison;
        return this.getAssumptions().size() - ((Predicate) otherObject).getAssumptions().size();
    }

    /**
     * Can this Type be a predicate?
     * @param type
     * @return
     */
    public static boolean isPredicate(Type type) {
        return (type instanceof Predicate) || (
                (type instanceof Variable) &&
                ((Variable) type).isTypeAssignableFrom(Predicate.TRUE)
        );
    }

    private Set<String> getAssumptionLabelsWithoutSelf() {
        Set<String> assumptionLabels = new HashSet<String>();
        for (Mappable<Predicate> assumption : getAssumptions()) {
            if (this == assumption.getRawObject()) continue;
            String label = assumption.getRawObject().getLabel();

            // TODO: labels are a hack to avoid cyclic assumption graphs. Find better method
            if (label == null)
                throw new RuntimeException("Encountered assumption with null label");

            if (label.equals(this.getLabel())) continue;
            assumptionLabels.add(label);
        }
        return assumptionLabels;
    }
}
