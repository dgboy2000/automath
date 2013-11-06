package automath.type;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class BaseType implements Type, Cloneable {
    private String id;
    public String getId() { return id; }
    protected void setId(String id) { this.id = id; }

    private String name;
    public String getName() { return name; }
    protected void setName(String name) { this.name = name; }

    public BaseType() { this.id = UUID.randomUUID().toString(); }
    public BaseType(String name) {
        this();
        this.name = name;
    }

    @Override
    public boolean isAssignableFrom(Type type) {
        return this.equals(type);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public BaseType clone() {
        try { return getClass().getConstructor(getClass()).newInstance(this); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
}
