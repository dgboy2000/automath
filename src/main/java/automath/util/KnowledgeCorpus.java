package automath.util;

import automath.type.Predicate;
import automath.type.Theorem;

import java.util.List;

/**
 * Corpus of known statements
 */
public interface KnowledgeCorpus extends Cloneable {
    // TODO: remove this method if nobody needs to use it
    public List<VariableAssignment> getAssignmentsOf(Predicate predicate); // Find all predicates which are an instance of the specified rule
    public List<Inference> getLegalInferences(Theorem theorem); // Find all inferences that can be made with this theorem

    public boolean addAxiomIfNew(Predicate axiom); // Add to corpus if previously unknown, and return whether added
    public boolean addInferenceIfNew(Inference inference); // Add to corpus if new result, and return whether added
    public boolean isKnown(Predicate predicate); // Is this predicate part of the corpus of knowledge?

    public Predicate get(int i); // Get the i-th predicate
    public Inference getInference(Predicate predicate); // Get the reasoning step for a predicate
    public List<Theorem> getTheorems(); // Return a list of theorems in the corpus
    public int size(); // Number of facts

    public KnowledgeCorpus clone();
}
