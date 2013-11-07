package automath.inference;

import automath.type.Predicate;

/**
 * Interface for a strategy to find a proof
 */
public interface ProofStrategy {
    public boolean execute(); // Try to find a proof, return whether successful
    public Proof generateProof(Predicate goal); // Return the developed proof
    public KnowledgeCorpus getCurrentKnowledge(); // Return the current state of knowledge
}
