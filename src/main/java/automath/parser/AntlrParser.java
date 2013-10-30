package automath.parser;

import automath.antlr.*;
import automath.type.BaseType;
import automath.type.Operator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dannygoodman
 * Date: 10/30/13
 * Time: 2:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class AntlrParser {

    private class AntlrVisitor extends TestGrammerBaseVisitor<BaseType> {
        @Override
        public Operator visitOperator(@NotNull TestGrammerParser.OperatorContext ctx) {
            return new Operator(ctx.getText());
        }

        @Override
        public Operator visitExpression(@NotNull TestGrammerParser.ExpressionContext ctx) {
            Operator operator = visitOperator(ctx.operator());
            operator.setArgument(0, visitTerm(ctx.term(0)));
            operator.setArgument(1, visitTerm(ctx.term(1)));
            return operator;
        }

        @Override
        public BaseType visitTerm(@NotNull TestGrammerParser.TermContext ctx) {
            return new BaseType(ctx.getText());
        }
    };

    public BaseType parse(String functions) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(new ByteArrayInputStream("x=4".getBytes()));
        TestGrammerLexer lexer = new TestGrammerLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        TestGrammerParser parser = new TestGrammerParser(tokens);
        ParseTree tree = parser.expression(); // begin parsing at init rule

        return new AntlrVisitor().visit(tree);
    }
}
