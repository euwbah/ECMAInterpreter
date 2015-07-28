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

    public static class Helper
    {
        /**
         * Removes everything encapsulated in "" for Lexing purposes
         * @param expression The raw expression
         * @return Returns the raw expression without string literals
         */
        public static String removeAllStringLiterals(String expression)
        {
            String returnable = "";
            boolean currentlyAString = false;
            boolean usesDoubleQuote = false;

            for(int i = 0; i < expression.length(); i++) {
                String curr = String.valueOf(expression.charAt(i));

                if(!currentlyAString) {
                    if(curr.equals("'")) {
                        currentlyAString = true;
                        usesDoubleQuote = false;
                    }
                    else if(curr.equals('"')) {
                        currentlyAString = true;
                        usesDoubleQuote = true;
                    }
                    else {
                        returnable += curr;
                    }
                }
                else {
                    if((usesDoubleQuote && curr.equals('"')) ||
                            (!usesDoubleQuote && curr.equals("'"))) {
                        currentlyAString = false;
                    }
                    else if(curr.equals("\\")) {
                        i++;//Skip the next char...
                    }
                }
            }

            return returnable;
        }
    }
}