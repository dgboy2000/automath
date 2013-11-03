automath
========

Automated mathematics

Getting Started
1. Add aliases:
    antlr4='java -jar /DannyGoodmanOld/Sites/ai/math/automath/lib/antlr-4.1-complete.jar'
2. Compile grammar:
    antlr4 -package automath.antlr.firstgrammar -visitor -Xlog FirstGrammar.g4
    grammars: TestGrammar.g4 FirstGrammar.g4