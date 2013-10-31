// Generated from TestGrammer.g4 by ANTLR 4.1
package automath.antlr.testgrammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TestGrammerLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__3=1, T__2=2, T__1=3, T__0=4, VARIABLE=5, INT=6, WS=7;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'{'", "','", "'='", "'}'", "VARIABLE", "INT", "WS"
	};
	public static final String[] ruleNames = {
		"T__3", "T__2", "T__1", "T__0", "VARIABLE", "INT", "WS"
	};


	public TestGrammerLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestGrammer.g4"; }

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
		case 6: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\t,\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\7\6\34\n\6\f\6\16\6\37\13\6\3\7\6\7\"\n\7\r\7\16\7#\3"+
		"\b\6\b\'\n\b\r\b\16\b(\3\b\3\b\2\t\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b"+
		"\1\17\t\2\3\2\6\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"."+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\3\21\3\2\2\2\5\23\3\2\2\2\7\25\3\2\2\2\t\27\3\2\2\2"+
		"\13\31\3\2\2\2\r!\3\2\2\2\17&\3\2\2\2\21\22\7}\2\2\22\4\3\2\2\2\23\24"+
		"\7.\2\2\24\6\3\2\2\2\25\26\7?\2\2\26\b\3\2\2\2\27\30\7\177\2\2\30\n\3"+
		"\2\2\2\31\35\t\2\2\2\32\34\t\3\2\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3"+
		"\2\2\2\35\36\3\2\2\2\36\f\3\2\2\2\37\35\3\2\2\2 \"\t\4\2\2! \3\2\2\2\""+
		"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\16\3\2\2\2%\'\t\5\2\2&%\3\2\2\2\'(\3\2"+
		"\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\b\b\2\2+\20\3\2\2\2\6\2\35#(";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}