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
		T__1=10, T__0=11, NUM_VAR=12, PRED_VAR=13, PRED_OP=14, INT=15, WHITESPACE=16, 
		NEWLINE=17;
	public static final String[] tokenNames = {
		"<INVALID>", "'->'", "'&'", "'^'", "')'", "'+'", "'-'", "'*'", "'('", 
		"'/'", "'~'", "'|'", "NUM_VAR", "PRED_VAR", "PRED_OP", "INT", "WHITESPACE", 
		"NEWLINE"
	};
	public static final int
		RULE_file = 0, RULE_statement = 1, RULE_predicate = 2, RULE_impPred = 3, 
		RULE_orPred = 4, RULE_andPred = 5, RULE_notPred = 6, RULE_pPred = 7, RULE_equationPredicate = 8, 
		RULE_predicateOperator = 9, RULE_predicateVariable = 10, RULE_expression = 11, 
		RULE_aExp = 12, RULE_sExp = 13, RULE_mExp = 14, RULE_dExp = 15, RULE_eExp = 16, 
		RULE_pExp = 17, RULE_term = 18, RULE_numberVariable = 19, RULE_number = 20;
	public static final String[] ruleNames = {
		"file", "statement", "predicate", "impPred", "orPred", "andPred", "notPred", 
		"pPred", "equationPredicate", "predicateOperator", "predicateVariable", 
		"expression", "aExp", "sExp", "mExp", "dExp", "eExp", "pExp", "term", 
		"numberVariable", "number"
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
		public TerminalNode NEWLINE() { return getToken(FirstGrammarParser.NEWLINE, 0); }
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
			setState(49); match(NEWLINE);
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
			setState(51); impPred(0);
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
			setState(54); orPred(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(61);
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
					setState(56);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(57); match(1);
					setState(58); impPred(3);
					}
					} 
				}
				setState(63);
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
			setState(65); andPred(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
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
					setState(67);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(68); match(11);
					setState(69); orPred(3);
					}
					} 
				}
				setState(74);
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
			setState(76); notPred();
			}
			_ctx.stop = _input.LT(-1);
			setState(83);
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
					setState(78);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(79); match(2);
					setState(80); andPred(3);
					}
					} 
				}
				setState(85);
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
			setState(89);
			switch (_input.LA(1)) {
			case 10:
				enterOuterAlt(_localctx, 1);
				{
				setState(86); match(10);
				setState(87); notPred();
				}
				break;
			case 8:
			case NUM_VAR:
			case PRED_VAR:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(88); pPred();
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
			setState(97);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91); match(8);
				setState(92); impPred(0);
				setState(93); match(4);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95); equationPredicate();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96); predicateVariable();
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
			setState(99); expression();
			setState(100); predicateOperator();
			setState(101); expression();
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
			setState(103); match(PRED_OP);
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
			setState(105); match(PRED_VAR);
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
			setState(107); aExp(0);
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
			setState(110); sExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(117);
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
					setState(112);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(113); match(5);
					setState(114); aExp(3);
					}
					} 
				}
				setState(119);
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
			setState(121); mExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(128);
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
					setState(123);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(124); match(6);
					setState(125); sExp(3);
					}
					} 
				}
				setState(130);
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
			setState(132); dExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(139);
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
					setState(134);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(135); match(7);
					setState(136); mExp(3);
					}
					} 
				}
				setState(141);
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
			setState(143); eExp(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(150);
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
					setState(145);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(146); match(9);
					setState(147); dExp(3);
					}
					} 
				}
				setState(152);
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
			setState(154); pExp();
			}
			_ctx.stop = _input.LT(-1);
			setState(161);
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
					setState(156);
					if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
					setState(157); match(3);
					setState(158); eExp(2);
					}
					} 
				}
				setState(163);
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
			setState(169);
			switch (_input.LA(1)) {
			case 8:
				enterOuterAlt(_localctx, 1);
				{
				setState(164); match(8);
				setState(165); aExp(0);
				setState(166); match(4);
				}
				break;
			case NUM_VAR:
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(168); term();
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
		public NumberVariableContext numberVariable() {
			return getRuleContext(NumberVariableContext.class,0);
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
			setState(173);
			switch (_input.LA(1)) {
			case NUM_VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(171); numberVariable();
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(172); number();
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

	public static class NumberVariableContext extends ParserRuleContext {
		public TerminalNode NUM_VAR() { return getToken(FirstGrammarParser.NUM_VAR, 0); }
		public NumberVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).enterNumberVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FirstGrammarListener ) ((FirstGrammarListener)listener).exitNumberVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FirstGrammarVisitor ) return ((FirstGrammarVisitor<? extends T>)visitor).visitNumberVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberVariableContext numberVariable() throws RecognitionException {
		NumberVariableContext _localctx = new NumberVariableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_numberVariable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175); match(NUM_VAR);
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
			setState(177); match(INT);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\23\u00b6\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\5\2\61\n\2\3"+
		"\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\7\6I\n\6\f\6\16\6L\13\6\3\7\3\7\3\7\3\7\3\7\3\7\7"+
		"\7T\n\7\f\7\16\7W\13\7\3\b\3\b\3\b\5\b\\\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\td\n\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\7\16v\n\16\f\16\16\16y\13\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u0081\n\17\f\17\16\17\u0084\13\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u008c\n\20\f\20\16\20\u008f\13\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\7\21\u0097\n\21\f\21\16\21\u009a\13\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\7\22\u00a2\n\22\f\22\16\22\u00a5\13\22\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u00ac\n\23\3\24\3\24\5\24\u00b0\n\24\3\25\3\25\3\26\3\26\3\26\2\27\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\2\u00ae\2\60\3\2\2\2\4"+
		"\62\3\2\2\2\6\65\3\2\2\2\b\67\3\2\2\2\nB\3\2\2\2\fM\3\2\2\2\16[\3\2\2"+
		"\2\20c\3\2\2\2\22e\3\2\2\2\24i\3\2\2\2\26k\3\2\2\2\30m\3\2\2\2\32o\3\2"+
		"\2\2\34z\3\2\2\2\36\u0085\3\2\2\2 \u0090\3\2\2\2\"\u009b\3\2\2\2$\u00ab"+
		"\3\2\2\2&\u00af\3\2\2\2(\u00b1\3\2\2\2*\u00b3\3\2\2\2,\61\5\4\3\2-.\5"+
		"\4\3\2./\5\2\2\2/\61\3\2\2\2\60,\3\2\2\2\60-\3\2\2\2\61\3\3\2\2\2\62\63"+
		"\5\6\4\2\63\64\7\23\2\2\64\5\3\2\2\2\65\66\5\b\5\2\66\7\3\2\2\2\678\b"+
		"\5\1\289\5\n\6\29?\3\2\2\2:;\6\5\2\3;<\7\3\2\2<>\5\b\5\2=:\3\2\2\2>A\3"+
		"\2\2\2?=\3\2\2\2?@\3\2\2\2@\t\3\2\2\2A?\3\2\2\2BC\b\6\1\2CD\5\f\7\2DJ"+
		"\3\2\2\2EF\6\6\3\3FG\7\r\2\2GI\5\n\6\2HE\3\2\2\2IL\3\2\2\2JH\3\2\2\2J"+
		"K\3\2\2\2K\13\3\2\2\2LJ\3\2\2\2MN\b\7\1\2NO\5\16\b\2OU\3\2\2\2PQ\6\7\4"+
		"\3QR\7\4\2\2RT\5\f\7\2SP\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\r\3\2"+
		"\2\2WU\3\2\2\2XY\7\f\2\2Y\\\5\16\b\2Z\\\5\20\t\2[X\3\2\2\2[Z\3\2\2\2\\"+
		"\17\3\2\2\2]^\7\n\2\2^_\5\b\5\2_`\7\6\2\2`d\3\2\2\2ad\5\22\n\2bd\5\26"+
		"\f\2c]\3\2\2\2ca\3\2\2\2cb\3\2\2\2d\21\3\2\2\2ef\5\30\r\2fg\5\24\13\2"+
		"gh\5\30\r\2h\23\3\2\2\2ij\7\20\2\2j\25\3\2\2\2kl\7\17\2\2l\27\3\2\2\2"+
		"mn\5\32\16\2n\31\3\2\2\2op\b\16\1\2pq\5\34\17\2qw\3\2\2\2rs\6\16\5\3s"+
		"t\7\7\2\2tv\5\32\16\2ur\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\33\3\2"+
		"\2\2yw\3\2\2\2z{\b\17\1\2{|\5\36\20\2|\u0082\3\2\2\2}~\6\17\6\3~\177\7"+
		"\b\2\2\177\u0081\5\34\17\2\u0080}\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\35\3\2\2\2\u0084\u0082\3\2\2\2\u0085"+
		"\u0086\b\20\1\2\u0086\u0087\5 \21\2\u0087\u008d\3\2\2\2\u0088\u0089\6"+
		"\20\7\3\u0089\u008a\7\t\2\2\u008a\u008c\5\36\20\2\u008b\u0088\3\2\2\2"+
		"\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e\37"+
		"\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\b\21\1\2\u0091\u0092\5\"\22\2"+
		"\u0092\u0098\3\2\2\2\u0093\u0094\6\21\b\3\u0094\u0095\7\13\2\2\u0095\u0097"+
		"\5 \21\2\u0096\u0093\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099!\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c\b\22\1\2"+
		"\u009c\u009d\5$\23\2\u009d\u00a3\3\2\2\2\u009e\u009f\6\22\t\3\u009f\u00a0"+
		"\7\5\2\2\u00a0\u00a2\5\"\22\2\u00a1\u009e\3\2\2\2\u00a2\u00a5\3\2\2\2"+
		"\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4#\3\2\2\2\u00a5\u00a3\3"+
		"\2\2\2\u00a6\u00a7\7\n\2\2\u00a7\u00a8\5\32\16\2\u00a8\u00a9\7\6\2\2\u00a9"+
		"\u00ac\3\2\2\2\u00aa\u00ac\5&\24\2\u00ab\u00a6\3\2\2\2\u00ab\u00aa\3\2"+
		"\2\2\u00ac%\3\2\2\2\u00ad\u00b0\5(\25\2\u00ae\u00b0\5*\26\2\u00af\u00ad"+
		"\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0\'\3\2\2\2\u00b1\u00b2\7\16\2\2\u00b2"+
		")\3\2\2\2\u00b3\u00b4\7\21\2\2\u00b4+\3\2\2\2\17\60?JU[cw\u0082\u008d"+
		"\u0098\u00a3\u00ab\u00af";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}