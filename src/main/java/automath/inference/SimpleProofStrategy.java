package automath.inference;

import automath.type.Predicate;
import automath.type.Theorem;
import automath.type.visitor.ExpressionVisitor;
import automath.type.visitor.processor.AntecedentExtractionProcessor;
import automath.util.Mappable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple proof strategy does a BFS, implemented quite slowly.
 * Will need to speedup later, this is just to make dev happen fast.
 */
public class SimpleProofStrategy implements ProofStrategy {
    private static final int MAX_ROUNDS_OF_INFERENCE = 5;
    private static final int MAX_NUM_ASSERTIONS = 4;

    private final Predicate goal; // The result to prove
    private final KnowledgeCorpus currentKnowledge; // The current set of known facts
    private int numAssertionsLimit = 1;


    public SimpleProofStrategy(Predicate goal, KnowledgeCorpus axioms) {
        this.goal = goal;
        this.currentKnowledge = axioms.clone();
    }

    /*
     * Do the next layer of searching and return whether anything changed
     */
    private boolean executeOneRoundOfInference() {
        List<Theorem> rulesOfInference = currentKnowledge.getTheorems();
        rulesOfInference.add(Theorem.REDUCTION);
        return executeOnceRoundOfInferenceWith(rulesOfInference);
    }

    private boolean executeOnceRoundOfInferenceWith(List<Theorem> rulesOfInference) {
        Map<Mappable<Predicate>, Inference> resultToInference = new HashMap<Mappable<Predicate>, Inference>();
        for (Theorem rule : rulesOfInference) {
            for (Inference inference : currentKnowledge.getLegalInferences(rule)) {
                resultToInference.put(new Mappable<Predicate>(inference.result), inference);
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

    /**
     * Generate candidate assumptions that might help the proof
     * @return
     */
    private List<Inference> generateCandidateAssumptions() {
        List<Inference> candidateAssumptions = new ArrayList<Inference>();
        List<Predicate> currentAssumptions = new ArrayList<Predicate>();

        AntecedentExtractionProcessor processor = new AntecedentExtractionProcessor();
        ExpressionVisitor visitor = new ExpressionVisitor(processor);
//        for (int i=0; i<currentKnowledge.size(); ++i) {
//            Predicate fact = currentKnowledge.get(i);
//            if (fact.isAssumption()) currentAssumptions.add(fact);
//            visitor.visit(fact);
//        }
        visitor.visit(goal);

        for (Mappable<Predicate> antecedent : processor.getAntecedents()) {
            candidateAssumptions.add(Inference.assumption(antecedent.getRawObject()));

            // Allow assumptions that compound with other assumptions
            for (Predicate assumptionToBuildOn : currentAssumptions) {
                candidateAssumptions.add(Inference.assumption(antecedent.getRawObject(), assumptionToBuildOn));
            }
        }


        return candidateAssumptions;
    }

    private void addAssumptions() {
        for (Inference assumption : generateCandidateAssumptions()) {
            currentKnowledge.addInferenceIfNew(assumption);
        }
    }

    @Override
    public boolean execute() {
        for (int j=0; j<numAssertionsLimit; ++j) {
            System.out.println("Unproven with "+j+" joint assertions; increasing to "+(j+1));
//            addAssumptions();
            for (int i=0; i<MAX_ROUNDS_OF_INFERENCE; ++i) {
                if (currentKnowledge.isKnown(goal)) return true;
                if (!executeOneRoundOfInference()) break;
                System.out.println("Finished inference round " + (i + 1) + " with " + currentKnowledge.size() + " known facts");
                System.out.println(currentKnowledge.toString());
            }
        }
        return currentKnowledge.isKnown(goal);
    }

    /**
     * A proof is a post-order traversal of a
     * @return
     */
    @Override
    public Proof generateProof(Predicate goal) {
        return new Proof(goal, currentKnowledge);
    }

    @Override
    public KnowledgeCorpus getCurrentKnowledge() {
        return currentKnowledge;
    }
}
