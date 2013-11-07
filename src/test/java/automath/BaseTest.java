package automath;

import automath.parser.FirstParser;
import automath.inference.KnowledgeCorpus;
import automath.type.Expression;
import automath.util.Mappable;
import org.hamcrest.Matcher;

import java.util.Collection;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class BaseTest {
    protected FirstParser parser = new FirstParser();
    protected KnowledgeCorpus corpus;

    protected <T extends Expression> void assertHasMappable(Collection<Mappable<T>> collection, T expression) {
        assertThat(collection, hasItem(new Mappable<T>(expression)));
    }
}
