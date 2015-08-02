import Parser.*;
import Token.TokenGroup;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        TokenGroup test = Parser.Tokenizer.TokenizeExpression("6 + 8.3");

        System.out.print(test.toString());
    }
}
