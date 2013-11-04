package automath.type;

import automath.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Test the Variable type
 */
@RunWith(JUnit4.class)
public class VariableTest extends BaseTest {
    @Test
    public void testTypes() {
        NaturalNumber num = new NaturalNumber("4");
        Predicate pred = new Predicate();

        Variable varNum = parser.parseVariable("4");
        Variable varExp = parser.parseVariable("P");

        assertFalse(varNum.isTypeAssignableFrom(varExp));
        assertTrue(varNum.isTypeAssignableFrom(num));
        assertFalse(varNum.isTypeAssignableFrom(pred));
        assertFalse(varExp.isTypeAssignableFrom(varNum));
        assertTrue(varExp.isTypeAssignableFrom(pred));
    }

    @Test
    public void testPredicate() {
        Variable aVar = parser.parseVariable("A");
        assertTrue(Predicate.isPredicate(aVar));
    }

    @Test
    public void testBinding() {
        Variable numVar = parser.parseVariable("x");
        assertFalse(numVar.isBound());

        Predicate predicate = parser.parsePredicate("x = 4");
        numVar.bindTo(predicate);
        assertTrue(numVar.isBound());
        assertEquals(numVar.getBinding(), predicate);
        numVar.bindTo(null);
        assertFalse(numVar.isBound());

        Variable predVar = parser.parseVariable("P");
        assertFalse(predVar.isBound());

        predicate = parser.parsePredicate("P -> Q");
        predVar.bindTo(predicate);
        assertTrue(predVar.isBound());
        assertEquals(predVar.getBinding(), predicate);
        predVar.bindTo(null);
        assertFalse(predVar.isBound());
    }
}
