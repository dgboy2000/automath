package automath.type;

import automath.BaseTest;
import automath.inference.Inference;
import automath.type.visitor.processor.ExpressionComparisonProcessor;
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
        assertTrue(new ExpressionComparisonProcessor(true).equal(equation, equation));
        assertTrue(new ExpressionComparisonProcessor(true).equal(equation, equationCopy));
        assertTrue(new ExpressionComparisonProcessor(true).equal(flippedEquation, flippedEquation));
        assertFalse(new ExpressionComparisonProcessor(true).equal(equation, numberEquation));
        assertFalse(new ExpressionComparisonProcessor(true).equal(flippedEquation, equation));
        assertFalse(new ExpressionComparisonProcessor(true).equal(equation, flippedEquation));
        assertFalse(new ExpressionComparisonProcessor(true).equal(numberEquation, equation));
    }

    @Test
    public void assumptionTest() {
        Predicate assumption1 = Inference.assumption(parser.parsePredicate("A")).result;
        Predicate assumption2 = Inference.assumption(parser.parsePredicate("A")).result;
        assertEquals(assumption1.hashCode(), assumption2.hashCode());
        assertTrue(new ExpressionComparisonProcessor(true).equal(assumption1, assumption2));
    }
}
