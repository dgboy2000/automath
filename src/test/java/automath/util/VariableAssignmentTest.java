package automath.util;

import automath.BaseTest;
import automath.parser.FirstParser;
import automath.type.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Tests the VariableAssignment
 */
@RunWith(JUnit4.class)
public class VariableAssignmentTest extends BaseTest {
    @Test
    public void applyTest() {
        VariableAssignment variableAssignment = new VariableAssignment();

        Variable xVar = parser.parseVariable("x");
        NaturalNumber num = new NaturalNumber("4");
        Variable aVar = parser.parseVariable("a");
        Variable bVar = parser.parseVariable("b");

        variableAssignment.put(aVar, xVar);
        variableAssignment.put(bVar, num);

        assertTrue(variableAssignment.containsKey(aVar));
        assertTrue(variableAssignment.containsKey(new Variable("a")));
        assertTrue(variableAssignment.containsKey(new Variable("b")));
        assertFalse(variableAssignment.containsKey(xVar));

        Predicate equation = new Predicate(aVar, Operator.EQUALS, bVar);
        Predicate expectedResult = new Predicate(xVar, Operator.EQUALS, num);

        assertEquals(expectedResult, variableAssignment.applyTo(equation));
    }

    @Test
    public void intersectingNamesTest() {
        // TODO: functionality for post-variable-assignment rewrite of consequent
        // to make sure we don't inadvertently combine variables that should be separate

        Variable aVar = parser.parseVariable("a");
        Variable bVar = parser.parseVariable("b");
        Variable cVar = parser.parseVariable("c");
        Variable dVar = parser.parseVariable("d");

        VariableAssignment firstAssignment = new VariableAssignment();
        firstAssignment.put(aVar, bVar);
        VariableAssignment secondAssignment = new VariableAssignment();
        secondAssignment.put(cVar, dVar);
        VariableAssignment conflictAssignment = new VariableAssignment();
        conflictAssignment.put(aVar, dVar);
        VariableAssignment overloadingAssignment = new VariableAssignment();
        overloadingAssignment.put(cVar, bVar);

        assertEquals(1, firstAssignment.intersect(firstAssignment).size());
        assertEquals(2, firstAssignment.intersect(secondAssignment).size());
        assertNull(firstAssignment.intersect(conflictAssignment));
        assertEquals(2, firstAssignment.intersect(overloadingAssignment).size());

        // TODO: Functionality for post-merge variable rewrite to avoid binding distinct variables
    }
}
