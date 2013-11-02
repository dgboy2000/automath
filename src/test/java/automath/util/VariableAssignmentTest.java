package automath.util;

import automath.type.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the VariableAssignment
 */
@RunWith(JUnit4.class)
public class VariableAssignmentTest {
    @Test
    public void applyTest() {
        VariableAssignment variableAssignment = new VariableAssignment();

        Variable xVar = new Variable("x", NaturalNumber.class);
        NaturalNumber num = new NaturalNumber("4");
        Variable aVar = new Variable("a", NaturalNumber.class);
        Variable bVar = new Variable("b", NaturalNumber.class);

        variableAssignment.put(aVar, xVar);
        variableAssignment.put(bVar, num);

        Predicate equation = new Predicate(aVar, Operator.EQUALS, bVar);
        Predicate expectedResult = new Predicate(xVar, Operator.EQUALS, num);

        assertEquals(expectedResult, variableAssignment.applyTo(equation));
    }
}
