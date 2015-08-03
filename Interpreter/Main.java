import Elements.*;
import Parser.*;
import Token.Operator;
import Token.Token;
import Token.TokenGroup;
import Token.ParseError;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        TokenGroup lexedTokens = Parser.Tokenizer.TokenizeExpression("-6 + 8.3");

        Token parsedTokensTester = Parser.TreeParsing.parse(lexedTokens);
        if(parsedTokensTester instanceof ParseError) {
            System.out.print(((ParseError) parsedTokensTester).errorMessage);
        }
        else {
            TokenGroup parsedTokens = ((TokenGroup) parsedTokensTester);
            System.out.print(parsedTokens.toString());
        }
    }
}
