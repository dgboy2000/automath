package automath.type.visitor.processor;

import automath.BaseTest;
import automath.type.Expression;
import automath.type.Predicate;
import automath.type.PredicateVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ExpressionHashProcessorTest extends BaseTest {
    @Test
    public void hashTest() {
        Expression expA = parser.parsePredicate("x=y");
        Expression expB = parser.parsePredicate("a=b");
        assertEquals(ExpressionHashProcessor.hash(expA), ExpressionHashProcessor.hash(expB));

        Expression expC = parser.parseExpression("x+y*z");
        assertNotSame(ExpressionHashProcessor.hash(expA), ExpressionHashProcessor.hash(expC));

        Expression expD = parser.parseExpression("x+(y*z)");
        assertEquals(ExpressionHashProcessor.hash(expC), ExpressionHashProcessor.hash(expD));

        Expression expE = parser.parseExpression("(x+y)*z");
        assertNotSame(ExpressionHashProcessor.hash(expD), ExpressionHashProcessor.hash(expE));
    }

    @Test
    public void testParallelExpressions() {
        Predicate cycle1 = generateCyclicAssumptions();
        Predicate cycle2 = generateCyclicAssumptions();
        assertEquals(ExpressionHashProcessor.hash(cycle1), ExpressionHashProcessor.hash(cycle2));
    }

    @Test
    public void testDifferentAssumptions() {
        Predicate cycle1 = generateCyclicAssumptions();
        Predicate cycle2 = generateCyclicAssumptions();
        cycle2.getAssumptions().clear();
        cycle2.addAssumption(cycle1);
        assertFalse(ExpressionComparisonProcessor.equal(cycle1, cycle2));
        assertFalse(ExpressionComparisonProcessor.equal(cycle2, cycle1));
    }

    @Test
    public void testDifferentBindings() {
        Predicate cycle1 = generateCyclicAssumptions();
        Predicate cycle2 = generateCyclicAssumptions();
        ((PredicateVariable) cycle2).bindTo(null);
        assertFalse(ExpressionComparisonProcessor.equal(cycle1, cycle2));
        assertFalse(ExpressionComparisonProcessor.equal(cycle2, cycle1));
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
