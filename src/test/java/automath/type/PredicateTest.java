package automath.type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests the Predicate
 */
@RunWith(JUnit4.class)
public class PredicateTest {
    Predicate equation;
    Predicate equationCopy;
    Predicate flippedEquation;
    Predicate numberEquation;

    @Before
    public void init() {
        Variable var = new Variable("x");
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
        assertTrue(equation.equals(equation));
        assertTrue(equation.equals(equationCopy));
        assertTrue(flippedEquation.equals(flippedEquation));
        assertFalse(equation.equals(numberEquation));
        assertFalse(flippedEquation.equals(equation));
        assertFalse(equation.equals(flippedEquation));
        assertFalse(numberEquation.equals(equation));
    }
}
