package automath.util;

/**
 * Interface for a strategy to find a proof
 */
public interface ProofStrategy {
    public boolean execute(); // Try to find a proof, return whether successful
    public Proof generateProof(); // Return the developed proof
}
