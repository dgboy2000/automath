package automath.inference;

import automath.type.Predicate;
import automath.type.Theorem;
import automath.util.Mappable;
import org.antlr.v4.runtime.misc.OrderedHashSet;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a logical inference
 */
public class Inference {
    public final Set<Mappable<Predicate>> precedents = new HashSet<Mappable<Predicate>>(); // The previous facts we reasoned from
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

    /**
     * Return an assumption that incorporates the specified context assumptions and precedent
     * @param toAssume
     * @param context
     * @return
     */
    public static Inference assumption(Predicate toAssume, Predicate context) {
        Inference inference = Inference.assumption(toAssume);
        inference.result.getAssumptions().addAll(context.getAssumptions());
        inference.precedents.add(new Mappable<Predicate>(context));

        return inference;
    }

    public static Inference reduction(Predicate toReduce, Predicate assumption) {
        Inference inference = new Inference();
        inference.variableAssignment = new VariableAssignment();
        inference.result = toReduce.getReductionBy(assumption);
        inference.theorem = Theorem.REDUCTION;
        inference.precedents.add(new Mappable<Predicate>(assumption));
        inference.precedents.add(new Mappable<Predicate>(toReduce));

        return inference;
    }

    public String toString() {
        StringBuilder inferenceStringBuilder = new StringBuilder(result.toString());
        inferenceStringBuilder.append(StringUtils.repeat(" ", 40-inferenceStringBuilder.length()))
                .append(theorem.getName() == null ? "Theorem " + theorem.getLabel() : theorem.getName());

        if (precedents.size() > 0) {
            inferenceStringBuilder.append("(");
            Set<String> precendentLabels = new OrderedHashSet<String>();
            for (Mappable<Predicate> precedent : precedents) {
                precendentLabels.add(precedent.getRawObject().getLabel());
            }
            for (Mappable<Predicate> assumption : result.getAssumptions()) {
                precendentLabels.add(assumption.getRawObject().getLabel());
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
