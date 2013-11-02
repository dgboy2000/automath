package automath.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Test the Theorem type
 */
@RunWith(JUnit4.class)
public class TheoremTest {
    @Test
    public void simpleApplicationTest() {
        Variable var = new Variable("x");
        NaturalNumber num = new NaturalNumber("4");
        Predicate equation = new Predicate(var, Operator.EQUALS, num);
        Predicate flippedEquation = new Predicate(num, Operator.EQUALS, var);

        Variable aVar = new Variable("a");
        Variable bVar = new Variable("b");
        Predicate antecedent = new Predicate(aVar, Operator.EQUALS, bVar);
        Predicate consequent = new Predicate(bVar, Operator.EQUALS, aVar);

        Theorem theorem = new Theorem(antecedent, consequent);
        assertTrue(theorem.isApplicable(equation));

        Predicate result = theorem.apply(equation);
        assertTrue(flippedEquation.equals(result));
    }
}
