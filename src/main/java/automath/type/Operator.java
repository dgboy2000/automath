package automath.type;

import sun.org.mozilla.javascript.internal.Undefined;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:49 AM
 * To change this template use File | Settings | File Templates.
 */
public enum Operator implements Type {

    // BINARY
    AND(2, Predicate.class,"&"),
    EQUALS(2, Expression.class, "="),
    IMPLIES(2, Predicate.class, "->"),
    OR(2, Predicate.class, "|");

    // Number of arguments
    public final int arity;
    public final Class operandType;
    public final String name;

    Operator(int arity, Class operandType, String name) {
        this.arity = arity;
        this.operandType = operandType;
        this.name = name;
    }

    public static Operator getEnum(String name)
    {
        // first, try using the internal method which is by name
        try {
            return Operator.valueOf(name);
        }
        catch (IllegalArgumentException iae) {
            for (Operator operatorType : Operator.values()){
                if (operatorType.name.equals(name)) return operatorType;
            }
        }

        throw new IllegalArgumentException();
    }

    public boolean isAssignableFrom(Type type) { return type instanceof Operator && this == type; }
    public String toString() { return name; }
}
