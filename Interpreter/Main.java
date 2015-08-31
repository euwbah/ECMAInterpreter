import Elements.*;
import Parser.*;
import Token.Operator;
import Token.Token;
import Token.TokenGroup;
import Token.ParseError;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...

        String expression = "4 * (2 ++  ) - 1";

        TokenGroup tokenized = Parser.Tokenizer.TokenizeExpression(expression, null);

        System.out.println(tokenized.toString());
    }
}
