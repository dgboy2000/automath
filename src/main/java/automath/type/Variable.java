package automath.type;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */

// TODO: variables that appear in known equations are bound, not free
public interface Variable extends Type, Cloneable {
    public Predicate getBinding(); // Return the predicate that binds this variable
    public void bindTo(Predicate predicate); // Announce that variable is bound by this predicate
    public boolean isBound(); // Whether variable is bound in it's current context

    public String getId();
    public Class getType(); // Class of objects over which the variable ranges
    public boolean isTypeAssignableFrom(Type otherType);
    public int hashCode();
    public boolean equals(Object otherObject);
    public Variable clone();
}
