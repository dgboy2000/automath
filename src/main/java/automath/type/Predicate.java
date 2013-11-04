package automath.type;

import automath.util.ExpressionHashProcessor;
import automath.util.VariableAssignment;

import java.util.HashSet;
import java.util.Set;

/**
 * Expression with a boolean value (true / false)
 */
public class Predicate extends Expression {
    private String label;
    public String getLabel() { return label; }
    public void setLabel(String value) { this.label = value; }

    private final Set<Predicate> assumptions = new HashSet<Predicate>();
    public Set<Predicate> getAssumptions() { return assumptions; }

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
        if (assumptionToReduce.getAssumptions().size() > 0)
            throw new RuntimeException("Assumptions can't depend on assumptions");

        Predicate reduced = new Predicate(
                assumptionToReduce,
                Operator.IMPLIES,
                this.clone()
        );
        reduced.getAssumptions().addAll(this.getAssumptions());

        if (!reduced.getAssumptions().remove(assumptionToReduce))
            throw new RuntimeException("Tried to reduce predicate by non-assumption");

        return reduced;
    }

    /**
     * Hash a predicate by it's expression and the hashes of it's assumptions
     * @return
     */
    @Override
    public int hashCode() {
        int code = ExpressionHashProcessor.hash(this);
        for (Predicate assumption : this.assumptions) {
            int assumptionCode = assumption.hashCode();
            code = code*code*code + assumptionCode;
        }

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
