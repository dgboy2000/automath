package automath.type.visitor;

import automath.type.BaseType;
import automath.type.Type;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Visitor that can visit cyclic graphs but only look at each node once
 */
public abstract class BaseTypeVisitor {
    protected final Map<String, Integer> idToVisitOrder = new HashMap<String, Integer>();

    public int getVisitIndexOf(BaseType baseType) {
        return idToVisitOrder.get(baseType.getId());
    }

    protected boolean isFirstTimeSeeing(Type type) {
        if (!(type instanceof BaseType)) return true;
        BaseType baseType = (BaseType) type;

        if (idToVisitOrder.containsKey(baseType.getId())) return false;
        idToVisitOrder.put(baseType.getId(), nextNodeCount());

        return true;
    }

    private Integer nextNodeCount() {
        return idToVisitOrder.size() + 1;
    }
}
