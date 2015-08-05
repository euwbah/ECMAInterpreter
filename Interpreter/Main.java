import Elements.*;
import Parser.*;
import Token.Operator;
import Token.Token;
import Token.TokenGroup;
import Token.ParseError;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        System.out.print(Parser.Helper.removeAllStringLiterals("1234\"1234\"56\"789\"7"));
    }
}
