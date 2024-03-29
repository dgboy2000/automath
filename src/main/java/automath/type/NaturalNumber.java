package automath.type;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class NaturalNumber extends BaseType {
    private final long value;

    public NaturalNumber(NaturalNumber num) {
        super(num.getName());
        this.value = num.value;
    }
    public NaturalNumber(String name) {
        super(name);
        this.value = Long.valueOf(name);
    }

    @Override
    public boolean isAssignableFrom(Type type) {
        return (type instanceof NaturalNumber) && value == ((NaturalNumber) type).value;
    }

    @Override
    public boolean equals(Object otherObject) {
        return (otherObject instanceof NaturalNumber) &&
                this.getName().equals(((NaturalNumber) otherObject).getName());
    }

    @Override
    public int compareTo(Object otherObject) {
        int comparison = super.compareTo(otherObject);
        if (comparison != 0) return comparison;
        return this.getName().compareTo(((NaturalNumber) otherObject).getName());
    }
}
