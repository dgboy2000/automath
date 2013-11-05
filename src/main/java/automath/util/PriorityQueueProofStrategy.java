package automath.util;

import automath.type.Predicate;
import automath.type.Theorem;

import java.util.*;

/**
 * Apply inferences in priority order.
 */
public class PriorityQueueProofStrategy extends PriorityQueue<Inference> implements ProofStrategy {
    private static final int MAX_CORPUS_SIZE = 200;
    private static final int RECOMPUTE_INFERENCES_PERIOD = 20;

    private final Predicate goal; // The result to prove
    private final KnowledgeCorpus currentKnowledge; // The current set of known facts
    private int numNewInferences = 0;

    public final static Comparator<Inference> SYMBOL_COUNTER = new Comparator<Inference>() {
        @Override
        public int compare(Inference inference, Inference inference2) {
            return SymbolCountProcessor.countSymbolsIn(inference.result) -
                    SymbolCountProcessor.countSymbolsIn(inference2.result);
        }
    };

    public PriorityQueueProofStrategy(
            Predicate goal,
            KnowledgeCorpus axioms) {
        this(goal, axioms, SYMBOL_COUNTER);
    }

    public PriorityQueueProofStrategy(
            Predicate goal,
            KnowledgeCorpus axioms,
            Comparator<Inference> inferenceComparator) {
        super(MAX_CORPUS_SIZE, inferenceComparator);
        this.goal = goal;
        this.currentKnowledge = axioms.clone();
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

        for (Predicate antecedent : processor.getAntecedents()) {
            candidateAssumptions.add(Inference.assumption(antecedent));

            // Allow assumptions that compound with other assumptions
            for (Predicate assumptionToBuildOn : currentAssumptions) {
                candidateAssumptions.add(Inference.assumption(antecedent, assumptionToBuildOn));
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
        int inferenceCnt = 0;
        numNewInferences = -1; // Hack to force refresh to work the first time
        refreshInferences();
        while (currentKnowledge.size() < MAX_CORPUS_SIZE) {
            if (currentKnowledge.isKnown(goal)) return true;
            if (!processNextInference() || numNewInferences % RECOMPUTE_INFERENCES_PERIOD == 0) {
                if (!refreshInferences())
                    return false;
            }
            ++inferenceCnt;
        }
        System.out.println("Reached max corpus size "+MAX_CORPUS_SIZE+" in "+inferenceCnt+" iterations");
        return currentKnowledge.isKnown(goal);
    }

    /**
     * Recompute legal inferences that may be made
     * @return True if we were able to add new inferences since last time.
     */
    private boolean refreshInferences() {
        if (numNewInferences == 0) return false;
        numNewInferences = 0;

        for (Theorem theorem : currentKnowledge.getTheorems()) {
            this.addAll(currentKnowledge.getLegalInferences(theorem));
        }
        this.addAll(currentKnowledge.getLegalInferences(Theorem.REDUCTION));
        this.addAssumptions();
        return true;
    }

    /**
     * Process the next inference on the queue
     * @return True if there is another expression to process
     */
    private boolean processNextInference() {
        Inference nextInference = this.poll();
        if (currentKnowledge.addInferenceIfNew(nextInference)) ++numNewInferences;
        return this.peek() != null;
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
