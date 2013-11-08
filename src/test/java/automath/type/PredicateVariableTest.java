package automath.type;


import automath.BaseTest;
import automath.inference.Inference;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class PredicateVariableTest extends BaseTest {
    @Test
    public void testAssumptionEquals() {
        PredicateVariable assumption1 = (PredicateVariable) parser.parseVariable("A");
        assumption1.addAssumption(assumption1);
        assumption1.bindTo(assumption1);

        PredicateVariable assumption2 = (PredicateVariable) parser.parseVariable("A");
        assumption2.addAssumption(assumption2);
        assumption2.bindTo(assumption2);

        assertEquals(assumption1.hashCode(), assumption2.hashCode());
        assertEquals(assumption1, assumption2);
    }

    @Test
    public void testBoundInPredicate() {
        Predicate assumption1 = Inference.assumption(parser.parsePredicate("(A->B)&(B->C)")).result;
        Predicate assumption2 = Inference.assumption(parser.parsePredicate("(A->B)&(B->C)")).result;
        assertEquals(assumption1.hashCode(), assumption2.hashCode());
        assertEquals(assumption1, assumption2);
    }
}
