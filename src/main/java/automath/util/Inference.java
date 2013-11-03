package automath.util;

import automath.type.Predicate;
import automath.type.Theorem;

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
        inference.theorem = new Theorem(Predicate.EMPTY, inference.result);

        return inference;
    }
}
