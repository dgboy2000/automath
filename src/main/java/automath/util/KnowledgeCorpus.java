package automath.util;

import automath.type.Predicate;

import java.util.List;

/**
 * Corpus of known statements
 */
public interface KnowledgeCorpus {
    public List<Predicate> getInstancesOf(Predicate predicate); // Find all predicates which are an instance of the specified rule
}
