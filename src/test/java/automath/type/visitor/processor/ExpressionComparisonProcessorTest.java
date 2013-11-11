package automath.type.visitor.processor;

import automath.BaseTest;
import automath.type.Predicate;
import automath.type.PredicateVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ExpressionComparisonProcessorTest extends BaseTest {
    @Test
    public void testParallelExpressions() {
        Predicate cycle1 = generateCyclicAssumptions();
        Predicate cycle2 = generateCyclicAssumptions();
        assertTrue(new ExpressionComparisonProcessor(true).equal(cycle1, cycle2));
    }

    @Test
    public void testAssumptionUniqueness() {
        Predicate derived1 = parser.parsePredicate("x=4");
        Predicate derived2 = parser.parsePredicate("x=4");
        assertTrue(new ExpressionComparisonProcessor(true).equal(derived1, derived2));

        derived1.addAssumption(generateCyclicAssumptions());
        assertFalse(new ExpressionComparisonProcessor(true).equal(derived1, derived2));

        derived2.addAssumption(generateCyclicAssumptions());
        assertTrue(new ExpressionComparisonProcessor(true).equal(derived1, derived2));

        derived2.addAssumption(generateCyclicAssumptions());
        assertTrue(new ExpressionComparisonProcessor(true).equal(derived1, derived2));
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
