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
    private Class type; // Class of objects over which the variable ranges

    public Variable(String name) {
        super(name);
    }
}
