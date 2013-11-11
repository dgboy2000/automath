package automath.util;

import automath.type.Expression;
import automath.type.visitor.processor.ExpressionComparisonProcessor;

import java.util.Comparator;

/**
 * Overrides the equals method, allowing storage in a hashmap
 */
public class Mappable<T extends Expression> implements Comparable {
    private final T rawObject;
    public T getRawObject() { return rawObject; }

    public Mappable(T rawObject) { this.rawObject = rawObject; }

    protected static class NoAssumptionComparator implements Comparator<Mappable> {
        @Override
        public int compare(Mappable mappableA, Mappable mappableB) {
            return new ExpressionComparisonProcessor(false).compare(mappableA.getRawObject(), mappableB.getRawObject());
        }
    }
    protected static class FullComparator implements Comparator<Mappable> {
        @Override
        public int compare(Mappable mappableA, Mappable mappableB) {
            return new ExpressionComparisonProcessor(true).compare(mappableA.getRawObject(), mappableB.getRawObject());
        }
    }

    public static Comparator<Mappable> NO_ASSUMPTION_COMPARATOR = new NoAssumptionComparator();
    public static Comparator<Mappable> FULL_COMPARATOR = new FullComparator();

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Mappable) {
            return new ExpressionComparisonProcessor(true).equal(rawObject, ((Mappable<T>) otherObject).getRawObject());
        } else {
            return new ExpressionComparisonProcessor(true).equal(rawObject, (T) otherObject);
        }
    }

    @Override
    public int hashCode() {
        return rawObject.hashCode();
    }

    @Override
    public int compareTo(Object otherObject) {
        if (otherObject instanceof Mappable) {
            return new ExpressionComparisonProcessor(true).compare(rawObject, ((Mappable<T>) otherObject).getRawObject());
        } else {
            return new ExpressionComparisonProcessor(true).compare(rawObject, (T) otherObject);
        }
    }
}
