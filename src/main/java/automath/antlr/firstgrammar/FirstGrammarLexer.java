// Generated from FirstGrammar.g4 by ANTLR 4.1
package automath.antlr.firstgrammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FirstGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, NUM_VAR=12, PRED_VAR=13, PRED_OP=14, INT=15, WHITESPACE=16, 
		NEWLINE=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'->'", "'&'", "'^'", "')'", "'+'", "'-'", "'*'", "'('", "'/'", "'~'", 
		"'|'", "NUM_VAR", "PRED_VAR", "PRED_OP", "INT", "WHITESPACE", "NEWLINE"
	};
	public static final String[] ruleNames = {
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "NUM_VAR", "PRED_VAR", "PRED_OP", "INT", "WHITESPACE", 
		"NEWLINE"
	};


	public FirstGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FirstGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 15: WHITESPACE_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\23h\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\7\r?\n\r\f\r\16\rB\13\r\3\16\3\16\7\16"+
		"F\n\16\f\16\16\16I\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17R\n\17"+
		"\3\20\6\20U\n\20\r\20\16\20V\3\21\6\21Z\n\21\r\21\16\21[\3\21\3\21\3\22"+
		"\7\22a\n\22\f\22\16\22d\13\22\3\22\5\22g\n\22\2\23\3\3\1\5\4\1\7\5\1\t"+
		"\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1"+
		"\35\20\1\37\21\1!\22\2#\23\1\3\2\n\3\2c|\6\2\62;C\\aac|\3\2C\\\3\2>@\3"+
		"\2\62;\4\2\13\13\"\"\4\2\f\f\17\17\4\3\f\f\17\17o\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5(\3\2"+
		"\2\2\7*\3\2\2\2\t,\3\2\2\2\13.\3\2\2\2\r\60\3\2\2\2\17\62\3\2\2\2\21\64"+
		"\3\2\2\2\23\66\3\2\2\2\258\3\2\2\2\27:\3\2\2\2\31<\3\2\2\2\33C\3\2\2\2"+
		"\35Q\3\2\2\2\37T\3\2\2\2!Y\3\2\2\2#b\3\2\2\2%&\7/\2\2&\'\7@\2\2\'\4\3"+
		"\2\2\2()\7(\2\2)\6\3\2\2\2*+\7`\2\2+\b\3\2\2\2,-\7+\2\2-\n\3\2\2\2./\7"+
		"-\2\2/\f\3\2\2\2\60\61\7/\2\2\61\16\3\2\2\2\62\63\7,\2\2\63\20\3\2\2\2"+
		"\64\65\7*\2\2\65\22\3\2\2\2\66\67\7\61\2\2\67\24\3\2\2\289\7\u0080\2\2"+
		"9\26\3\2\2\2:;\7~\2\2;\30\3\2\2\2<@\t\2\2\2=?\t\3\2\2>=\3\2\2\2?B\3\2"+
		"\2\2@>\3\2\2\2@A\3\2\2\2A\32\3\2\2\2B@\3\2\2\2CG\t\4\2\2DF\t\3\2\2ED\3"+
		"\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\34\3\2\2\2IG\3\2\2\2JR\t\5\2\2K"+
		"L\7>\2\2LR\7?\2\2MN\7@\2\2NR\7?\2\2OP\7\u0080\2\2PR\7?\2\2QJ\3\2\2\2Q"+
		"K\3\2\2\2QM\3\2\2\2QO\3\2\2\2R\36\3\2\2\2SU\t\6\2\2TS\3\2\2\2UV\3\2\2"+
		"\2VT\3\2\2\2VW\3\2\2\2W \3\2\2\2XZ\t\7\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2"+
		"\2[\\\3\2\2\2\\]\3\2\2\2]^\b\21\2\2^\"\3\2\2\2_a\t\b\2\2`_\3\2\2\2ad\3"+
		"\2\2\2b`\3\2\2\2bc\3\2\2\2cf\3\2\2\2db\3\2\2\2eg\t\t\2\2fe\3\2\2\2g$\3"+
		"\2\2\2\n\2@GQV[bf";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}