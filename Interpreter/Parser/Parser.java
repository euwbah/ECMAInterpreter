package Parser;

import Token.*;

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import Token.Operator.*;

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
        /**
         * Basically a Parser solely for expressions
         * @param expression An expression
         * @return Returns a TokenGroup of the TokenizedExpression
         */
        public static TokenGroup TokenizeExpression(String expression)
        {
            TokenGroup currentGroup = new TokenGroup();
            String toBeScanned = expression;

            //Repeat until everything is scanned
            while(toBeScanned != "") {
                String currentIdentifierBeforeNextOperator = returnStringUntilNextOperator(toBeScanned);
                //Mark the current identifier as scanned; remove the cIBNO from toBeScanned
                toBeScanned.substring(currentIdentifierBeforeNextOperator.length());

                //TODO:
            }

            return currentGroup;
        }

        /**
         * Checks a String and returns everything until the next operator (excluding the operator)
         * @param expression A String to check for
         * @return Returns everything before the next operator. Returns "" if the first char is non-alphanumeric.
         */
        private static String returnStringUntilNextOperator(String expression) {
            String returnable = "";

            for(int i = 0; i < expression.length(); i++) {
                String curr = String.valueOf(expression.charAt(i));
                if(Helper.isAlphaNumeric(curr))
                    returnable += curr;
                else
                    return returnable;
            }

            return returnable;
        }
    }

    /**
     * Collection of random generic helper functions
     */
    public static class Helper
    {
        /**
         * Used to check if a String is an identifier
         */
        private static Pattern identifierPattern = Pattern.compile("^[A-Za-z_]*[A-Za-z_0-9]+$");

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

        /**
         * Gets the lowest precendence top-level operator from a TokenGroup
         * Used as the first step for recursive-decent parsing
         *
         * @param group A Token Group containing the parsed expression.
         * @return An expression with anything in brackets removed..
         */
        public static OperatorGroup getLowestPrecedenceOperator(TokenGroup group) {
			OperatorGroup opGrp = OperatorGroup.Primary;

			for(int i = 0; i < group.tokens.size(); i++) {
                Token currToken = group.tokens.get(i);
                if(currToken instanceof Operator && ((Operator) currToken).opGrp.compareTo(opGrp) < 0) {//If is of lower precedence
                    opGrp = ((Operator) currToken).opGrp;
                }
            }

            return opGrp;
        }

        public static boolean isAlphaNumeric(String test) {
            return identifierPattern.matcher(test).matches();
        }
    }
}
