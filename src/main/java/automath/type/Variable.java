package automath.type;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Variable extends BaseType {
    private boolean isBound = false; // Whether variable is bound in it's current context
    private Class type = Type.class; // Class of objects over which the variable ranges

    public Variable(Variable var) {
        super(var.getName());
        this.type = var.type;
        this.isBound = var.isBound;
    }
    public Variable(String name) {
        super(name);
    }
    public Variable(String name, Class type) {
        super(name);
        this.type = type;
    }

    public boolean isTypeAssignableFrom(Type otherType) {
        Class otherClass = (otherType instanceof Variable) ? ((Variable) otherType).type : otherType.getClass();
        return type.isAssignableFrom(otherClass);
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
