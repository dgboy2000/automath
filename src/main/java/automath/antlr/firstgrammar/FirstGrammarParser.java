// Generated from FirstGrammar.g4 by ANTLR 4.1
package automath.antlr.firstgrammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FirstGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, VARIABLE=12, PRED_VAR=13, PRED_OP=14, INT=15, WHITESPACE=16;
	public static final String[] tokenNames = {
		"<INVALID>", "'->'", "'&'", "'^'", "')'", "'+'", "'-'", "'*'", "'('", 
		"'/'", "'~'", "'|'", "VARIABLE", "PRED_VAR", "PRED_OP", "INT", "WHITESPACE"
	};
	public static final int
		RULE_file = 0, RULE_statement = 1, RULE_predicate = 2, RULE_impPred = 3, 
		RULE_orPred = 4, RULE_andPred = 5, RULE_notPred = 6, RULE_pPred = 7, RULE_equationPredicate = 8, 
		RULE_predicateOperator = 9, RULE_predicateVariable = 10, RULE_expression = 11, 
		RULE_aExp = 12, RULE_sExp = 13, RULE_mExp = 14, RULE_dExp = 15, RULE_eExp = 16, 
		RULE_pExp = 17, RULE_term = 18, RULE_variable = 19, RULE_number = 20;
	public static final String[] ruleNames = {
		"file", "statement", "predicate", "impPred", "orPred", "andPred", "notPred", 
		"pPred", "equationPredicate", "predicateOperator", "predicateVariable", 
		"expression", "aExp", "sExp", "mExp", "dExp", "eExp", "pExp", "term", 
		"variable", "number"
	};

	@Override
	public String getGrammarFileName() { return "FirstGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public FirstGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public FileContext file() {
			return getRuleContext(FileContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(42); statement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43); statement();
				setState(44); file();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public PredicateContext predicate() {
			return getRuleContext(PredicateContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); predicate();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public ImpPredContext impPred() {
			return getRuleContext(ImpPredContext.class,0);
		}
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); impPred(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImpPredContext extends ParserRuleContext {
		public int _p;
		public OrPredContext orPred() {
			return getRuleContext(OrPredContext.class,0);
		}
		public ImpPredContext impPred(int i) {
			return getRuleContext(ImpPredContext.class,i);
		}
		public List<ImpPredContext> impPred() {
			return getRuleContexts(ImpPredContext.class);
		}
		public ImpPredContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ImpPredContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_impPred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterImpPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitImpPred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitImpPred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImpPredContext impPred(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ImpPredContext _localctx = new ImpPredContext(_ctx, _parentState, _p);
		ImpPredContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, RULE_impPred);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(53); orPred(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(60);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ImpPredContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_impPred);
					setState(55);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(56); match(1);
					setState(57); impPred(3);
					}
					} 
				}
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class OrPredContext extends ParserRuleContext {
		public int _p;
		public List<OrPredContext> orPred() {
			return getRuleContexts(OrPredContext.class);
		}
		public OrPredContext orPred(int i) {
			return getRuleContext(OrPredContext.class,i);
		}
		public AndPredContext andPred() {
			return getRuleContext(AndPredContext.class,0);
		}
		public OrPredContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public OrPredContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_orPred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterOrPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitOrPred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitOrPred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrPredContext orPred(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		OrPredContext _localctx = new OrPredContext(_ctx, _parentState, _p);
		OrPredContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, RULE_orPred);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(64); andPred(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new OrPredContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_orPred);
					setState(66);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(67); match(11);
					setState(68); orPred(3);
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AndPredContext extends ParserRuleContext {
		public int _p;
		public AndPredContext andPred(int i) {
			return getRuleContext(AndPredContext.class,i);
		}
		public List<AndPredContext> andPred() {
			return getRuleContexts(AndPredContext.class);
		}
		public NotPredContext notPred() {
			return getRuleContext(NotPredContext.class,0);
		}
		public AndPredContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AndPredContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_andPred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterAndPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitAndPred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitAndPred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndPredContext andPred(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AndPredContext _localctx = new AndPredContext(_ctx, _parentState, _p);
		AndPredContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, RULE_andPred);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(75); notPred();
			}
			_ctx.stop = _input.LT(-1);
			setState(82);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AndPredContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_andPred);
					setState(77);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(78); match(2);
					setState(79); andPred(3);
					}
					} 
				}
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class NotPredContext extends ParserRuleContext {
		public PPredContext pPred() {
			return getRuleContext(PPredContext.class,0);
		}
		public NotPredContext notPred() {
			return getRuleContext(NotPredContext.class,0);
		}
		public NotPredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_notPred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterNotPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitNotPred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitNotPred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotPredContext notPred() throws RecognitionException {
		NotPredContext _localctx = new NotPredContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_notPred);
		try {
			setState(88);
			switch (_input.LA(1)) {
			case 10:
				enterOuterAlt(_localctx, 1);
				{
				setState(85); match(10);
				setState(86); notPred();
				}
				break;
			case 8:
			case VARIABLE:
			case PRED_VAR:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(87); pPred();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PPredContext extends ParserRuleContext {
		public PredicateVariableContext predicateVariable() {
			return getRuleContext(PredicateVariableContext.class,0);
		}
		public ImpPredContext impPred() {
			return getRuleContext(ImpPredContext.class,0);
		}
		public EquationPredicateContext equationPredicate() {
			return getRuleContext(EquationPredicateContext.class,0);
		}
		public PPredContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pPred; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterPPred(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitPPred(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitPPred(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PPredContext pPred() throws RecognitionException {
		PPredContext _localctx = new PPredContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_pPred);
		try {
			setState(96);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90); match(8);
				setState(91); impPred(0);
				setState(92); match(4);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(94); equationPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(95); predicateVariable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EquationPredicateContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public PredicateOperatorContext predicateOperator() {
			return getRuleContext(PredicateOperatorContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public EquationPredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equationPredicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterEquationPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitEquationPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitEquationPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationPredicateContext equationPredicate() throws RecognitionException {
		EquationPredicateContext _localctx = new EquationPredicateContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_equationPredicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); expression();
			setState(99); predicateOperator();
			setState(100); expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateOperatorContext extends ParserRuleContext {
		public TerminalNode PRED_OP() { return getToken(FirstGrammarParser.PRED_OP, 0); }
		public PredicateOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicateOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterPredicateOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitPredicateOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitPredicateOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateOperatorContext predicateOperator() throws RecognitionException {
		PredicateOperatorContext _localctx = new PredicateOperatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_predicateOperator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); match(PRED_OP);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateVariableContext extends ParserRuleContext {
		public TerminalNode PRED_VAR() { return getToken(FirstGrammarParser.PRED_VAR, 0); }
		public PredicateVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicateVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterPredicateVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitPredicateVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitPredicateVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateVariableContext predicateVariable() throws RecognitionException {
		PredicateVariableContext _localctx = new PredicateVariableContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_predicateVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); match(PRED_VAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AExpContext aExp() {
			return getRuleContext(AExpContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); aExp(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AExpContext extends ParserRuleContext {
		public int _p;
		public SExpContext sExp() {
			return getRuleContext(SExpContext.class,0);
		}
		public AExpContext aExp(int i) {
			return getRuleContext(AExpContext.class,i);
		}
		public List<AExpContext> aExp() {
			return getRuleContexts(AExpContext.class);
		}
		public AExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public AExpContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_aExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterAExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitAExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitAExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AExpContext aExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AExpContext _localctx = new AExpContext(_ctx, _parentState, _p);
		AExpContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, RULE_aExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(109); sExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AExpContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_aExp);
					setState(111);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(112); match(5);
					setState(113); aExp(3);
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class SExpContext extends ParserRuleContext {
		public int _p;
		public SExpContext sExp(int i) {
			return getRuleContext(SExpContext.class,i);
		}
		public MExpContext mExp() {
			return getRuleContext(MExpContext.class,0);
		}
		public List<SExpContext> sExp() {
			return getRuleContexts(SExpContext.class);
		}
		public SExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public SExpContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_sExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterSExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitSExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitSExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SExpContext sExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		SExpContext _localctx = new SExpContext(_ctx, _parentState, _p);
		SExpContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, RULE_sExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(120); mExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SExpContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_sExp);
					setState(122);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(123); match(6);
					setState(124); sExp(3);
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class MExpContext extends ParserRuleContext {
		public int _p;
		public List<MExpContext> mExp() {
			return getRuleContexts(MExpContext.class);
		}
		public MExpContext mExp(int i) {
			return getRuleContext(MExpContext.class,i);
		}
		public DExpContext dExp() {
			return getRuleContext(DExpContext.class,0);
		}
		public MExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public MExpContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_mExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterMExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitMExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitMExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MExpContext mExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MExpContext _localctx = new MExpContext(_ctx, _parentState, _p);
		MExpContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, RULE_mExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(131); dExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MExpContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_mExp);
					setState(133);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(134); match(7);
					setState(135); mExp(3);
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DExpContext extends ParserRuleContext {
		public int _p;
		public DExpContext dExp(int i) {
			return getRuleContext(DExpContext.class,i);
		}
		public EExpContext eExp() {
			return getRuleContext(EExpContext.class,0);
		}
		public List<DExpContext> dExp() {
			return getRuleContexts(DExpContext.class);
		}
		public DExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DExpContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_dExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterDExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitDExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitDExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DExpContext dExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DExpContext _localctx = new DExpContext(_ctx, _parentState, _p);
		DExpContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, RULE_dExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(142); eExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DExpContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_dExp);
					setState(144);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(145); match(9);
					setState(146); dExp(3);
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class EExpContext extends ParserRuleContext {
		public int _p;
		public EExpContext eExp(int i) {
			return getRuleContext(EExpContext.class,i);
		}
		public PExpContext pExp() {
			return getRuleContext(PExpContext.class,0);
		}
		public List<EExpContext> eExp() {
			return getRuleContexts(EExpContext.class);
		}
		public EExpContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public EExpContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_eExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterEExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitEExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitEExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EExpContext eExp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EExpContext _localctx = new EExpContext(_ctx, _parentState, _p);
		EExpContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, RULE_eExp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(153); pExp();
			}
			_ctx.stop = _input.LT(-1);
			setState(160);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new EExpContext(_parentctx, _parentState, _p);
					pushNewRecursionContext(_localctx, _startState, RULE_eExp);
					setState(155);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(156); match(3);
					setState(157); eExp(2);
					}
					} 
				}
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PExpContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AExpContext aExp() {
			return getRuleContext(AExpContext.class,0);
		}
		public PExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterPExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitPExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitPExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PExpContext pExp() throws RecognitionException {
		PExpContext _localctx = new PExpContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_pExp);
		try {
			setState(168);
			switch (_input.LA(1)) {
			case 8:
				enterOuterAlt(_localctx, 1);
				{
				setState(163); match(8);
				setState(164); aExp(0);
				setState(165); match(4);
				}
				break;
			case VARIABLE:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(167); term();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_term);
		try {
			setState(172);
			switch (_input.LA(1)) {
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(170); variable();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(171); number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(FirstGrammarParser.VARIABLE, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); match(VARIABLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(FirstGrammarParser.INT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3: return impPred_sempred((ImpPredContext)_localctx, predIndex);

		case 4: return orPred_sempred((OrPredContext)_localctx, predIndex);

		case 5: return andPred_sempred((AndPredContext)_localctx, predIndex);

		case 12: return aExp_sempred((AExpContext)_localctx, predIndex);

		case 13: return sExp_sempred((SExpContext)_localctx, predIndex);

		case 14: return mExp_sempred((MExpContext)_localctx, predIndex);

		case 15: return dExp_sempred((DExpContext)_localctx, predIndex);

		case 16: return eExp_sempred((EExpContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean andPred_sempred(AndPredContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean sExp_sempred(SExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean mExp_sempred(MExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean orPred_sempred(OrPredContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean dExp_sempred(DExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean aExp_sempred(AExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean impPred_sempred(ImpPredContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 2 >= _localctx._p;
		}
		return true;
	}
	private boolean eExp_sempred(EExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: return 2 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\22\u00b5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\5\2\61\n\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5=\n\5\f\5\16\5@\13\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\7\6H\n\6\f\6\16\6K\13\6\3\7\3\7\3\7\3\7\3\7\3\7\7\7S"+
		"\n\7\f\7\16\7V\13\7\3\b\3\b\3\b\5\b[\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\tc"+
		"\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\7\16u\n\16\f\16\16\16x\13\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17"+
		"\u0080\n\17\f\17\16\17\u0083\13\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20"+
		"\u008b\n\20\f\20\16\20\u008e\13\20\3\21\3\21\3\21\3\21\3\21\3\21\7\21"+
		"\u0096\n\21\f\21\16\21\u0099\13\21\3\22\3\22\3\22\3\22\3\22\3\22\7\22"+
		"\u00a1\n\22\f\22\16\22\u00a4\13\22\3\23\3\23\3\23\3\23\3\23\5\23\u00ab"+
		"\n\23\3\24\3\24\5\24\u00af\n\24\3\25\3\25\3\26\3\26\3\26\2\27\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\2\u00ad\2\60\3\2\2\2\4\62\3\2"+
		"\2\2\6\64\3\2\2\2\b\66\3\2\2\2\nA\3\2\2\2\fL\3\2\2\2\16Z\3\2\2\2\20b\3"+
		"\2\2\2\22d\3\2\2\2\24h\3\2\2\2\26j\3\2\2\2\30l\3\2\2\2\32n\3\2\2\2\34"+
		"y\3\2\2\2\36\u0084\3\2\2\2 \u008f\3\2\2\2\"\u009a\3\2\2\2$\u00aa\3\2\2"+
		"\2&\u00ae\3\2\2\2(\u00b0\3\2\2\2*\u00b2\3\2\2\2,\61\5\4\3\2-.\5\4\3\2"+
		"./\5\2\2\2/\61\3\2\2\2\60,\3\2\2\2\60-\3\2\2\2\61\3\3\2\2\2\62\63\5\6"+
		"\4\2\63\5\3\2\2\2\64\65\5\b\5\2\65\7\3\2\2\2\66\67\b\5\1\2\678\5\n\6\2"+
		"8>\3\2\2\29:\6\5\2\3:;\7\3\2\2;=\5\b\5\2<9\3\2\2\2=@\3\2\2\2><\3\2\2\2"+
		">?\3\2\2\2?\t\3\2\2\2@>\3\2\2\2AB\b\6\1\2BC\5\f\7\2CI\3\2\2\2DE\6\6\3"+
		"\3EF\7\r\2\2FH\5\n\6\2GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\13\3\2"+
		"\2\2KI\3\2\2\2LM\b\7\1\2MN\5\16\b\2NT\3\2\2\2OP\6\7\4\3PQ\7\4\2\2QS\5"+
		"\f\7\2RO\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\r\3\2\2\2VT\3\2\2\2WX"+
		"\7\f\2\2X[\5\16\b\2Y[\5\20\t\2ZW\3\2\2\2ZY\3\2\2\2[\17\3\2\2\2\\]\7\n"+
		"\2\2]^\5\b\5\2^_\7\6\2\2_c\3\2\2\2`c\5\22\n\2ac\5\26\f\2b\\\3\2\2\2b`"+
		"\3\2\2\2ba\3\2\2\2c\21\3\2\2\2de\5\30\r\2ef\5\24\13\2fg\5\30\r\2g\23\3"+
		"\2\2\2hi\7\20\2\2i\25\3\2\2\2jk\7\17\2\2k\27\3\2\2\2lm\5\32\16\2m\31\3"+
		"\2\2\2no\b\16\1\2op\5\34\17\2pv\3\2\2\2qr\6\16\5\3rs\7\7\2\2su\5\32\16"+
		"\2tq\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\33\3\2\2\2xv\3\2\2\2yz\b\17"+
		"\1\2z{\5\36\20\2{\u0081\3\2\2\2|}\6\17\6\3}~\7\b\2\2~\u0080\5\34\17\2"+
		"\177|\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2"+
		"\2\u0082\35\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\b\20\1\2\u0085\u0086"+
		"\5 \21\2\u0086\u008c\3\2\2\2\u0087\u0088\6\20\7\3\u0088\u0089\7\t\2\2"+
		"\u0089\u008b\5\36\20\2\u008a\u0087\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008d\37\3\2\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0090\b\21\1\2\u0090\u0091\5\"\22\2\u0091\u0097\3\2\2\2\u0092\u0093\6"+
		"\21\b\3\u0093\u0094\7\13\2\2\u0094\u0096\5 \21\2\u0095\u0092\3\2\2\2\u0096"+
		"\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098!\3\2\2\2"+
		"\u0099\u0097\3\2\2\2\u009a\u009b\b\22\1\2\u009b\u009c\5$\23\2\u009c\u00a2"+
		"\3\2\2\2\u009d\u009e\6\22\t\3\u009e\u009f\7\5\2\2\u009f\u00a1\5\"\22\2"+
		"\u00a0\u009d\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3"+
		"\3\2\2\2\u00a3#\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7\n\2\2\u00a6"+
		"\u00a7\5\32\16\2\u00a7\u00a8\7\6\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00ab\5"+
		"&\24\2\u00aa\u00a5\3\2\2\2\u00aa\u00a9\3\2\2\2\u00ab%\3\2\2\2\u00ac\u00af"+
		"\5(\25\2\u00ad\u00af\5*\26\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\'\3\2\2\2\u00b0\u00b1\7\16\2\2\u00b1)\3\2\2\2\u00b2\u00b3\7\21\2\2\u00b3"+
		"+\3\2\2\2\17\60>ITZbv\u0081\u008c\u0097\u00a2\u00aa\u00ae";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}