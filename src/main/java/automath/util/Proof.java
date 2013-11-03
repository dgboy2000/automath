package automath.util;

import automath.type.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a logical proof
 */
public class Proof {
    private final List<Predicate> axioms = new ArrayList<Predicate>();
    public List<Predicate> getAxioms() { return axioms; }

    private final List<Inference> steps = new ArrayList<Inference>();
    public Inference getStep(int i) { return steps.get(i); }
    public List<Inference> getSteps() { return steps; }
    public void addStep(Inference inference) { steps.add(inference); }

    private Predicate goal;
    public Predicate getGoal() { return goal; }

    public Proof(List<Predicate> axioms, Predicate goal) {
        this.axioms.addAll(axioms);
        this.goal = goal;
    }

    public Proof(List<Predicate> axioms, List<Inference> steps, Predicate goal) {
        this(axioms, goal);
        this.steps.addAll(steps);
    }
}
