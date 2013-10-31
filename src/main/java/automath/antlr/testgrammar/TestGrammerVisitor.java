// Generated from TestGrammer.g4 by ANTLR 4.1
package automath.antlr.testgrammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TestGrammerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TestGrammerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TestGrammerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull TestGrammerParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link TestGrammerParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull TestGrammerParser.TermContext ctx);

	/**
	 * Visit a parse tree produced by {@link TestGrammerParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull TestGrammerParser.ValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link TestGrammerParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(@NotNull TestGrammerParser.InitContext ctx);

	/**
	 * Visit a parse tree produced by {@link TestGrammerParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(@NotNull TestGrammerParser.OperatorContext ctx);
}