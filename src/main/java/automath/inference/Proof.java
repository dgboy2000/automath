package automath.inference;

import automath.inference.Inference;
import automath.inference.KnowledgeCorpus;
import automath.type.Predicate;
import automath.type.Theorem;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    // Keeps track of predicates we've already seen
    private Set<String> includedPredicates = new HashSet<String>();

    private Predicate goal;
    public Predicate getGoal() { return goal; }

    private KnowledgeCorpus knowledgeCorpus;

    public Proof(Predicate goal, KnowledgeCorpus knowledgeCorpus) {
        this.goal = goal;
        this.knowledgeCorpus = knowledgeCorpus;
        generate();
    }

    public Proof(List<Predicate> axioms, Predicate goal) {
        this.axioms.addAll(axioms);
        this.goal = goal;
    }

    public Proof(List<Predicate> axioms, List<Inference> steps, Predicate goal) {
        this(axioms, goal);
        this.steps.addAll(steps);
    }

    private void generate() {
        Predicate fact;
        for (int i=0; i<knowledgeCorpus.size(); ++i) {
            fact = knowledgeCorpus.get(i);
            if (fact.isAssignableFrom(goal)) {
                visitInference(knowledgeCorpus.getInference(fact));
            }
        }
    }

    private void visitInference(Inference inference) {
        if (inference.theorem == Theorem.AXIOM) {
            this.axioms.add(inference.result);
            return;
        }

        for (Predicate predecessor : inference.precedents) {
            String label = predecessor.getLabel();
            if (this.includedPredicates.contains(label)) continue;
            this.includedPredicates.add(label);
            visitInference(knowledgeCorpus.getInference(predecessor));
        }
        this.steps.add(inference);
    }

    public String toString() {
        List<String> knowledgeStrings = new ArrayList<String>();
        int stepCounter = 0;

        for (Predicate axiom : this.axioms) {
            ++stepCounter;
            knowledgeStrings.add(stepCounter+": "+knowledgeCorpus.getInference(axiom).toString());
        }

        for (Inference inference : this.steps) {
            ++stepCounter;
            knowledgeStrings.add(stepCounter+": "+inference.toString());
        }

        return StringUtils.join(knowledgeStrings, "\n");
    }
}
