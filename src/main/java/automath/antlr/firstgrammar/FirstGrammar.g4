grammar FirstGrammar;

// An entire input file
file : statement
 | statement file

/** A value can be either a nested array/struct or a simple integer (INT) */
value : init
| INT
;

expression : term operator term;

term : INT | VARIABLE;
operator : '=';

VARIABLE : [a-zA-Z_][a-zA-Z_0-9]*;

// parser rules start with lowercase letters, lexer rules with uppercase
INT : [0-9]+ ; // Define token INT as one or more digits
WS : [ \t\r\n]+ -> skip ; // Define whitespace rule, toss it out