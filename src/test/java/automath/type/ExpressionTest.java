package automath.type;

import automath.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Tests the Expression
 */
@RunWith(JUnit4.class)
public class ExpressionTest extends BaseTest {
    @Test
    public void copyTest() {
        Variable var = parser.parseVariable("x");
        NaturalNumber num = new NaturalNumber("4");
        Expression equation = new Predicate(var, Operator.EQUALS, num);

        // TODO: figure out how to create a new instance of a variable class
        assertEquals(equation, equation.clone());
    }

    @Test
    public void testBecomeCloneOf() {
        Expression blank = new Expression();
        Expression sum = parser.parseExpression("x+4");
        Expression flippedSum = parser.parseExpression("4+x");

        blank.becomeCloneOf(flippedSum);
        assertEquals(blank, flippedSum);
        assertNotSame(blank, sum);

        blank.becomeCloneOf(sum);
        assertEquals(blank, sum);
        assertNotSame(blank, flippedSum);
    }
}
