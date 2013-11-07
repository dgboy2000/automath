package automath.inference;

import automath.BaseTest;
import automath.inference.PriorityQueueProofStrategy;
import automath.inference.ProofStrategy;
import automath.type.Predicate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
public class PriorityQueueProofStrategyTest extends BaseTest {
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
        ProofStrategy proofStrategy = new PriorityQueueProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = proofStrategy.execute();
        assertFalse(isSuccessful);
    }

    @Test
    @Ignore
    public void testLogicalInference() {
        String test = new StringBuilder("A&B -> A\n")
                .append("A&B -> B\n")
                .append("A&(A->B)->B\n")
                .toString();
        Predicate goal = parser.parsePredicate("(A->B) & (B->C) -> (A->C)");
        ProofStrategy proofStrategy = new PriorityQueueProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = proofStrategy.execute();
        System.out.println(proofStrategy.getCurrentKnowledge().toString());
        assertTrue(isSuccessful);
    }
}
