package automath.type;

import automath.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

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
}
