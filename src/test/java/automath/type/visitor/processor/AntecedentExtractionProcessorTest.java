package automath.type.visitor.processor;

import automath.BaseTest;
import automath.type.Predicate;
import automath.type.visitor.ExpressionVisitor;
import automath.type.visitor.processor.AntecedentExtractionProcessor;
import automath.util.Mappable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@RunWith(JUnit4.class)
public class AntecedentExtractionProcessorTest extends BaseTest {
    @Test
    public void nestedAntecedentTest() {
        Predicate predicate = parser.parsePredicate("(A->B) & (B->C) -> (A->C)");
        AntecedentExtractionProcessor processor = new AntecedentExtractionProcessor();
        ExpressionVisitor visitor = new ExpressionVisitor(processor);
        visitor.visit(predicate);

        assertHasMappable(processor.getAntecedents(), parser.parsePredicate("A"));
        assertHasMappable(processor.getAntecedents(), parser.parsePredicate("B"));
        assertHasMappable(processor.getAntecedents(), parser.parsePredicate("(A->B) & (B->C)"));
    }
}
