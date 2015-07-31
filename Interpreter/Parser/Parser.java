package Parser;

import Elements.NativeValue;
import Token.*;

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

        public static boolean isLiteral(String testCase) { return isNumber(testCase) || isBool(testCase) || isString(testCase); }

        /**
         * Gets the type of the literal
         * @param literalStr A raw string literal
         * @return Returns a NativeValue.NativeType. <b>IMPORTANT: </b> it may also return NULL!!!
         */
        public static NativeValue.NativeType getTypeOfLiteral(String literalStr) {
            if(isBool(literalStr)) return NativeValue.NativeType.bool;
            else if(isNumber(literalStr)) return NativeValue.NativeType.number;
            else if(isString(literalStr)) return NativeValue.NativeType.string;

            return null;
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
            while(!toBeScanned.trim().equals("")) {
                String currentIdentifierBeforeNextOperator = returnStringUntilNextOperator(toBeScanned);

                //Operator mode (aka, no identifiers before the next operator, aka now is the next operator)
                if(currentIdentifierBeforeNextOperator.trim().equals("")) {
                    //This means that toBeScanned starts with an operator
                    String operatorString = returnStringUntilNextIdentifier(toBeScanned);
                    toBeScanned = toBeScanned.substring(operatorString.length());

                    switch(operatorString) {
                        case "+":
                            if(currentGroup.tokens.size() == 0) {
                                //+ is the first operator, hence Unary positive.
                                currentGroup.tokens.add(new UnaryOperator(UnaryOperator.UnaryOperatorType.positive));
                            }
                            else {
                                //Binary param addition operator
                                currentGroup.tokens.add(new AdditiveOperator(AdditiveOperator.AdditiveOperatorType.Addition));
                            }
                            break;
                    }
                }
                //Identifier mode
                else {
                    //Mark the current identifier as scanned; remove the cIBNO from toBeScanned
                    toBeScanned = toBeScanned.substring(currentIdentifierBeforeNextOperator.length());
                    NativeValue.NativeType nativeType = P_Literal.getTypeOfLiteral(currentIdentifierBeforeNextOperator);
                    if(nativeType != null) {
                        //Is a native type
                        currentGroup.add(new Literal(currentIdentifierBeforeNextOperator));
                    }
                    else {
                        //Is a reference identifier. (Variable name, method name)
                        currentGroup.add(new Identifier(currentIdentifierBeforeNextOperator));
                    }
                }
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
            boolean deciamalPointEncountered = false;//If true, the next . will be considered a . possesion operator.

            for(int i = 0; i < expression.length(); i++) {
                String curr = String.valueOf(expression.charAt(i));
                String next = i + 1 < expression.length() ? String.valueOf(expression.charAt(i + 1)) : "";
                String prev = i - 1 >= 0 ? String.valueOf(expression.charAt(i - 1)) : "";

                if(Helper.isAlphaNumeric(curr))//Simple
                    returnable += curr;
                else if(curr.equals(".") && !deciamalPointEncountered
                        && !(Helper.isLetter(next) || Helper.isLetter(prev))) {//Decimal points are not possession ops
                    returnable += curr;
                    deciamalPointEncountered = true;
                }
                else//Returnit.exe
                    return returnable;
            }

            return returnable;
        }

        private static String returnStringUntilNextIdentifier(String expression) {
            String returnable = "";

            for(int i = 0; i < expression.length(); i++) {
                String curr = String.valueOf(expression.charAt(i));

                if(Helper.isAlphaNumeric(curr))
                    return returnable;
                else
                    returnable += curr;
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
        private static Pattern numberPattern = Pattern.compile("$[0-9]+$");
        private static Pattern letterPattern = Pattern.compile("^[A-Za-z_]+$");
        private static Pattern whiteSpacePattern = Pattern.compile("^\\s+$");
        private static Pattern notOperatorPattern = Pattern.compile("^[A-Za-z0-9_]*$");

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

        /**
         * Returns true if input is alphanumeric.
         * @param test input test
         */
        public static boolean isAlphaNumeric(String test) {
            return identifierPattern.matcher(test).matches();
        }

        /**
         * Return true if input consists of only 0-9 (No decimal points!)
         * @param test input test
         */
        public static boolean isNumber(String test) {
            return numberPattern.matcher(test).matches();
        }

        /**
         * Return true if input consists of only letters and underscores
         * @param test input test
         */
        public static boolean isLetter(String test) {
            return letterPattern.matcher(test).matches();
        }

        /**
         * Return true if input consists of only whitespace
         * @param test input test
         */
        public static boolean isWhiteSpace(String test) {
            return whiteSpacePattern.matcher(test).matches();
        }

        /**
         * Return true if input is not an operator. Whitespace is also counted as not an operator
         * @param test input test
         * @return
         */
        public static boolean isNotOperator(String test) {
            return notOperatorPattern.matcher(test).matches();
        }
    }
}
