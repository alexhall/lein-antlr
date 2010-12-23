// Simple ANTLR grammar, borrowed from http://www.antlr.org/wiki/display/ANTLR3/Five+minute+introduction+to+ANTLR+3

// Chopped off the last few lines so this is an invalid grammar file.

grammar InvalidCalc;

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

expr  : term ( ( PLUS | MINUS )  term )* ;

term  : factor ( ( MULT | DIV ) factor
