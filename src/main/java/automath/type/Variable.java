package automath.type;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */

// TODO: variables that appear in known equations are bound, not free
public class Variable extends BaseType {
    private boolean bound = false; // Whether variable is bound in it's current context
    public boolean isBound() { return bound; }

    private Class type = Type.class; // Class of objects over which the variable ranges
    public Class getType() { return type; }

    public Variable(Variable var) {
        super(var.getName());
        this.type = var.type;
        this.bound = var.bound;
    }
    public Variable(String name) {
        super(name);
        this.type = Character.isUpperCase(name.charAt(0)) ? Predicate.class : Expression.class;
    }
    public Variable(String name, Class type) {
        super(name);
        this.type = type;
    }

    public boolean isTypeAssignableFrom(Type otherType) {
        Class otherClass = (otherType instanceof Variable) ? ((Variable) otherType).type : otherType.getClass();
        // TODO: implement a real type-aware system.
        // This assumes that non-predicate expressions are natural numbers
        return type.isAssignableFrom(otherClass) |
                (type.isAssignableFrom(Expression.class) && NaturalNumber.class.isAssignableFrom(otherClass));
    }

    @Override
    public int hashCode() {
        int code = getName().hashCode();
        return code;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!otherObject.getClass().equals(getClass())) return false;
        Variable otherVariable = (Variable) otherObject;
        return getName().equals(otherVariable.getName()) &&
                this.type == otherVariable.type &&
                this.bound == otherVariable.bound;
    }
}
