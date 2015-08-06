import Elements.*;
import Parser.*;
import Token.Operator;
import Token.Token;
import Token.TokenGroup;
import Token.ParseError;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        System.out.print(Parser.SyntaxHandler.BraceTypeImbalances.getBracketImbalance("asdf(bt.(a_')'ak(\"))\"md('('"));
    }
}
