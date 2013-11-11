package automath.inference;


import automath.BaseTest;
import automath.inference.Inference;
import automath.inference.SimpleKnowledgeCorpus;
import automath.type.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(JUnit4.class)
public class InferenceTest extends BaseTest {
    @Test
    public void stringTest() {
        corpus = new SimpleKnowledgeCorpus();
        corpus.addAxiomIfNew(parser.parsePredicate("2+2=4"));

        Theorem theorem = new Theorem(parser.parsePredicate("a=b -> b=a"));
        theorem.setTheoremName("Reflexivity");
        assertEquals(corpus.getLegalInferences(theorem).size(), 1);

        Inference inference = corpus.getLegalInferences(theorem).get(0);
        assertThat(inference.toString(), containsString("4=2+2"));
        assertThat(inference.toString(), containsString(theorem.getName()));
    }
}
