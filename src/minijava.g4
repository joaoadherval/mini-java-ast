grammar minijava;

goal: mainclass (classdeclaration)* EOF;

mainclass: 'class' IDENTIFIER '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' IDENTIFIER ')' '{' statement '}' '}';

classdeclaration: 'class' IDENTIFIER ('extends' IDENTIFIER)? '{' (vardeclaration)* (methoddeclaration)* '}';

vardeclaration: type IDENTIFIER ';';

methoddeclaration: 'public' type IDENTIFIER '(' (type IDENTIFIER (',' type IDENTIFIER)*)? ')' '{' (vardeclaration)* (statement)* 'return' expression ';' '}';

type: 'int' '[' ']'
	| 'boolean'
	| 'int'
	| IDENTIFIER
	;

statement: '{' (statement)* '}'
		 | 'if' '(' expression ')' statement 'else' statement
		 | 'while' '(' expression ')' statement
		 | 'System.out.prinln' '('expression')'';'
		 | IDENTIFIER '=' expression ';'
		 | IDENTIFIER '[' expression ']' '=' expression ';'
		 ;

expression: expression OP expression
		  | expression '[' expression ']'
		  | expression '.' 'length'
		  | expression '.' IDENTIFIER '(' (expression (',' expression)*)? ')'
		  | INTEGER
		  | 'true'
		  | 'false'
		  | IDENTIFIER
		  | 'this'
		  | 'new' 'int' '[' expression ']'
		  | 'new' IDENTIFIER '(' ')'
		  | '!' expression
		  | '(' expression ')'
		  ;

IDENTIFIER: ([a-zA-Z] | '_' | [0-9])+;
INTEGER: 	('-')?[0-9]+;
OP: 		('&&' | '<' | '+' | '-' | '*');
WS: 		[ \r\t\n]+ -> skip;