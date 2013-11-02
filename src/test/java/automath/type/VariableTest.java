package automath.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Test the Variable type
 */
@RunWith(JUnit4.class)
public class VariableTest {
    @Test
    public void testTypes() {
        NaturalNumber num = new NaturalNumber("4");
        Predicate pred = new Predicate();

        Variable varNum = new Variable("x", num.getClass());
        Variable varExp = new Variable("P", pred.getClass());

        assertFalse(varNum.isTypeAssignableFrom(varExp));
        assertTrue(varNum.isTypeAssignableFrom(num));
        assertFalse(varNum.isTypeAssignableFrom(pred));
        assertFalse(varExp.isTypeAssignableFrom(varNum));
        assertTrue(varExp.isTypeAssignableFrom(pred));
    }
}
