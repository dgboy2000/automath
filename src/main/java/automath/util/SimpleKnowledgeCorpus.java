package automath.util;

import automath.type.Operator;
import automath.type.Predicate;
import automath.type.Theorem;

import java.util.*;

/**
 * Simple, dumb implementation of the interface
 */
public class SimpleKnowledgeCorpus implements KnowledgeCorpus {
    private final List<Predicate> facts = new ArrayList<Predicate>(); // Facilitates ordering

    // Facilitates fast lookup and stores back-tracking to recreate a proof
    private final Map<Predicate, Inference> factToInference = new HashMap<Predicate, Inference>();

    @Override
    public boolean addInferenceIfNew(Inference inference) {
        Predicate result = inference.result;

        // Convert to theorem if result is a theorem
        if (result.getChildren().size() == 3
                && result.getChild(1) == Operator.IMPLIES)
            result = new Theorem(result);


        if (factToInference.containsKey(result)) return false;
        facts.add(result);
        factToInference.put(result, inference);
        return true;
    }

    @Override
    public boolean isKnown(Predicate predicate) {
        for (Predicate fact : facts) if (fact.isAssignableFrom(predicate)) return true;
        return false;
    }

    @Override
    public Predicate get(int i) { return facts.get(i); }

    @Override
    public int size() { return facts.size(); }

    @Override
    public List<VariableAssignment> getAssignmentsOf(Predicate predicate) {
        if (predicate.isCompoundPredicate()) {
            List<VariableAssignment> jointAssignments = new ArrayList<VariableAssignment>();
            List<VariableAssignment> lhsAssignments = getAssignmentsOf(predicate.getLhs());
            List<VariableAssignment> rhsAssignments = getAssignmentsOf(predicate.getRhs());
            for (VariableAssignment lhs : lhsAssignments) {
                for (VariableAssignment rhs : rhsAssignments) {
                    VariableAssignment jointAssignment = lhs.intersect(rhs);
                    if (null != jointAssignment) jointAssignments.add(jointAssignment);
                }
            }
            return jointAssignments;
        }

        List<VariableAssignment> matchingAssignments = new ArrayList<VariableAssignment>();
        for (Predicate fact : facts) {
            VariableAssignment assignment = predicate.getVariableAssignmentTo(fact);
            if (null != assignment) matchingAssignments.add(assignment);
        }
        return matchingAssignments;
    }

    @Override
    public List<Inference> getLegalInferences(Theorem theorem) {
        Predicate antecedent = theorem.getAntecedent();
        if (antecedent.isCompoundPredicate()) { // Process compound predicates recursively
            List<Inference> legalInferences = new ArrayList<Inference>();

            Theorem lhsTheorem = new Theorem(antecedent.getLhs(), Predicate.EMPTY);
            Theorem rhsTheorem = new Theorem(antecedent.getRhs(), Predicate.EMPTY);

            List<Inference> lhsAssignments = getLegalInferences(lhsTheorem);
            List<Inference> rhsAssignments = getLegalInferences(rhsTheorem);
            for (Inference lhs : lhsAssignments) {
                for (Inference rhs : rhsAssignments) {

                    Inference jointAssignment = new Inference();
                    jointAssignment.variableAssignment = lhs.variableAssignment.intersect(rhs.variableAssignment);
                    if (jointAssignment.variableAssignment != null) {
                        jointAssignment.precedents.addAll(lhs.precedents);
                        jointAssignment.precedents.addAll(rhs.precedents);
                        legalInferences.add(jointAssignment);
                    }
                }
            }

            // Apply the variable assignments if this is the top-level theorem
            if (theorem.getConsequent() != Predicate.EMPTY) {
                for (Inference inference : legalInferences) {
                    inference.theorem = theorem;
                    inference.result = theorem.apply(inference.variableAssignment);
                }
            }

            return legalInferences;
        }

        // Get variable legal variable assignments
        List<Inference> legalInferences = new ArrayList<Inference>();
        for (Predicate fact : facts) {
            Inference inference = new Inference();
            inference.variableAssignment = antecedent.getVariableAssignmentTo(fact);
            if (null != inference.variableAssignment) {
                inference.precedents.add(fact);
                legalInferences.add(inference);
            }
        }

        // Apply the variable assignments if this is the top-level theorem
        if (theorem.getConsequent() != Predicate.EMPTY) {
            for (Inference inference : legalInferences) {
                inference.theorem = theorem;
                inference.result = theorem.apply(inference.variableAssignment);
            }
        }

        return legalInferences;
    }

    @Override
    public boolean addAxiomIfNew(Predicate axiom) {
        return addInferenceIfNew(Inference.axiom(axiom));
    }

    @Override
    public SimpleKnowledgeCorpus clone() {
        SimpleKnowledgeCorpus newCorpus = new SimpleKnowledgeCorpus();
        newCorpus.facts.addAll(this.facts);
        newCorpus.factToInference.putAll(this.factToInference);
        return newCorpus;
    }
}
