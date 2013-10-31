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

// TODO: change this class to enum, change existing uses to Predicate
// TODO: operator should know the type of its arguments
public class Operator extends BaseType {
    public enum Type {

        // BINARY
        AND(2, 1, "&"),
        EQUALS(2, 1, "="),
        OR(2, 1, "|");

        // Number of arguments
        public final int arity;
        public final int displayPosition;
        public final String name;

        Type(int arity, int displayPosition, String name) {
            this.arity = arity;
            this.displayPosition = displayPosition;
            this.name = name;
        }

        public static Type getEnum(String name)
        {
            // first, try using the internal method which is by name
            try {
                return Type.valueOf(name);
            }
            catch (IllegalArgumentException iae) {
                for (Type operatorType : Type.values()){
                    if (operatorType.name.equals(name)) return operatorType;
                }
            }

            throw new IllegalArgumentException();
        }

    }

    private final Type type;

    private final List<BaseType> arguments;
    public List<BaseType> getArguments() { return arguments; }
    public void setArgument(int ind, BaseType arg) { arguments.set(ind, arg); }

    public Operator(String name) {
        super(name);
        this.type = Type.getEnum(name);
        this.arguments = new ArrayList<BaseType>(type.arity);
        for (int argInd = 0; argInd < type.arity; ++argInd) {
            this.arguments.add(new PlaceholderType());
        }
    }

    public Operator(String name, List<BaseType> arguments) {
        super(name);
        this.type = Type.getEnum(name);
        this.arguments = new ArrayList<BaseType>(type.arity);
        for (BaseType arg : arguments) {
            this.arguments.add(arg);
        }
    }

    public String toString() {
        StringBuilder repr = new StringBuilder();
        for (int argInd = 0; argInd < type.arity; ++argInd) {
            if (type.displayPosition == argInd) repr.append(super.toString());
            repr.append(arguments.get(argInd).toString());
        }
        if (type.displayPosition == type.arity) repr.append(super.toString());
        return repr.toString();
    }
}
