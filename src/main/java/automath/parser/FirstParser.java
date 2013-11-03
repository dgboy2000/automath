package automath.parser;

import automath.antlr.firstgrammar.*;
import automath.type.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Parses FirstGrammar into automath types
 */
public class FirstParser {

    /*
    predicate : impPred;

    impPred : impPred '->' impPred | orPred;
    orPred : orPred '|' orPred | andPred;
    andPred : andPred '&' andPred | notPred;
    notPred : '~' notPred | pPred;
    pPred : '(' impPred ')' | equationPredicate | predicateVariable;
     */
    private class FirstVisitor extends FirstGrammarBaseVisitor<Type> {
        @Override
        public BaseType visitStatement(@NotNull FirstGrammarParser.StatementContext ctx) {
            return visitPredicate(ctx.predicate());
        }

        @Override
        public BaseType visitPredicate(@NotNull FirstGrammarParser.PredicateContext ctx) {
            return visitImpPred(ctx.impPred());
        }

        @Override
        public BaseType visitImpPred(@NotNull FirstGrammarParser.ImpPredContext ctx) {
            if (ctx.orPred() != null) return visitOrPred(ctx.orPred());
            return new Predicate(
                    visitImpPred(ctx.impPred(0)),
                    Operator.IMPLIES,
                    visitImpPred(ctx.impPred(1))
            );
        }

        @Override
        public BaseType visitOrPred(@NotNull FirstGrammarParser.OrPredContext ctx) {
            if (ctx.andPred() != null) return visitAndPred(ctx.andPred());
            return new Predicate(
                    visitOrPred(ctx.orPred(0)),
                    Operator.OR,
                    visitOrPred(ctx.orPred(1))
            );
        }

        @Override
        public BaseType visitAndPred(@NotNull FirstGrammarParser.AndPredContext ctx) {
            if (ctx.notPred() != null) return visitNotPred(ctx.notPred());
            return new Predicate(
                    visitAndPred(ctx.andPred(0)),
                    Operator.AND,
                    visitAndPred(ctx.andPred(1))
            );
        }

        @Override
        public BaseType visitNotPred(@NotNull FirstGrammarParser.NotPredContext ctx) {
            if (ctx.pPred() != null) return visitPPred(ctx.pPred());
            return new Predicate(
                    Operator.NOT,
                    visitNotPred(ctx.notPred())
            );
        }

        @Override
        public BaseType visitPPred(@NotNull FirstGrammarParser.PPredContext ctx) {
            if (ctx.equationPredicate() != null) return visitEquationPredicate(ctx.equationPredicate());
            if (ctx.predicateVariable() != null) return visitPredicateVariable(ctx.predicateVariable());
            return visitImpPred(ctx.impPred());
        }

        @Override
        public Predicate visitEquationPredicate(@NotNull FirstGrammarParser.EquationPredicateContext ctx) {
            return new Predicate(
                    visitExpression(ctx.expression(0)),
                    visitPredicateOperator(ctx.predicateOperator()),
                    visitExpression(ctx.expression(1))
            );
        }

        @Override
        public Variable visitPredicateVariable(@NotNull FirstGrammarParser.PredicateVariableContext ctx) {
            return new Variable(ctx.getText());
        }

        @Override
        public BaseType visitExpression(@NotNull FirstGrammarParser.ExpressionContext ctx) {
            return visitAExp(ctx.aExp());
        }

        @Override
        public BaseType visitAExp(@NotNull FirstGrammarParser.AExpContext ctx) {
            if (ctx.sExp() != null) return visitSExp(ctx.sExp());
            return new Expression(
                    visitAExp(ctx.aExp(0)),
                    Operator.PLUS,
                    visitAExp(ctx.aExp(1))
            );
        }

        @Override
        public BaseType visitSExp(@NotNull FirstGrammarParser.SExpContext ctx) {
            if (ctx.mExp() != null) return visitMExp(ctx.mExp());
            return new Expression(
                    visitSExp(ctx.sExp(0)),
                    Operator.MINUS,
                    visitSExp(ctx.sExp(1))
            );
        }

        @Override
        public BaseType visitMExp(@NotNull FirstGrammarParser.MExpContext ctx) {
            if (ctx.dExp() != null) return visitDExp(ctx.dExp());
            return new Expression(
                    visitMExp(ctx.mExp(0)),
                    Operator.MULT,
                    visitMExp(ctx.mExp(1))
            );
        }

        @Override
        public BaseType visitDExp(@NotNull FirstGrammarParser.DExpContext ctx) {
            if (ctx.eExp() != null) return visitEExp(ctx.eExp());
            return new Expression(
                    visitDExp(ctx.dExp(0)),
                    Operator.DIV,
                    visitDExp(ctx.dExp(1))
            );
        }

        @Override
        public BaseType visitEExp(@NotNull FirstGrammarParser.EExpContext ctx) {
            if (ctx.pExp() != null) return visitPExp(ctx.pExp());
            return new Expression(
                    visitEExp(ctx.eExp(0)),
                    Operator.EXP,
                    visitEExp(ctx.eExp(1))
            );
        }

        @Override
        public BaseType visitPExp(@NotNull FirstGrammarParser.PExpContext ctx) {
            if (ctx.term() != null) return visitTerm(ctx.term());
            return visitAExp(ctx.aExp());
        }

        @Override
        public BaseType visitTerm(@NotNull FirstGrammarParser.TermContext ctx) {
            if (ctx.variable() != null) return visitVariable(ctx.variable());
            return visitNumber(ctx.number());
        }

        @Override
        public Variable visitVariable(@NotNull FirstGrammarParser.VariableContext ctx) {
            return new Variable(ctx.getText());
        }

        @Override
        public NaturalNumber visitNumber(@NotNull FirstGrammarParser.NumberContext ctx) {
            return new NaturalNumber(ctx.getText());
        }

        @Override
        public Operator visitPredicateOperator(@NotNull FirstGrammarParser.PredicateOperatorContext ctx) {
            return Operator.getEnum(ctx.getText());
        }
    };

    private FirstGrammarParser getParser(String str) {
        ANTLRInputStream input;
        try { input = new ANTLRInputStream(new ByteArrayInputStream(str.getBytes())); }
        catch (IOException ioe) { throw new RuntimeException(ioe); }
        FirstGrammarLexer lexer = new FirstGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new FirstGrammarParser(tokens);
    }

    public Predicate parsePredicate(String predicate) {
        return (Predicate) new FirstVisitor().visit(getParser(predicate).predicate());
    }

    public Expression parseExpression(String expression) {
        return (Expression) new FirstVisitor().visit(getParser(expression).expression());
    }

    public Variable parseVariable(String variable) {
        return (Variable) new FirstVisitor().visit(getParser(variable).variable());
    }
}
