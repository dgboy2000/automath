package automath.parser;

// import ANTLR's runtime libraries
import automath.antlr.*;
import automath.type.Operator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class AntlrParserTest {

    @Test
    public void basicParseTest() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream("x=4".getBytes()));
        TestGrammerLexer lexer = new TestGrammerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestGrammerParser parser = new TestGrammerParser(tokens);
        ParseTree tree = parser.init(); // begin parsing at init rule
        System.out.println(tree.toStringTree(parser)); // print LISP-style tree
    }

    @Test
    public void basicVisitorTest() throws IOException {
        Operator operator = (Operator) new AntlrParser().parse("x = 4");
        assertEquals(operator.toString(), "x=4");
    }
}
