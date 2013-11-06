package automath.util;

import automath.BaseTest;
import automath.type.Predicate;
import automath.type.Theorem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SimpleKnowledgeCorpusTest extends BaseTest {
    @Before
    public void init() {
        corpus = new SimpleKnowledgeCorpus();
    }

    @Test
    public void testAntecedentMatching() {
        corpus.addAxiomIfNew(parser.parsePredicate("x=y"));
        Predicate pred = parser.parsePredicate("a=b");
        Predicate otherPred = parser.parsePredicate("c=c");

        assertEquals(corpus.getAssignmentsOf(pred).size(), 1);
        assertEquals(corpus.getAssignmentsOf(otherPred).size(), 0);

        Theorem theorem = new Theorem(parser.parsePredicate("a=b -> b=a"));
        assertEquals(corpus.getLegalInferences(theorem).size(), 1);
        assertEquals(corpus.getLegalInferences(theorem).get(0).result, parser.parsePredicate("y=x"));
    }

    @Test
    public void conjunctionMatching() {
        corpus.addAxiomIfNew(parser.parsePredicate("a=b"));
        corpus.addAxiomIfNew(parser.parsePredicate("b=4"));

        Predicate predicate = parser.parsePredicate("x = y & y = z");
        assertEquals(predicate.toString(), "x=y&y=z");

        assertEquals(corpus.getAssignmentsOf(predicate).size(), 1);
        VariableAssignment assignment = corpus.getAssignmentsOf(predicate).get(0);
        Theorem transitivity = new Theorem(predicate, parser.parsePredicate("x=z"));
        Predicate result = transitivity.apply(assignment);
        assertEquals(result, parser.parsePredicate("a = 4"));
    }

    @Test
    public void newTest() {
        // TODO: variables can be bound over an entire corpus, don't make every instance of a var equal
        assertTrue(corpus.addAxiomIfNew(parser.parsePredicate("a=b")));
        assertFalse(corpus.addAxiomIfNew(parser.parsePredicate("a=b")));
        assertTrue(corpus.addAxiomIfNew(parser.parsePredicate("b=c")));
        assertTrue(corpus.addAxiomIfNew(parser.parsePredicate("b=4")));

        assertTrue(corpus.addAxiomIfNew(parser.parsePredicate("x=y -> y=x")));
        assertTrue(corpus.get(corpus.size()-1) instanceof Theorem);
    }

    @Test
    public void duplicateTest() {
        String test = new StringBuilder("A&B -> A\n")
                .append("A&B -> B\n")
                .toString();
        corpus = parser.parseFile(test);
        assertEquals(corpus.size(), 2);
    }
}
