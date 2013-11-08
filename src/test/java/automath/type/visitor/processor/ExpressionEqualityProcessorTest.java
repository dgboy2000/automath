package automath.type.visitor.processor;

import automath.BaseTest;
import automath.type.Predicate;
import automath.type.PredicateVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ExpressionEqualityProcessorTest extends BaseTest {
    @Test
    public void testParallelExpressions() {
        Predicate cycle1 = generateCyclicAssumptions();
        Predicate cycle2 = generateCyclicAssumptions();
        assertTrue(ExpressionEqualityProcessor.equal(cycle1, cycle2));
    }

    private Predicate generateCyclicAssumptions() {
        Predicate assumption1 = parser.parsePredicate("(A->B)&(B->C)").asAssumption();
        PredicateVariable assumption2 = (PredicateVariable) parser.parseVariable("A");
        assumption2.bindTo(assumption1);
        assumption2.addAssumption(assumption1);
        assumption2.addAssumption(assumption2);

        return assumption2;
    }
}
