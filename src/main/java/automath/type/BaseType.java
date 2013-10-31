package automath.type;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class BaseType {
    private final String name;

    public BaseType() { name = "NONAME"; }
    public BaseType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
