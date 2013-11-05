package automath.type;

import automath.util.VariableAssignment;

/**
 * A proven rule of inference
 */
public class Theorem extends Predicate {
    private Predicate antecedent; public Predicate getAntecedent() { return antecedent; }
    private Predicate consequent; public Predicate getConsequent() { return consequent; }

    // Denote an axiom. Will be the inference reason for all axioms
    public static Theorem AXIOM = new Theorem(Predicate.EMPTY, Predicate.EMPTY) {
        @Override public String getName() { return "AXIOM"; }
    };

    // Represent the process of assuming something. Will be the inference reason for all assumptions
    public static Theorem ASSUMPTION = new Theorem(Predicate.EMPTY, Predicate.EMPTY) {
        @Override public String getName() { return "ASSUMPTION"; }
    };

    // Assumption ==> Derived result
    public static Theorem REDUCTION = new Theorem(Predicate.EMPTY, Predicate.EMPTY) {
        @Override public String getName() { return "REDUCTION"; }
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
            this.getChildren().addAll(theoremPredicate.getChildren());
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
    public Predicate apply(Predicate predicate) {
        return antecedent.getVariableAssignmentTo(predicate).applyTo(consequent);
    }
    public Predicate apply(VariableAssignment variableAssignment) {
        return variableAssignment.applyTo(consequent);
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
