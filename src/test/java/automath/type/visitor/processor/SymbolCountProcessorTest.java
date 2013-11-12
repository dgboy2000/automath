package automath.type.visitor.processor;

import automath.BaseTest;
import automath.type.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SymbolCountProcessorTest extends BaseTest {
    private final static SymbolCountProcessor withAssumptions = new SymbolCountProcessor(true);
    private final static SymbolCountProcessor withoutAssumptions = new SymbolCountProcessor(false);

    @Test
    public void symbolCountTest() {
        Assert.assertEquals(withAssumptions.countSymbolsIn(parser.parsePredicate("A&B -> B&A")), 7);
        assertEquals(withAssumptions.countSymbolsIn(parser.parsePredicate("A|~A")), 4);
        assertEquals(withAssumptions.countSymbolsIn(parser.parsePredicate("x=2+2")), 5);
    }

    @Test
    public void testWithAssumptions() {
        Predicate aPred = parser.parsePredicate("A|~A");
        Predicate bPred = parser.parsePredicate("A->B");
        int aCount = withoutAssumptions.countSymbolsIn(aPred);
        int bCount = withoutAssumptions.countSymbolsIn(bPred);

        bPred.addAssumption(aPred);
        assertEquals(withAssumptions.countSymbolsIn(bPred), aCount+bCount+1);

        aPred.addAssumption(aPred);
        assertEquals(withAssumptions.countSymbolsIn(aPred), aCount + 1);

        assertEquals(withoutAssumptions.countSymbolsIn(aPred), 4);
        assertEquals(withoutAssumptions.countSymbolsIn(bPred), 3);
    }
}
