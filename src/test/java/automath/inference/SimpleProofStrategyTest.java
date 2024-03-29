package automath.inference;

import automath.BaseTest;
import automath.inference.KnowledgeCorpus;
import automath.inference.ProofStrategy;
import automath.inference.SimpleKnowledgeCorpus;
import automath.inference.SimpleProofStrategy;
import automath.type.Predicate;
import automath.type.PredicateVariable;
import automath.type.visitor.processor.VariableBindingProcessor;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SimpleProofStrategyTest extends BaseTest {
    protected ProofStrategy prover;

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
        ProofStrategy prover = new SimpleProofStrategy(goal, axioms);

        assertTrue(prover.execute());
    }

    @Test
    public void simpleProofTest() {
        Predicate goal = parser.parsePredicate("3+1=2+2");
        KnowledgeCorpus axioms = new SimpleKnowledgeCorpus();
        assertTrue(axioms.addAxiomIfNew(parser.parsePredicate("3+1=4")));
        assertTrue(axioms.addAxiomIfNew(parser.parsePredicate("2+2=4")));
        assertTrue(axioms.addAxiomIfNew(parser.parsePredicate("f=g & g=h -> f=h")));
        assertTrue(axioms.addAxiomIfNew(parser.parsePredicate("f=g -> g=f")));
        ProofStrategy prover = new SimpleProofStrategy(goal, axioms);

        assertTrue(prover.execute());
    }

    @Test
    @Ignore
    public void proofCompletionTest() {
        corpus.addAxiomIfNew(parser.parseTheorem("A&B -> A"));
        corpus.addAxiomIfNew(parser.parseTheorem("A&B -> B"));
        corpus.addAxiomIfNew(parser.parseTheorem("(A->B)&A -> B"));

        // Add assumptions as axioms
        Predicate assumption1 = parser.parsePredicate("(A->B)&(B->C)").asAssumption();
        PredicateVariable assumption2 = (PredicateVariable) parser.parseVariable("A");
        assumption2.bindTo(assumption1);
        assumption2.addAssumption(assumption1);
        assumption2.addAssumption(assumption2);
        corpus.addAxiomIfNew(assumption1);
        corpus.addAxiomIfNew(assumption2);

        Predicate goal = parser.parsePredicate("(A->B)&(B->C) -> (A->C)");

        ProofStrategy prover = new SimpleProofStrategy(goal, corpus);
        assertTrue(prover.execute());
    }

    @Test
    @Ignore
    public void testLogicalInference() {
        String test = new StringBuilder("A&B -> A\n")
                .append("A&B -> B\n")
                .append("A&(A->B)->B\n")
                .toString();
        Predicate goal = parser.parsePredicate("(A->B) & (B->C) -> (A->C)");
        ProofStrategy prover = new SimpleProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = prover.execute();
        System.out.println(prover.getCurrentKnowledge().toString());
        assertTrue(isSuccessful);
    }

    @Test
    @Ignore
    public void testLogicalInference2() {
        String test = new StringBuilder("A|B -> (~A -> B)\n")
                .append("(A -> B) -> (~A | B)\n")
                .append("A|B -> B|A\n")
                .append("A&B -> A\n")
                .append("A&B -> B\n")
                .append("(A|B) & ~A -> B\n")
                .toString();
        Predicate goal = parser.parsePredicate("A & (A -> B) -> B");
        prover = new SimpleProofStrategy(goal, parser.parseFile(test));

        boolean isSuccessful = prover.execute();
        System.out.println(prover.getCurrentKnowledge().toString());
        assertTrue(isSuccessful);
        System.out.println(prover.generateProof(goal).toString());
    }
}
