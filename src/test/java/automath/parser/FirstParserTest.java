package automath.parser;

import automath.BaseTest;
import automath.antlr.firstgrammar.*;
import automath.type.Expression;
import automath.type.Predicate;
import automath.type.Theorem;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FirstParserTest extends BaseTest {
    @Test
    public void basicParseTest() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream("x=4".getBytes()));
        FirstGrammarLexer lexer = new FirstGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FirstGrammarParser parser = new FirstGrammarParser(tokens);
        ParseTree tree = parser.predicate();
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }

    @Test
    public void basicVisitorTest() throws IOException {
        Predicate predicate = parser.parsePredicate("x = 4");
        assertEquals(predicate.toString(), "x=4");
    }

    @Test
    public void compoundPredicateTest() {
        Predicate predicate = parser.parsePredicate("x = y & y = z");
        assertEquals(predicate.toString(), "x=y&y=z");
    }

    @Test
    public void arithmeticTest() {
        String formula = "x+y*z";
        Expression expression = parser.parseExpression(formula);
        assertEquals(expression.toString(), formula);

        // TODO: printing arithmetic expressions should take into account operator precedence.
        // Can accomplish this by printing parentheses around expressions based on the operator
        // Operators then need to know their precedence
        formula = "(x+y)*z";
        expression = parser.parseExpression(formula);
        assertTrue(expression.toString().length() >= 5);
    }

    @Test
    public void theoremTest() {
        String theoremStr = "x=y&y=z -> x=z";
        Predicate theoremPred = new Theorem(parser.parsePredicate(theoremStr));
        assertTrue(theoremPred instanceof Theorem);
    }

    @Test
    public void fileTest() {
        String th1 = "a=b -> b=a";
        String th2 = "x=y & y=z -> x=z";
        String knowledgeFile = new StringBuilder(th1+"\n")
                .append(th2+"\n")
                .toString();
        corpus = parser.parseFile(knowledgeFile);
        assertEquals(corpus.size(), 2);
        assertTrue(corpus.isKnown(new Theorem(parser.parsePredicate(th1))));
        assertTrue(corpus.isKnown(new Theorem(parser.parsePredicate(th2))));
    }
}
