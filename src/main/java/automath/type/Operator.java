package automath.type;

import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
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

    // LOGIC
    AND(2, Predicate.class,"&") {
        @Override
        public boolean apply(boolean... arguments) {
            return arguments[0] && arguments[1];
        }
    },
    IMPLIES(2, Predicate.class, "->") {
        @Override
        public boolean apply(boolean... arguments) {
            return !arguments[0] || arguments[1];
        }
    },
    NOT(1, Predicate.class, "~"), // TODO: ~P should work for P a variable.
    OR(2, Predicate.class, "|") {
        @Override
        public boolean apply(boolean... arguments) {
            return arguments[0] || arguments[1];
        }
    },

    // PREDICATE
    EQUALS(2, BaseType.class, "="),

    // ARITHMETIC
    EXP(2, BaseType.class, "^"),
    MULT(2, BaseType.class, "*"),
    DIV(2, BaseType.class, "/"),
    PLUS(2, BaseType.class, "+"),
    MINUS(2, BaseType.class, "-")
    ;

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
    public boolean isBooleanOperator() { return this == IMPLIES || this == AND || this == OR; }

    // TODO: change this logic to allow short-circuiting
    public boolean apply(boolean... arguments) { throw new RuntimeException("Non-boolean operator: "+this); }
}
