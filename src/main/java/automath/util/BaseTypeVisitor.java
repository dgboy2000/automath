package automath.util;

import automath.type.BaseType;
import automath.type.Type;

import java.util.HashSet;
import java.util.Set;

/**
 * Visitor that can visit cyclic graphs but only look at each node once
 */
public abstract class BaseTypeVisitor {
    protected final Set<String> observedIds = new HashSet<String>();

    protected boolean isFirstTimeSeeing(Type type) {
        if (!(type instanceof BaseType)) return true;
        BaseType baseType = (BaseType) type;

        if (observedIds.contains(baseType.getId())) return false;
        observedIds.add(baseType.getId());

        return true;
    }
}
