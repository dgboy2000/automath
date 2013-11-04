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
     * Generates an predicate that is a reduction of this by the specified assumption
     * @param assumptionToReduce
     * @return
     */
    public Predicate getReductionBy(Predicate assumptionToReduce) {
        if (assumptionToReduce.getAssumptions().size() != 1 ||
                !assumptionToReduce.containsAssumption(assumptionToReduce))
            throw new RuntimeException("Assumption must assume only itself.");

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
        if (this.getAssumptions().size() > 0)
            throw new RuntimeException("Assumptions can't depend on assumptions");

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
//        for (Predicate assumption : this.assumptions) {
//            if (assumption == this) continue; // Avoid infinite recursion
//            int assumptionCode = assumption.hashCode();
//            code = code*code*code + assumptionCode;
//        }
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
}
