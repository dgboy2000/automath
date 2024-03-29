package automath.type;

import automath.type.visitor.ExpressionAlignmentVisitor;
import automath.type.visitor.processor.ExpressionHashProcessor;
import automath.inference.VariableAssignment;
import automath.type.visitor.processor.VariableAssignmentProcessor;
import automath.util.Mappable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * One or more symbols of any kind that combine to produce a value
 */
public class Expression extends BaseType {
    private final static boolean PRINT_PARENTHESES = false;

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
        if (otherObject instanceof Mappable) return otherObject.equals(this);
        if (otherObject == null || this.getClass() != otherObject.getClass()) return false;
        Expression otherExpression = (Expression) otherObject;
        return this.getChildren().size() == otherExpression.getChildren().size();
    }

    @Override
    public int compareTo(Object otherObject) {
        if (otherObject instanceof Mappable) return -((Mappable) otherObject).compareTo(this);
        int comparison = super.compareTo(otherObject);
        if (comparison != 0) return comparison;
        return this.getChildren().size() - ((Expression) otherObject).getChildren().size();
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
        if (PRINT_PARENTHESES) stringBuilder.append("(");
        for (Type child : children) stringBuilder.append(child.toString());
        if (PRINT_PARENTHESES) stringBuilder.append(")");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return ExpressionHashProcessor.hash(this);
    }

    public void becomeCloneOf(Expression expression) {
        this.getChildren().clear();
        this.getChildren().addAll(expression.getChildren());
        this.setName(expression.getName());
    }
}
