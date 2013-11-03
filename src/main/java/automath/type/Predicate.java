package automath.type;

import java.util.Arrays;

/**
 * Expression with a boolean value (true / false)
 */
public class Predicate extends Expression {
    public static Predicate EMPTY = new Predicate() {
        @Override
        public boolean isAssignableFrom(Type dontCare) {
            return true;
        }
    };

    public Predicate() {}

    public Predicate(Type... children) { super(children); }

    /*
     * Does this predicate consist of 2 predicates and the 'AND' operator?
     */
    public boolean isCompoundPredicate() {
        return getChildren().size() == 3 &&
                (getChild(0) instanceof Predicate) &&
                (getChild(1) instanceof Operator) &&
                getChild(1) == Operator.AND &&
                (getChild(2) instanceof Predicate);
    }
    public Predicate getLhs() { return (Predicate) getChild(0); }
    public Predicate getRhs() { return (Predicate) getChild(2); }
}
