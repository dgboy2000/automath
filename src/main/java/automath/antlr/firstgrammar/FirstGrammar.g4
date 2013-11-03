grammar FirstGrammar;

// An entire input file
file : statement | statement file;
statement : predicate;

/*
Logical operations after arithmetic operations
Order of logical operations:
() ~ & | ->
*/

predicate : impPred;

impPred : impPred '->' impPred | orPred;
orPred : orPred '|' orPred | andPred;
andPred : andPred '&' andPred | notPred;
notPred : '~' notPred | pPred;
pPred : '(' impPred ')' | equationPredicate | predicateVariable;

equationPredicate : expression predicateOperator expression;
predicateOperator : PRED_OP;
predicateVariable : PRED_VAR;

// Order of operations: PEMDAS
expression : aExp;

aExp : aExp '+' aExp | sExp;
sExp : sExp '-' sExp | mExp;

mExp : mExp '*' mExp | dExp;
dExp : dExp '/' dExp | eExp;

eExp : eExp '^'<assoc=right> eExp | pExp;
pExp : '(' aExp ')' | term;

term : variable | number;
variable : VARIABLE;
number : INT;


// parser rules start with lowercase letters, lexer rules with uppercase
VARIABLE : [a-z][a-zA-Z_0-9]*;
PRED_VAR : [A-Z][a-zA-Z_0-9]*;

PRED_OP : [=<>] | '<=' | '>=';

INT : [0-9]+ ; // Define token INT as one or more digits
WHITESPACE : [ \t]+ -> skip ; // Define whitespace rule, toss it out