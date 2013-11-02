package automath.util;

import automath.type.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple, dumb implementation of the interface
 */
public class SimpleKnowledgeCorpus implements KnowledgeCorpus {
    private final List<Predicate> facts = new ArrayList<Predicate>();

    @Override
    public List<Predicate> getInstancesOf(Predicate predicate) {
        List<Predicate> matchingPredicates = new ArrayList<Predicate>();
        for (Predicate pred : facts)
            if (pred.isAssignableFrom(predicate))
                matchingPredicates.add(pred);
        return matchingPredicates;
    }
}
