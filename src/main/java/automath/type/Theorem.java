package automath.type;

/**
 * A proven rule of inference
 */
public class Theorem extends Predicate {
    private Predicate antecedent;
    private Predicate consequent;

    public boolean isApplicable(Predicate predicate) {
        return antecedent.matches(predicate);
    }

    // Apply the theorem and return the resulting new predicate
    public Predicate apply(Predicate predicate) {
        return Predicate.EMPTY;
    }
}
