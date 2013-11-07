package automath.util;

import automath.type.Expression;
import automath.type.Predicate;
import automath.type.visitor.processor.ExpressionEqualityProcessor;

/**
 * Overrides the equals method, allowing storage in a hashmap
 */
public class Mappable<T extends Expression> {
    private final T rawObject;
    public T getRawObject() { return rawObject; }

    public Mappable(T rawObject) { this.rawObject = rawObject; }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Mappable) {
            return ExpressionEqualityProcessor.equal(rawObject, ((Mappable<T>) otherObject).getRawObject());
        } else {
            return ExpressionEqualityProcessor.equal(rawObject, (T) otherObject);
        }
    }

    @Override
    public int hashCode() {
        return rawObject.hashCode();
    }
}
