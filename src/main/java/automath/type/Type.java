package automath.type;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/31/13
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Type {
    public boolean isAssignableFrom(Type otherType); // True if this type can take on the value of otherType
    public boolean equals(Object other); // True if the expressions are identical
    public String toString();
    public String getName();
}
