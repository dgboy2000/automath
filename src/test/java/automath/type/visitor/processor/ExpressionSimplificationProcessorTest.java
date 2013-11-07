package automath.type.visitor.processor;

import automath.BaseTest;
import automath.type.Predicate;
import automath.type.visitor.processor.ExpressionSimplificationProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ExpressionSimplificationProcessorTest extends BaseTest {
    @Test
    public void testDoubleNegation() {
        Predicate doubleNeg = parser.parsePredicate("~~~B");
        Predicate dummyAssumption = parser.parsePredicate("x=4");
        dummyAssumption.setLabel("label");

        doubleNeg.getAssumptions().add(dummyAssumption);
        ExpressionSimplificationProcessor.process(doubleNeg);
        Predicate expected = parser.parsePredicate("~B");
        expected.getAssumptions().add(dummyAssumption);
        assertEquals(doubleNeg, expected);
        assertEquals(doubleNeg.getAssumptions().size(), 1);
        assertEquals(doubleNeg.getAssumptions().get(0), dummyAssumption);

        doubleNeg = parser.parsePredicate("~~~(~~A & ~~B)");
        ExpressionSimplificationProcessor.process(doubleNeg);
        assertEquals(doubleNeg, parser.parsePredicate("~(~A&~B)"));
    }
}
