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

    public Predicate(Type... children) {
        this.getChildren().addAll(Arrays.asList(children));
    }
}
