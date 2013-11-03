package automath.util;

import automath.type.Predicate;
import automath.type.Theorem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple proof strategy does a BFS, implemented quite slowly.
 * Will need to speedup later, this is just to make dev happen fast.
 */
public class SimpleProofStrategy implements ProofStrategy {
    private static final int MAX_ROUNDS_OF_INFERENCE = 20;

    private final Predicate goal; // The result to prove
    private final KnowledgeCorpus axioms; // The unchanging set of axioms
    private final KnowledgeCorpus currentKnowledge; // The current set of known facts

    public SimpleProofStrategy(Predicate goal, KnowledgeCorpus axioms) {
        this.goal = goal;
        this.axioms = axioms.clone();
        this.currentKnowledge = axioms.clone();
    }

    private List<Theorem> getKnownTheorems() {
        List<Theorem> knownTheorems = new ArrayList<Theorem>();
        for (int factInd = 0; factInd < currentKnowledge.size(); ++factInd) {
            Predicate curFact = currentKnowledge.get(factInd);
            if (curFact instanceof Theorem) knownTheorems.add((Theorem) curFact);
        }
        return knownTheorems;
    }

    /*
     * Do the next layer of searching and return whether anything changed
     */
    private boolean executeOneRoundOfInference() {
        List<Theorem> rulesOfInference = getKnownTheorems();
        Map<Predicate, Inference> resultToInference = new HashMap<Predicate, Inference>();
        for (Theorem rule : rulesOfInference) {
            for (Inference inference : currentKnowledge.getLegalInferences(rule)) {
                resultToInference.put(inference.result, inference);
                if (inference.result == null) {
                    throw new RuntimeException("Null inference result");
                }
            }
        }

        boolean didAnythingChange = false;
        for (Inference inference : resultToInference.values()) {
            didAnythingChange = didAnythingChange | currentKnowledge.addInferenceIfNew(inference);
        }

        return didAnythingChange;
    }

    @Override
    public boolean execute() {
        for (int i=0; i<MAX_ROUNDS_OF_INFERENCE; ++i) {
            if (currentKnowledge.isKnown(goal)) return true;
            if (!executeOneRoundOfInference()) return false;
        }
        return currentKnowledge.isKnown(goal);
    }

    @Override
    public Proof generateProof() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
