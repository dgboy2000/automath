package automath.util;

import automath.BaseTest;
import automath.type.Predicate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SimpleProofStrategyTest extends BaseTest {
    @Before
    public void init() {
        corpus = new SimpleKnowledgeCorpus();
    }

    @Test
    public void alreadyProvenTest() {
        Predicate goal = parser.parsePredicate("x=4");
        KnowledgeCorpus axioms = new SimpleKnowledgeCorpus();
        axioms.addAxiomIfNew(parser.parsePredicate("a=b"));
        axioms.addAxiomIfNew(parser.parsePredicate("b=4"));
        axioms.addAxiomIfNew(parser.parsePredicate("f=g & g=h -> f=h"));
        ProofStrategy proofStrategy = new SimpleProofStrategy(goal, axioms);

        assertTrue(proofStrategy.execute());
    }

    @Test
    public void simpleProofTest() {
        Predicate goal = parser.parsePredicate("3+1=2+2");
        KnowledgeCorpus axioms = new SimpleKnowledgeCorpus();
        axioms.addAxiomIfNew(parser.parsePredicate("3+1=4"));
        axioms.addAxiomIfNew(parser.parsePredicate("2+2=4"));
        axioms.addAxiomIfNew(parser.parsePredicate("f=g & g=h -> f=h"));
        axioms.addAxiomIfNew(parser.parsePredicate("f=g -> g=f"));
        ProofStrategy proofStrategy = new SimpleProofStrategy(goal, axioms);

        assertTrue(proofStrategy.execute());
    }

    @Test
    public void testLogicalInference() {
        String test = new StringBuilder("A|B -> (~A -> B)\n")
                .append("(A -> B) -> (~A | B)\n")
                .append("A|B -> B|A\n")
                .append("(A|B) & ~A -> B\n")
                .toString();
        Predicate goal = parser.parsePredicate("(A->B) & (B->C) -> (A->C)");
        ProofStrategy proofStrategy = new SimpleProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = proofStrategy.execute();
        System.out.println(proofStrategy.getCurrentKnowledge().toString());
        assertTrue(isSuccessful);
    }

    @Test
    public void testLogicalInference2() {
        String test = new StringBuilder("A|B -> (~A -> B)\n")
                .append("(A -> B) -> (~A | B)\n")
                .append("A|B -> B|A\n")
                .append("A&B -> A\n")
                .append("A&B -> B\n")
                .append("(A|B) & ~A -> B\n")
                .toString();
        Predicate goal = parser.parsePredicate("A & (A -> B) -> B");
        ProofStrategy proofStrategy = new SimpleProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = proofStrategy.execute();
        System.out.println(proofStrategy.getCurrentKnowledge().toString());
        assertTrue(isSuccessful);
    }
}
