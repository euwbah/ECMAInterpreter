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
        String testSplit = "Derp => {Lol} => Face";
        ArrayList<String> result = Parser.Helper.smartSplit(testSplit, "=>");

        System.out.println("Original: " + testSplit);

        result.forEach(System.out::println);
    }
}
