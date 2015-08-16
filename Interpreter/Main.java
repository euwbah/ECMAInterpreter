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

        String original = "hamla(asdf(potat)o(asdf)(((a))))dnkvea()";

        System.out.println("Original: " + original);

        System.out.println("Within Parenthesis: " + Parser.Helper.readWithinParenthesis(original));
    }
}
