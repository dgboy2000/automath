package automath.type;

import automath.BaseTest;
import automath.inference.Inference;
import automath.type.visitor.processor.ExpressionEqualityProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Tests the Predicate
 */
@RunWith(JUnit4.class)
public class PredicateTest extends BaseTest {
    Predicate equation;
    Predicate equationCopy;
    Predicate flippedEquation;
    Predicate numberEquation;

    @Before
    public void init() {
        Variable var = parser.parseVariable("x");
        NaturalNumber num = new NaturalNumber("4");

        equation = new Predicate(var, Operator.EQUALS, num);
        equationCopy = new Predicate(var, Operator.EQUALS, num);
        flippedEquation = new Predicate(num, Operator.EQUALS, var);
        numberEquation = new Predicate(num, Operator.EQUALS, num);
    }

    @Test
    public void simplePrint() {
        assertThat(equation.toString(), is("x=4"));
    }

    @Test
    public void matchingTest() {
        assertTrue(equation.isAssignableFrom(equation));
        assertTrue(flippedEquation.isAssignableFrom(flippedEquation));
        assertTrue(equation.isAssignableFrom(numberEquation));
        assertFalse(numberEquation.isAssignableFrom(equation));
        assertFalse(equation.isAssignableFrom(flippedEquation));
        assertFalse(flippedEquation.isAssignableFrom(equation));
    }

    @Test
    public void equalsTest() {
        assertTrue(ExpressionEqualityProcessor.equal(equation, equation));
        assertTrue(ExpressionEqualityProcessor.equal(equation, equationCopy));
        assertTrue(ExpressionEqualityProcessor.equal(flippedEquation, flippedEquation));
        assertFalse(ExpressionEqualityProcessor.equal(equation, numberEquation));
        assertFalse(ExpressionEqualityProcessor.equal(flippedEquation, equation));
        assertFalse(ExpressionEqualityProcessor.equal(equation, flippedEquation));
        assertFalse(ExpressionEqualityProcessor.equal(numberEquation, equation));
    }

    @Test
    public void assumptionTest() {
        Predicate assumption1 = Inference.assumption(parser.parsePredicate("A")).result;
        Predicate assumption2 = Inference.assumption(parser.parsePredicate("A")).result;
        assertEquals(assumption1.hashCode(), assumption2.hashCode());
        assertTrue(ExpressionEqualityProcessor.equal(assumption1, assumption2));
    }
}
