package automath.type.visitor.processor;

import automath.type.Operator;
import automath.type.Predicate;
import automath.type.Type;
import automath.type.visitor.ExpressionVisitor;
import automath.util.Mappable;

import java.util.HashSet;
import java.util.Set;

/**
 * Save every antecedent in an implication clause.
 */
public class AntecedentExtractionProcessor implements ExpressionVisitor.Processor {
    private final Set<Mappable<Predicate>> antecedents = new HashSet<Mappable<Predicate>>();
    public Set<Mappable<Predicate>> getAntecedents() { return antecedents; }

    @Override
    public boolean process(Type type) {
        if (type instanceof Predicate) {
            Predicate predicate = (Predicate) type;
            if (predicate.getChildren().size() == 3 && predicate.getChild(1) == Operator.IMPLIES) {
                antecedents.add(new Mappable<Predicate>((Predicate) predicate.getChild(0)));
            }
        }

        return true;
    }
}
