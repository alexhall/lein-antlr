// Simple ANTLR grammar, borrowed from http://www.antlr.org/wiki/display/ANTLR3/Five+minute+introduction+to+ANTLR+3

// Now with parentheses!

grammar ParenCalc;

tokens {
  PLUS  = '+' ;
  MINUS = '-' ;
  MULT  = '*' ;
  DIV = '/' ;
}

@members {
    public static void main(String[] args) throws Exception {
        SimpleCalcLexer lex = new SimpleCalcLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        SimpleCalcParser parser = new SimpleCalcParser(tokens);

        try {
            parser.expr();
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

prog : expr+ ;

expr  : term ( ( PLUS | MINUS )  term )* ;

term  : factor ( ( MULT | DIV ) factor )* ;

factor  : NUMBER | '(' expr ')' ;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

NUMBER  : (DIGIT)+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+  { $channel = HIDDEN; } ;

fragment DIGIT  : '0'..'9' ;
