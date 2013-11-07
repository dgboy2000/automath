package automath.type;

import automath.inference.VariableAssignment;

/**
 * A proven rule of inference
 */
public class Theorem extends Predicate {
    private Predicate antecedent; public Predicate getAntecedent() { return antecedent; }
    private Predicate consequent; public Predicate getConsequent() { return consequent; }

    // Denote an axiom. Will be the inference reason for all axioms
    public static final Theorem AXIOM = new Theorem(Predicate.TRUE, Predicate.TRUE) {
        @Override public String getName() { return "AXIOM"; }
    };

    // Represent the process of assuming something. Will be the inference reason for all assumptions
    public static final Theorem ASSUMPTION = new Theorem(Predicate.TRUE, Predicate.TRUE) {
        @Override public String getName() { return "ASSUMPTION"; }
    };

    // TODO: can only reduce by the last assumption on the stack
    // Assumption ==> Derived result
    public static final Theorem REDUCTION = new Theorem(Predicate.TRUE, Predicate.TRUE) {
        @Override public String getName() { return "REDUCTION"; }
    };

    public static final Theorem CONTRADICTION = new Theorem(Predicate.CONTRADICTION, Predicate.FALSE) {
        @Override public String getName() { return "CONTRADICTION"; }
    };

    public Theorem() {}
    public Theorem(Predicate antecedent, Predicate consequent) {
        super(antecedent, Operator.IMPLIES, consequent);
        this.antecedent = antecedent;
        this.consequent = consequent;
    }
    public Theorem(Predicate theoremPredicate) {
        if (theoremPredicate.getChildren().size() == 3 &&
                theoremPredicate.getChild(0) instanceof Predicate &&
                theoremPredicate.getChild(1) == Operator.IMPLIES &&
                theoremPredicate.getChild(2) instanceof Predicate) {
            // TODO: need to clone the predicate, and cloning needs to be cycle safe
            this.getChildren().addAll(theoremPredicate.getChildren());
            this.getAssumptions().addAll(theoremPredicate.getAssumptions());
            this.antecedent = (Predicate) getChild(0);
            this.consequent = (Predicate) getChild(2);
        }
        else throw new IllegalArgumentException("Argument not a theorem");
    }

    public void setTheoremName(String name) { setName(name); }

    public boolean isApplicable(Predicate predicate) {
        return antecedent.isAssignableFrom(predicate);
    }

    // Apply the theorem and return the resulting new predicate
    public Predicate applyTo(Predicate predicate) {
        return antecedent.getVariableAssignmentTo(predicate).applyTo(consequent);
    }
    public Predicate apply(VariableAssignment variableAssignment) {
        try {return variableAssignment.applyTo(consequent);}
        catch (Exception e) { return variableAssignment.applyTo(consequent); }
    }

    @Override
    public String toString() {
        return new StringBuilder(antecedent.toString())
                .append(Operator.IMPLIES)
                .append(consequent.toString())
                .toString();
    }


    /**
     * In addition to cloning the expression, set precedent and antecedent.
     * @return
     */
    @Override
    public Theorem clone() {
        return new Theorem((Predicate) super.clone());
    }
}
