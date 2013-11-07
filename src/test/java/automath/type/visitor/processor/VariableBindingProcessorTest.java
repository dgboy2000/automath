package automath.type.visitor.processor;


import automath.BaseTest;
import automath.type.Predicate;
import automath.type.Theorem;
import automath.type.Variable;
import automath.inference.Inference;
import automath.inference.SimpleKnowledgeCorpus;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class VariableBindingProcessorTest extends BaseTest {
    @Test
    public void unbindingTest() {
        corpus = new SimpleKnowledgeCorpus();
        Theorem theorem = new Theorem(parser.parsePredicate("a=b -> b=a"));
        corpus.addAxiomIfNew(theorem);

        // TODO: it isn't the assumption that binds this but the implied quantifier
        // ie we read this as "assume there exists x such that x=4",
        // but syntactically could also be "for every x, x is 4"
        Predicate assumption = parser.parsePredicate("x=4");
        assertNull(((Variable) assumption.getChild(0)).getBinding());
        corpus.addInferenceIfNew(Inference.assumption(assumption));
        assertEquals(corpus.get(1), ((Variable) corpus.get(1).getChild(0)).getBinding());

        Inference inference = corpus.getLegalInferences(theorem).get(0);
        corpus.addInferenceIfNew(inference);
        assumption = corpus.get(1);
        assertEquals(((Variable) corpus.get(2).getChild(2)).getBinding(), assumption);

        inference = Inference.reduction(corpus.get(2), assumption);
        corpus.addInferenceIfNew(inference);
        Predicate antecedent = (Predicate) corpus.get(3).getChild(0);
        Predicate consequent = (Predicate) corpus.get(3).getChild(2);
        assertNull(((Variable) antecedent.getChild(0)).getBinding());
        assertNull(((Variable) consequent.getChild(2)).getBinding());
    }
}
