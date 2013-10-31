package automath.type;

/**
 * Expression with a boolean value (true / false)
 */
public class Predicate extends Expression {
    public static Predicate EMPTY = new Predicate() {
        @Override
        public boolean matches(Predicate dontCare) {
            return true;
        }
    };

    public boolean matches(Predicate otherPredicate) {
        return false;
    }
}
