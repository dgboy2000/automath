package automath.inference;

import automath.BaseTest;
import automath.inference.PriorityQueueProofStrategy;
import automath.inference.ProofStrategy;
import automath.type.Predicate;
import automath.type.PredicateVariable;
import automath.util.AutomathLogger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class PriorityQueueProofStrategyTest extends BaseTest {
    protected ProofStrategy prover;

    @Before
    public void init() {
        corpus = new SimpleKnowledgeCorpus();
    }

    @Test
    @Ignore
    public void testInvalidInference() {
        String test = new StringBuilder("A|~A\n")
                .append("~A | B -> (A->B)\n")
                .append("A|B -> B|A\n")
                .append("A&B -> A\n")
                .append("A&B -> B\n")
                .toString();
        Predicate goal = parser.parsePredicate("A&B -> B&A");
        prover = new PriorityQueueProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = prover.execute();
        assertFalse(isSuccessful);
    }

    @Test
    public void proofCompletionTest() {
        corpus.addAxiomIfNew(parser.parseTheorem("A&B -> A"));
        corpus.addAxiomIfNew(parser.parseTheorem("A&B -> B"));
        corpus.addAxiomIfNew(parser.parseTheorem("(A->B)&A -> B"));

        // Add assumptions as axioms
        Predicate assumption1 = parser.parsePredicate("(A->B)&(B->C)").asAssumption();
        PredicateVariable assumption2 = (PredicateVariable) parser.parseVariable("A");
        assumption2.bindTo(assumption1);
        assumption2.addAssumption(assumption2);
        corpus.addAxiomIfNew(assumption1);
        corpus.addAxiomIfNew(assumption2);

        final Predicate goal = parser.parsePredicate("(A->B)&(B->C) -> (A->C)");

        prover = new PriorityQueueProofStrategy(goal, corpus);
        assertTrue(prover.execute());
        new AutomathLogger(){@Override public String fine(){
            return "\n"+prover.generateProof(goal);
        }};
    }

    @Test
    @Ignore
    public void testLogicalInference() {
        String test = new StringBuilder("A&B -> A\n")
                .append("A&B -> B\n")
                .append("A&(A->B)->B\n")
                .toString();
        Predicate goal = parser.parsePredicate("(A->B) & (B->C) -> (A->C)");
        prover = new PriorityQueueProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = prover.execute();
        System.out.println(prover.getCurrentKnowledge().toString());
        assertTrue(isSuccessful);
    }
}
