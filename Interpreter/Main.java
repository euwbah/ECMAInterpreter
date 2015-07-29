import java.util.*;
import java.io.*;
import Parser.*;
import Token.*;
import Token.Operator.*;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        TokenGroup temp = Parser.Tokenizer.TokenizeExpression("+");
        System.out.print(temp.toString());
    }
}
