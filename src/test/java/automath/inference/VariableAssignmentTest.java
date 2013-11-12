package automath.inference;

import automath.BaseTest;
import automath.inference.VariableAssignment;
import automath.type.*;
import automath.type.visitor.processor.VariableBindingCheckerProcessor;
import automath.util.Mappable;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests the VariableAssignment
 */
@RunWith(JUnit4.class)
public class VariableAssignmentTest extends BaseTest {
    @Before
    public void init() {
        corpus = new SimpleKnowledgeCorpus();
    }

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
        assertTrue(variableAssignment.containsKey(parser.parseVariable("a")));
        assertTrue(variableAssignment.containsKey(parser.parseVariable("b")));
        assertFalse(variableAssignment.containsKey(xVar));

        Predicate equation = new Predicate(aVar, Operator.EQUALS, bVar);
        Predicate expectedResult = new Predicate(xVar, Operator.EQUALS, num);

        assertEquals(expectedResult, variableAssignment.applyTo(equation));
    }

    @Test
    public void intersectingNamesTest() {
        // TODO: functionality for post-variable-assignment rewrite of consequent
        // to make sure we don't inadvertently combine variables that should be separate
        Predicate binding = parser.parsePredicate("a+b=c+d");
        binding.setLabel("1");

        Variable aVar = parser.parseVariable("a");
        Variable bVar = parser.parseVariable("b");
        Variable cVar = parser.parseVariable("c");
        Variable dVar = parser.parseVariable("d");
        bVar.bindTo(binding);
        dVar.bindTo(binding);

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

    @Test
    public void testBinding() {
        Predicate binding = parser.parsePredicate("x=4");
        Variable xVar = (Variable) binding.getChild(0);
        xVar.bindTo(binding);
        NaturalNumber num = (NaturalNumber) binding.getChild(2);

        Theorem theorem = new Theorem(parser.parsePredicate("a=b -> b=a"));
        Variable aVar = (Variable) theorem.getAntecedent().getChild(0);
        Variable bVar = (Variable) theorem.getAntecedent().getChild(2);

        VariableAssignment variableAssignment = theorem.getAntecedent().getVariableAssignmentTo(binding);
        assertEquals(((Variable) variableAssignment.get(aVar)).getBinding(), binding);

        Predicate result = theorem.apply(variableAssignment);
        Predicate expectedResult = new Predicate(num, Operator.EQUALS, xVar);

        assertEquals(result, expectedResult);
        assertEquals(((Variable) result.getChild(2)).getBinding(), binding);
    }

    @Test
    public void intersectBugTest() {
        Variable aVar = parser.parseVariable("A");
        Variable bVar = parser.parseVariable("B");
        Predicate dontcare = parser.parsePredicate("A|~A");

        VariableAssignment firstAssignment = new VariableAssignment();
        firstAssignment.put(aVar, dontcare);
        VariableAssignment secondAssignment = new VariableAssignment();
        secondAssignment.put(bVar, dontcare);
        VariableAssignment intersection = firstAssignment.intersect(secondAssignment);

        assertEquals(2, intersection.size());
    }

    @Test
    public void testOverlappingVariablesInTarget() {
        Theorem theorem = parser.parseTheorem("A&B -> B&A");

        PredicateVariable bound = (PredicateVariable) parser.parseVariable("D");
        bound.bindTo(bound);
        Predicate unbound = parser.parsePredicate("C&D");
        corpus.addAxiomIfNew(bound);
        corpus.addAxiomIfNew(unbound);

        boolean foundDifferentTargets = false;
        List<Inference> inferences = corpus.getLegalInferences(theorem);
        for (Inference inference : inferences) {
            Collection<Type> assignmentTargets = inference.variableAssignment.values();
            if (assignmentTargets.contains(bound) && assignmentTargets.contains(unbound)) {
                foundDifferentTargets = true;
                Map<String, Predicate> varNameToBinding = VariableBindingCheckerProcessor.getBindingsIn(inference.result);
                assertEquals(varNameToBinding.size(), 3); // The unbound 'D' should have been rewritten to another name
            }
        }
        assertTrue(foundDifferentTargets);
    }

    @Test
    public void testBug1() {
        Predicate precedent1 = parser.parsePredicate("A&B -> A");
        Predicate precedent2 = parser.parsePredicate("(A->B)&(B->C) -> (A->B)&(B->C)");
        Theorem theorem = parser.parseTheorem("(A->B)&(B->C) -> (A->B)");
        corpus.addAxiomIfNew(precedent1);
        corpus.addAxiomIfNew(precedent2);
        List<Inference> inferences = corpus.getLegalInferences(theorem);

        Predicate invalidResult = parser.parsePredicate("A&B -> ((A->B)&(B->C))");
        List<Mappable<Predicate>> results = new ArrayList<Mappable<Predicate>>();
        for (Inference inference : inferences) {
            results.add(new Mappable(inference.result));
            System.out.println(inference.toString());
        }
        assertFalse(results.contains(invalidResult));
    }

    @Test
    public void conflictingBindingsTest() {
        Predicate precedent1 = parser.parsePredicate("A|B").asAssumption();
        Predicate precedent2 = parser.parsePredicate("B->C").asAssumption();
        corpus.addAxiomIfNew(precedent1);
        corpus.addAxiomIfNew(precedent2);

        Theorem theorem = parser.parseTheorem("(A|B)&(B->C) -> (~A->C)");

        // There should be no legal inferences since the bindings of A and B conflict
        List<Inference> inferences = corpus.getLegalInferences(theorem);
        assertEquals(inferences.size(), 0);
    }
}
