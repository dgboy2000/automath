package automath.type;

import automath.util.ExpressionAlignmentVisitor;
import automath.util.ExpressionHashProcessor;
import automath.util.VariableAssignment;
import automath.util.VariableAssignmentProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * One or more symbols of any kind that combine to produce a value
 */
public class Expression extends BaseType {
    private final static boolean PRINT_PARENTHESES = true;

    private final List<Type> children = new ArrayList<Type>();
    public List<Type> getChildren() { return children; }
    public Type getChild(int i) { return children.get(i); }
    public void setChild(int i, Type type) { children.set(i, type); }

    public Expression() {}
    public Expression(Type... children) {
        this.getChildren().addAll(Arrays.asList(children));
    }

    public boolean isAssignableFrom(Type otherType) {
        return new ExpressionAlignmentVisitor(new VariableAssignmentProcessor()).visit(this, otherType);
    }
    public VariableAssignment getVariableAssignmentTo(Type otherType) {
        VariableAssignmentProcessor processor = new VariableAssignmentProcessor();
        if (!new ExpressionAlignmentVisitor(processor).visit(this, otherType)) return null;
        return processor.getVariableAssignment();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof Expression)) return false;
        Expression otherExpression = (Expression) otherObject;

        // TODO: inefficient; implement a second tree walker
        return this.isAssignableFrom(otherExpression) && otherExpression.isAssignableFrom(this);
    }

    @Override
    public Expression clone() {
        Expression copy;
        try { copy = getClass().getConstructor().newInstance(); }
        catch (Exception e) { throw new RuntimeException(e); }

        copy.getChildren().clear();
        for (Type child : children) {
            Type newChild;
            if (child instanceof BaseType) newChild = ((BaseType) child).clone();
            else if (child instanceof Operator) newChild = child;
            else throw new RuntimeException("Unhandled type in clone: "+child.getClass().getName());
            copy.getChildren().add(newChild);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (PRINT_PARENTHESES) stringBuilder.append(" (");
        for (Type child : children) stringBuilder.append(child.toString());
        if (PRINT_PARENTHESES) stringBuilder.append(") ");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return ExpressionHashProcessor.hash(this);
    }
}
