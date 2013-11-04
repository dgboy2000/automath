package automath.util;

import automath.type.Operator;
import automath.type.Predicate;
import automath.type.Type;

import java.util.HashSet;
import java.util.Set;

/**
 * Save every antecedent in an implication clause.
 */
public class AntecedentExtractionProcessor implements ExpressionVisitor.Processor {
    private final Set<Predicate> antecedents = new HashSet<Predicate>();
    public Set<Predicate> getAntecedents() { return antecedents; }

    @Override
    public boolean process(Type type) {
        if (type instanceof Predicate) {
            Predicate predicate = (Predicate) type;
            if (predicate.getChildren().size() == 3 && predicate.getChild(1) == Operator.IMPLIES) {
                antecedents.add((Predicate) predicate.getChild(0));
            }
        }

        return true;
    }
}
