package automath.util;

import automath.type.Predicate;
import automath.type.Theorem;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a logical inference
 */
public class Inference {
    public final Set<Predicate> precedents = new HashSet<Predicate>(); // The previous facts we reasoned from
    public VariableAssignment variableAssignment; // The variable assignment used in this inference
    public Theorem theorem; // The theorem we applied
    public Predicate result; // The result of inference

    public static Inference axiom(Predicate result) {
        Inference inference = new Inference();
        inference.variableAssignment = new VariableAssignment();
        inference.result = result;
        inference.theorem = Theorem.AXIOM;

        return inference;
    }

    public static Inference assumption(Predicate toAssume) {
        Inference inference = new Inference();
        inference.variableAssignment = new VariableAssignment();
        inference.result = toAssume.asAssumption();
        inference.theorem = Theorem.ASSUMPTION;

        return inference;
    }

    public static Inference reduction(Predicate toReduce, Predicate assumption) {
        Inference inference = new Inference();
        inference.variableAssignment = new VariableAssignment();
        inference.result = toReduce.getReductionBy(assumption);
        inference.theorem = Theorem.REDUCTION;
        inference.precedents.add(assumption);
        inference.precedents.add(toReduce);

        return inference;
    }

    public String toString() {
        StringBuilder inferenceStringBuilder = new StringBuilder(result.toString());
        inferenceStringBuilder.append(StringUtils.repeat(" ", 40-inferenceStringBuilder.length()))
                .append(theorem.getName() == null ? "Theorem " + theorem.getLabel() : theorem.getName());

        if (precedents.size() > 0) {
            inferenceStringBuilder.append("(");
            Set<String> precendentLabels = new OrderedHashSet<String>();
            for (Predicate precedent : precedents) {
                precendentLabels.add(precedent.getLabel());
            }
            for (Predicate assumption : result.getAssumptions()) {
                precendentLabels.add(assumption.getLabel());
            }
            inferenceStringBuilder.append(StringUtils.join(precendentLabels, ","));
            inferenceStringBuilder.append(")");
        }

        return inferenceStringBuilder.toString();
    }

    /**
     * Hash inference by its result and assumptions.
     * @return
     */
//    public int hashCode() {
//
//    }
//
//    public boolean equals()
}
