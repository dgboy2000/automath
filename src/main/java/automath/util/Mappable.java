package automath.util;

import automath.type.Expression;
import automath.type.visitor.processor.ExpressionComparisonProcessor;

/**
 * Overrides the equals method, allowing storage in a hashmap
 */
public class Mappable<T extends Expression> implements Comparable {
    private final T rawObject;
    public T getRawObject() { return rawObject; }

    public Mappable(T rawObject) { this.rawObject = rawObject; }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Mappable) {
            return ExpressionComparisonProcessor.equal(rawObject, ((Mappable<T>) otherObject).getRawObject());
        } else {
            return ExpressionComparisonProcessor.equal(rawObject, (T) otherObject);
        }
    }

    @Override
    public int hashCode() {
        return rawObject.hashCode();
    }

    @Override
    public int compareTo(Object otherObject) {
        if (otherObject instanceof Mappable) {
            return ExpressionComparisonProcessor.compare(rawObject, ((Mappable<T>) otherObject).getRawObject());
        } else {
            return ExpressionComparisonProcessor.compare(rawObject, (T) otherObject);
        }
    }
}
