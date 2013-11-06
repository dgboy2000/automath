package automath.util;

import automath.BaseTest;
import automath.type.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class SymbolCountProcessorTest extends BaseTest {
    @Test
    public void symbolCountTest() {
        assertEquals(SymbolCountProcessor.countSymbolsIn(parser.parsePredicate("A&B -> B&A")), 7);
        assertEquals(SymbolCountProcessor.countSymbolsIn(parser.parsePredicate("A|~A")), 4);
        assertEquals(SymbolCountProcessor.countSymbolsIn(parser.parsePredicate("x=2+2")), 5);
    }

    @Test
    public void testWithAssumptions() {
        Predicate aPred = parser.parsePredicate("A|~A");
        Predicate bPred = parser.parsePredicate("A->B");
        int aCount = SymbolCountProcessor.countSymbolsIn(aPred);
        int bCount = SymbolCountProcessor.countSymbolsIn(bPred);

        bPred.getAssumptions().add(aPred);
        assertEquals(SymbolCountProcessor.countSymbolsIn(bPred), aCount+bCount+1);

        aPred.getAssumptions().add(aPred);
        assertEquals(SymbolCountProcessor.countSymbolsIn(aPred), 2*aCount + 1);
    }
}
