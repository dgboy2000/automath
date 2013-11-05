package automath.util;

import automath.BaseTest;
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
}
