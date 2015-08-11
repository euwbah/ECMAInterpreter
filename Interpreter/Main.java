import Elements.*;
import Parser.*;
import Token.Operator;
import Token.Token;
import Token.TokenGroup;
import Token.ParseError;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        TokenGroup test = Parser.Tokenizer.TokenizeExpression("++x");
        TokenGroup tree = (TokenGroup) Parser.TreeParsing.parse(test);

        System.out.print(tree.toString());
    }
}
