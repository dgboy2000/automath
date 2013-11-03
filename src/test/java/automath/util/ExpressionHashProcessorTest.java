package automath.util;

import automath.parser.FirstParser;
import automath.type.Expression;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ExpressionHashProcessorTest {
    @Test
    public void hashTest() {
        FirstParser parser = new FirstParser();

        Expression expA = parser.parsePredicate("x=y");
        Expression expB = parser.parsePredicate("a=b");
        assertEquals(ExpressionHashProcessor.hash(expA), ExpressionHashProcessor.hash(expB));

        Expression expC = parser.parseExpression("x+y*z");
        assertNotSame(ExpressionHashProcessor.hash(expA), ExpressionHashProcessor.hash(expC));

        Expression expD = parser.parseExpression("x+(y*z)");
        assertEquals(ExpressionHashProcessor.hash(expC), ExpressionHashProcessor.hash(expD));

        Expression expE = parser.parseExpression("(x+y)*z");
        assertNotSame(ExpressionHashProcessor.hash(expD), ExpressionHashProcessor.hash(expE));
    }
}
