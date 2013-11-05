package automath.type;

import automath.BaseTest;
import automath.util.Inference;
import automath.util.SimpleKnowledgeCorpus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Test the Theorem type
 */
@RunWith(JUnit4.class)
public class TheoremTest extends BaseTest {
    @Test
    public void simpleApplicationTest() {
        Variable var = parser.parseVariable("x");
        NaturalNumber num = new NaturalNumber("4");
        Predicate equation = new Predicate(var, Operator.EQUALS, num);
        Predicate flippedEquation = new Predicate(num, Operator.EQUALS, var);

        Variable aVar = parser.parseVariable("a");
        Variable bVar = parser.parseVariable("b");
        Predicate antecedent = new Predicate(aVar, Operator.EQUALS, bVar);
        Predicate consequent = new Predicate(bVar, Operator.EQUALS, aVar);

        Theorem theorem = new Theorem(antecedent, consequent);
        assertTrue(theorem.isApplicable(equation));

        Type result = theorem.applyTo(equation);
        assertTrue(flippedEquation.equals(result));
    }

    @Test
    public void testFromPredicate() {
        Predicate predicate = parser.parsePredicate("x=y -> y=x");
        Theorem theorem = new Theorem(predicate);
        assertEquals(theorem.getAntecedent().toString(), "x=y");
        assertEquals(theorem.getConsequent().toString(), "y=x");

        boolean isException = false;
        try { new Theorem(parser.parsePredicate("x=x & y=y")); }
        catch (Exception e) { isException = true; }
        assertTrue(isException);
    }

    @Test
    public void testVariableAssignmentIsAppliedToPrecedent() {
        Theorem theorem = parser.parseTheorem("A&B -> A");
        corpus = new SimpleKnowledgeCorpus();
        corpus.addAxiomIfNew(parser.parsePredicate("A|~A"));
        List<Inference> legalInferences = corpus.getLegalInferences(theorem);
        assertEquals(legalInferences.size(), 1);
        assertThat(legalInferences.get(0).result, not(equalTo(parser.parsePredicate("A"))));
    }
}
