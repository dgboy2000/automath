// Generated from FirstGrammar.g4 by ANTLR 4.1
package automath.antlr.firstgrammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FirstGrammarParser}.
 */
public interface FirstGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull FirstGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull FirstGrammarParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#equationPredicate}.
	 * @param ctx the parse tree
	 */
	void enterEquationPredicate(@NotNull FirstGrammarParser.EquationPredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#equationPredicate}.
	 * @param ctx the parse tree
	 */
	void exitEquationPredicate(@NotNull FirstGrammarParser.EquationPredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#dExp}.
	 * @param ctx the parse tree
	 */
	void enterDExp(@NotNull FirstGrammarParser.DExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#dExp}.
	 * @param ctx the parse tree
	 */
	void exitDExp(@NotNull FirstGrammarParser.DExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(@NotNull FirstGrammarParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(@NotNull FirstGrammarParser.PredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#predicateVariable}.
	 * @param ctx the parse tree
	 */
	void enterPredicateVariable(@NotNull FirstGrammarParser.PredicateVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#predicateVariable}.
	 * @param ctx the parse tree
	 */
	void exitPredicateVariable(@NotNull FirstGrammarParser.PredicateVariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#aExp}.
	 * @param ctx the parse tree
	 */
	void enterAExp(@NotNull FirstGrammarParser.AExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#aExp}.
	 * @param ctx the parse tree
	 */
	void exitAExp(@NotNull FirstGrammarParser.AExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull FirstGrammarParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull FirstGrammarParser.NumberContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#pExp}.
	 * @param ctx the parse tree
	 */
	void enterPExp(@NotNull FirstGrammarParser.PExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#pExp}.
	 * @param ctx the parse tree
	 */
	void exitPExp(@NotNull FirstGrammarParser.PExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#impPred}.
	 * @param ctx the parse tree
	 */
	void enterImpPred(@NotNull FirstGrammarParser.ImpPredContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#impPred}.
	 * @param ctx the parse tree
	 */
	void exitImpPred(@NotNull FirstGrammarParser.ImpPredContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull FirstGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull FirstGrammarParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#notPred}.
	 * @param ctx the parse tree
	 */
	void enterNotPred(@NotNull FirstGrammarParser.NotPredContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#notPred}.
	 * @param ctx the parse tree
	 */
	void exitNotPred(@NotNull FirstGrammarParser.NotPredContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#andPred}.
	 * @param ctx the parse tree
	 */
	void enterAndPred(@NotNull FirstGrammarParser.AndPredContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#andPred}.
	 * @param ctx the parse tree
	 */
	void exitAndPred(@NotNull FirstGrammarParser.AndPredContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#sExp}.
	 * @param ctx the parse tree
	 */
	void enterSExp(@NotNull FirstGrammarParser.SExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#sExp}.
	 * @param ctx the parse tree
	 */
	void exitSExp(@NotNull FirstGrammarParser.SExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull FirstGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull FirstGrammarParser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#mExp}.
	 * @param ctx the parse tree
	 */
	void enterMExp(@NotNull FirstGrammarParser.MExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#mExp}.
	 * @param ctx the parse tree
	 */
	void exitMExp(@NotNull FirstGrammarParser.MExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(@NotNull FirstGrammarParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(@NotNull FirstGrammarParser.FileContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#orPred}.
	 * @param ctx the parse tree
	 */
	void enterOrPred(@NotNull FirstGrammarParser.OrPredContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#orPred}.
	 * @param ctx the parse tree
	 */
	void exitOrPred(@NotNull FirstGrammarParser.OrPredContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#pPred}.
	 * @param ctx the parse tree
	 */
	void enterPPred(@NotNull FirstGrammarParser.PPredContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#pPred}.
	 * @param ctx the parse tree
	 */
	void exitPPred(@NotNull FirstGrammarParser.PPredContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull FirstGrammarParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull FirstGrammarParser.VariableContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#eExp}.
	 * @param ctx the parse tree
	 */
	void enterEExp(@NotNull FirstGrammarParser.EExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#eExp}.
	 * @param ctx the parse tree
	 */
	void exitEExp(@NotNull FirstGrammarParser.EExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link FirstGrammarParser#predicateOperator}.
	 * @param ctx the parse tree
	 */
	void enterPredicateOperator(@NotNull FirstGrammarParser.PredicateOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link FirstGrammarParser#predicateOperator}.
	 * @param ctx the parse tree
	 */
	void exitPredicateOperator(@NotNull FirstGrammarParser.PredicateOperatorContext ctx);
}