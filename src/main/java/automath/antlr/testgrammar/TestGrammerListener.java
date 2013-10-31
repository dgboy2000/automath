// Generated from TestGrammer.g4 by ANTLR 4.1
package automath.antlr.testgrammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestGrammerParser}.
 */
public interface TestGrammerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestGrammerParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull TestGrammerParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammerParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull TestGrammerParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link TestGrammerParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull TestGrammerParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammerParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull TestGrammerParser.TermContext ctx);

	/**
	 * Enter a parse tree produced by {@link TestGrammerParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull TestGrammerParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammerParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull TestGrammerParser.ValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link TestGrammerParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(@NotNull TestGrammerParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammerParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(@NotNull TestGrammerParser.InitContext ctx);

	/**
	 * Enter a parse tree produced by {@link TestGrammerParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(@NotNull TestGrammerParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammerParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(@NotNull TestGrammerParser.OperatorContext ctx);
}