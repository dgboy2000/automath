package automath.inference;

import automath.type.Operator;
import automath.type.Predicate;
import automath.type.Theorem;
import automath.type.visitor.processor.ExpressionComparisonProcessor;
import automath.type.visitor.processor.ExpressionSimplificationProcessor;
import automath.util.AutomathLogger;
import automath.util.Mappable;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Simple, dumb implementation of the interface
 */
public class SimpleKnowledgeCorpus implements KnowledgeCorpus {
    private final List<Predicate> facts = new ArrayList<Predicate>(); // Facilitates ordering

    // Facilitates fast lookup and stores back-tracking to recreate a proof
    private final Map<Mappable, Inference> factToInference = new TreeMap<Mappable, Inference>(Mappable.FULL_COMPARATOR);
    private final Map<Mappable, Inference> uniqueFactToInference = new TreeMap<Mappable, Inference>(Mappable.NO_ASSUMPTION_COMPARATOR);

    @Override
    public boolean addInferenceIfNew(Inference inference) {
        if (inference.theorem == null) {
            throw new RuntimeException();
        }

        // Convert to theorem if result is a theorem
        if (inference.result.getChildren().size() == 3
                && inference.result.getChild(1) == Operator.IMPLIES)
            inference.result = new Theorem(inference.result);

        inference.result.setLabel(Integer.toString(size()+1));

        Mappable mappableResult = new Mappable(inference.result);
        if (uniqueFactToInference.containsKey(mappableResult)) {
            Inference oldInference = uniqueFactToInference.get(mappableResult);
            if (inference.result.getAssumptions().containsAll(oldInference.result.getAssumptions())) {
                // If we already knew this result but with the same or fewer assumptions, not new
                return false;
            }
        } else {
            assert(!factToInference.containsKey(mappableResult));
        }
        facts.add(inference.result);
        factToInference.put(mappableResult, inference);
        uniqueFactToInference.put(mappableResult, inference);
        return true;
    }

    @Override
    public boolean isKnown(Predicate predicate) {
        for (Predicate fact : facts) {
            if (fact.getAssumptions().size() > 0) continue;
            if (fact.isAssignableFrom(predicate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Predicate get(int i) { return facts.get(i); }

    @Override
    public Inference getInference(Predicate predicate) {
        return factToInference.get(new Mappable(predicate));
    }

    /**
     * TODO: Optimize this by caching theorems
     * @return
     */
    @Override
    public List<Theorem> getTheorems() {
        List<Theorem> knownTheorems = new ArrayList<Theorem>();
        for (Predicate fact : facts) {
            if (fact instanceof Theorem) knownTheorems.add((Theorem) fact);
        }
        return knownTheorems;
    }

    @Override
    public int size() { return facts.size(); }

//    @Override
//    public List<VariableAssignment> getAssignmentsOf(Predicate predicate) {
//        if (predicate.isCompoundPredicate()) {
//            List<VariableAssignment> jointAssignments = new ArrayList<VariableAssignment>();
//            List<VariableAssignment> lhsAssignments = getAssignmentsOf(predicate.getLhs());
//            List<VariableAssignment> rhsAssignments = getAssignmentsOf(predicate.getRhs());
//            for (VariableAssignment lhs : lhsAssignments) {
//                for (VariableAssignment rhs : rhsAssignments) {
//                    VariableAssignment jointAssignment = lhs.intersect(rhs);
//                    if (null != jointAssignment) jointAssignments.add(jointAssignment);
//                }
//            }
//            return jointAssignments;
//        }
//
//        List<VariableAssignment> matchingAssignments = new ArrayList<VariableAssignment>();
//        for (Predicate fact : facts) {
//            VariableAssignment assignment = predicate.getVariableAssignmentTo(fact);
//            if (null != assignment) matchingAssignments.add(assignment);
//        }
//        return matchingAssignments;
//    }

    @Override
    public List<Inference> getLegalInferences(Theorem theorem) {
        List<Inference> legalInferences = new ArrayList<Inference>();
        Predicate antecedent = theorem.getAntecedent();

        if (antecedent.isCompoundPredicate()) { // Process compound predicates recursively
            legalInferences.addAll(getLegalInferences_CompoundAntecedent(theorem));
            if (theorem.getConsequent() == Predicate.TRUE) return legalInferences; // If in a recursion, stop
        }

        if (theorem == Theorem.REDUCTION) { // Search for reductions
            return getLegalReductions();
        }

        // Get variable legal variable assignments for a normal theorem
        for (Predicate fact : facts) {
            Inference inference = new Inference();
            inference.variableAssignment = antecedent.getVariableAssignmentTo(fact);
            if (null != inference.variableAssignment) {
                inference.precedents.add(new Mappable(fact));
                legalInferences.add(inference);
            }
        }

        // Apply the variable assignments if this is the top-level theorem
        if (theorem.getConsequent() != Predicate.TRUE) {
            performInferencesWithTheorem(legalInferences, theorem);
        }

        return legalInferences;
    }

    /*
     * For a compound theorem, get the legal inferences of the 2 subparts and combine
     */
    private List<Inference> getLegalInferences_CompoundAntecedent(Theorem theorem) {
        List<Inference> legalInferences = new ArrayList<Inference>();

        Theorem lhsTheorem = new Theorem(theorem.getAntecedent().getLhs(), Predicate.TRUE);
        Theorem rhsTheorem = new Theorem(theorem.getAntecedent().getRhs(), Predicate.TRUE);

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
        if (theorem.getConsequent() != Predicate.TRUE) {
            performInferencesWithTheorem(legalInferences, theorem);
        }

        return legalInferences;
    }

    /**
     * Computes results of applying the theorem
     * @param inferences
     * @param theorem
     */
    private void performInferencesWithTheorem(List<Inference> inferences, Theorem theorem) {
        for (Inference inference : inferences) {
            inference.theorem = theorem;
            inference.result = theorem.apply(inference.variableAssignment);
            if (inference.result == null) {
                theorem.apply(inference.variableAssignment);
                throw new RuntimeException();
            }
            ExpressionSimplificationProcessor.process(inference.result);

            // We are assuming everything that was assumed by the precendents and the theorem
            inference.result.getAssumptions().addAll(theorem.getAssumptions());
            for (Mappable<Predicate> precedent : inference.precedents) {
                inference.result.getAssumptions().addAll(precedent.getRawObject().getAssumptions());
            }
        }
    }

    /**
     * Get a list of all reduction inferences that can be performed on the corpus.
     * @return
     */
    private List<Inference> getLegalReductions() {
        List<Inference> reductions = new ArrayList<Inference>();
        for (Inference prevInference : factToInference.values()) {
            for (Mappable<Predicate> assumption : prevInference.result.getAssumptions()) {
                reductions.add(Inference.reduction(prevInference.result, assumption.getRawObject()));
            }
        }
        return reductions;
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
        newCorpus.uniqueFactToInference.putAll(this.uniqueFactToInference);
        return newCorpus;
    }

    @Override
    public String toString() {
        List<String> knowledgeStrings = new ArrayList<String>();
        for (int i=0; i<size(); ++i) {
            Predicate fact = facts.get(i);
            knowledgeStrings.add(fact.getLabel()+": "+factToInference.get(new Mappable(fact)).toString());
        }
        return StringUtils.join(knowledgeStrings, "\n");
    }
}
