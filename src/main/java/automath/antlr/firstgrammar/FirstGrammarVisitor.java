// Generated from FirstGrammar.g4 by ANTLR 4.1
package automath.antlr.firstgrammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FirstGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FirstGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull FirstGrammarParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#equationPredicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquationPredicate(@NotNull FirstGrammarParser.EquationPredicateContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#dExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDExp(@NotNull FirstGrammarParser.DExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(@NotNull FirstGrammarParser.PredicateContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#predicateVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateVariable(@NotNull FirstGrammarParser.PredicateVariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#aExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAExp(@NotNull FirstGrammarParser.AExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(@NotNull FirstGrammarParser.NumberContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#pExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPExp(@NotNull FirstGrammarParser.PExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#impPred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImpPred(@NotNull FirstGrammarParser.ImpPredContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull FirstGrammarParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#notPred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotPred(@NotNull FirstGrammarParser.NotPredContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#andPred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndPred(@NotNull FirstGrammarParser.AndPredContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#sExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSExp(@NotNull FirstGrammarParser.SExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(@NotNull FirstGrammarParser.TermContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#mExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMExp(@NotNull FirstGrammarParser.MExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(@NotNull FirstGrammarParser.FileContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#orPred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrPred(@NotNull FirstGrammarParser.OrPredContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#pPred}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPPred(@NotNull FirstGrammarParser.PPredContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(@NotNull FirstGrammarParser.VariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#eExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEExp(@NotNull FirstGrammarParser.EExpContext ctx);

	/**
	 * Visit a parse tree produced by {@link FirstGrammarParser#predicateOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicateOperator(@NotNull FirstGrammarParser.PredicateOperatorContext ctx);
}