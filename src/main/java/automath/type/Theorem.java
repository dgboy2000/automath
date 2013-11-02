package automath.type;

/**
 * A proven rule of inference
 */
public class Theorem extends Predicate {
    private Predicate antecedent;
    private Predicate consequent;

    public Theorem() {}
    public Theorem(Predicate antecedent, Predicate consequent) {
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    public boolean isApplicable(Predicate predicate) {
        return antecedent.isAssignableFrom(predicate);
    }

    // Apply the theorem and return the resulting new predicate
    public Predicate apply(Predicate predicate) {
        return antecedent.getVariableAssignmentTo(predicate).applyTo(consequent);
    }

    @Override
    public String toString() {
        return new StringBuilder(antecedent.toString())
                .append(Operator.IMPLIES)
                .append(consequent.toString())
                .toString();
    }
}
