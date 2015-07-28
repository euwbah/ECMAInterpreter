import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Parser
{
    public static class P_Literal
    {
        public static Pattern numberPattern = Pattern.compile("^\\s*[0-9.]\\s*$");
        public static Pattern boolPattern = Pattern.compile("^\\s*true\\s*$|^\\s*false\\s*$");
        public static Pattern stringPattern = Pattern.compile("^\\s*\".*\"\\s*$|^\\s*\'.*\'\\s*$");

        public static boolean isNumber(String literalCase) {
            return numberPattern.matcher(literalCase).matches();
        }

        public static boolean isBool(String literalCase) {
            return boolPattern.matcher(literalCase).matches();
        }

        public static boolean isString(String literalCase) {
            return stringPattern.matcher(literalCase).matches();
        }

        public static boolean isLiteral(String testCase) {
            return isNumber(testCase) || isBool(testCase) || isString(testCase);
        }
    }

    public static class Tokenizer
    {
        public static TokenGroup TokenizeExpression(String expression)
        {
            TokenGroup currentGroup = new TokenGroup();

            return currentGroup;
        }
    }
}