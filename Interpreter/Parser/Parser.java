package Parser;

import Elements.*;
import Elements.Error;
import Token.*;

import java.util.ArrayList;
import java.util.regex.Pattern;
import Token.Operator.*;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

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
         * Basically a Lexer solely for expressions
         * @param expression An expression
         * @return Returns a TokenGroup of the TokenizedExpression
         */
        public static TokenGroup TokenizeExpression(String expression)
        {
            TokenGroup currentGroup = new TokenGroup();
            String toBeScanned = expression;
            CodePosition currentCodePosition = new CodePosition();

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
                                //+ is the first operator, hence Unary Positive.
                                UnaryOperator op = new UnaryOperator(UnaryOperator.UnaryOperatorType.Positive);
                                op.setPosition(currentCodePosition);
                                currentGroup.tokens.add(op);
                            }
                            else {
                                //Binary param addition operator
                                AdditiveOperator op = new AdditiveOperator(AdditiveOperator.AdditiveOperatorType.Addition);
                                op.setPosition(currentCodePosition);
                                currentGroup.tokens.add(op);
                            }
                            break;
                        case "-":
                            if(currentGroup.tokens.size() == 0) {
                                //+ is the first operator, hence Unary Positive.
                                UnaryOperator op = new UnaryOperator(UnaryOperator.UnaryOperatorType.Negative);
                                op.setPosition(currentCodePosition);
                                currentGroup.tokens.add(op);
                            }
                            else {
                                //Binary param addition operator
                                AdditiveOperator op = new AdditiveOperator(AdditiveOperator.AdditiveOperatorType.Subtraction);
                                op.setPosition(currentCodePosition);
                                currentGroup.tokens.add(op);
                            }
                            break;
                    }

                    currentCodePosition.increment(operatorString);
                }
                //Identifier mode
                else {
                    //Mark the current identifier as scanned; remove the cIBNO from toBeScanned
                    toBeScanned = toBeScanned.substring(currentIdentifierBeforeNextOperator.length());
                    NativeValue.NativeType nativeType = P_Literal.getTypeOfLiteral(currentIdentifierBeforeNextOperator);
                    if(nativeType != null) {
                        //Is a native type
                        Literal lit = new Literal(currentIdentifierBeforeNextOperator);
                        lit.setPosition(currentCodePosition);
                        currentGroup.add(lit);
                    }
                    else {
                        //Is a reference identifier. (Variable name, method name)
                        Identifier iden = new Identifier(currentIdentifierBeforeNextOperator);
                        iden.setPosition(currentCodePosition);
                        currentGroup.add(iden);
                    }

                    currentCodePosition.increment(currentIdentifierBeforeNextOperator);
                }
                //TODO:
            }

            return currentGroup;
        }

        /**
         * Checks a String and returns everything until the next operator (excluding the operator)
         * @param expression A String to check for
         * @return Returns everything before the next operator.
         *         Returns "" if the first char is an operator.
         */
        private static String returnStringUntilNextOperator(String expression) {
            String returnable = "";
            boolean deciamalPointEncountered = false;//If true, the next . will be considered a . possesion operator.

            for(int i = 0; i < expression.length(); i++) {
                String curr = String.valueOf(expression.charAt(i));
                String next = i + 1 < expression.length() ? String.valueOf(expression.charAt(i + 1)) : "";
                String prev = i - 1 >= 0 ? String.valueOf(expression.charAt(i - 1)) : "";

                if(Helper.isAlphaNumeric(curr) || Helper.isWhiteSpace(curr))//Simple
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

        /**
         * Checks a String and returns everything until the next identifier (excluding the first char of the identifier)
         * @param expression A String to check for
         * @return Returns everything before the next Identifier.
         *         Returns "" if the first char is not an operator (whitespace inclusive).
         */
        private static String returnStringUntilNextIdentifier(String expression) {
            String returnable = "";

            for(int i = 0; i < expression.length(); i++) {
                String curr = String.valueOf(expression.charAt(i));

                if(Helper.isAlphaNumeric(curr) || Helper.isWhiteSpace(curr))
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
            CodeHelper helper = new CodeHelper();
            String returnable = "";

            for(int i = 0; i < expression.length(); i++) {
                String currStr = String.valueOf(expression.charAt(i));
                helper.scan(currStr);
                returnable += helper.currentlyIsString ? "" : currStr;
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
                if(currToken instanceof Operator && ((Operator) currToken).opGrp.compareTo(opGrp) > 0) {//If is of lower precedence
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

        /**
         * To split based on a syntax grammar, ensuring that only top-level code is split.
         *
         * Code within literal string quotations, brackets, braces, array index square brackets, type arrow brackets
         * will not be split.
         *
         * @param stringToSplit The string to process
         * @param splitDeterminer The operator/grammar sign to check for to split the string.
         * @return Returns an ArrayList&lt;String&gt; of all the splits, of which therein absents the splitDeterminer
         */
        public static ArrayList<String> smartSplit (String stringToSplit, String splitDeterminer) {
            ArrayList<String> returnable = new ArrayList<>();



            return returnable;
        }
    }

    public static class TreeParsing
    {
        /**
         * Convert tokens into a ParseTree. <p>
         * A recursive function...
         * This function creates branches based on operator precedence. Branches are split by TokenGroups
         * Brackets are already handled during the lexing process.
         *
         *
         * @param tokens The input Lexed tokens
         * @return Returns a tree parsed TokenGroup using other TokenGroups as nodes.
         *         Should there be an error, returns an {@link Error} instead..
         */
        public static Token parse (TokenGroup tokens)
        {
            //TODO:
            TokenGroup returnable = new TokenGroup();
            OperatorGroup lowestPrecedenceOperator = Helper.getLowestPrecedenceOperator(tokens);

            //Create a lowest Precendence split
            TokenGroup lowestPrecedenceSplit = splitBy(lowestPrecedenceOperator, tokens);

            if(lowestPrecedenceOperator != OperatorGroup.Primary && tokens.size() > 1) {
                //Not primary, continue recursing...
                for (Token t : lowestPrecedenceSplit.tokens) {
                    //Iterate through each TokenGroup.
                    if (t instanceof TokenGroup) {
                        returnable.add(parse((TokenGroup) t));
                    }
                    else {
                        returnable.add(t);
                    }
                }
            }
            else
            {
                //Stop recursing at Primary or if only a single token present...
                return tokens;
            }

            return returnable;
        }

        /**
         * Splits a {@link TokenGroup} into a set of TokenGroups separated by an operator of specific precedence.
         * @param opSplit The {@link OperatorGroup} to split by.
         * @param sampleSpace The input TokenGroup
         * @return Returns a TokenGroup containing TokenGroups encapsulating Tokens, separated by Operators of the given {@link OperatorGroup}.
         *         If no splits at all, simply return the input sample space.
         */
        private static TokenGroup splitBy(OperatorGroup opSplit, TokenGroup sampleSpace) {
            TokenGroup returnable = new TokenGroup();
            TokenGroup currentSplit = new TokenGroup();

            for(Token t : sampleSpace.tokens) {
                if(t instanceof Operator && ((Operator) t).opGrp == opSplit) {
                    if(currentSplit.size() != 0) returnable.add(currentSplit);
                    returnable.add(t);
                    currentSplit = new TokenGroup();
                }
                else {
                    currentSplit.add(t);
                }
            }

            if(currentSplit.size() != 0) returnable.add(currentSplit);

            return returnable;
        }
    }

    public static class SyntaxHandler
    {
        public static class BraceTypeImbalances
        {
            /**
             * Returns the number of extra opening brackets, (A value of -2 means 2 missing closing brackets)
             *
             * These are brackets ()
             *
             * E.g: ((( )) will return 1
             *
             * @param testCase The String to test for
             * @return Returns the number of extra brackets
             */
            public static int getBracketImbalance (String testCase) {
                String noStringLiteral = Parser.Helper.removeAllStringLiterals(testCase);
                int unclosedOpeningBrackets = 0;

                for(int i = 0; i < noStringLiteral.length(); i++) {
                    String curr = String.valueOf(noStringLiteral.charAt(i));

                    if (curr.equals("(")) {
                        unclosedOpeningBrackets++;
                    } else if (curr.equals(")")) {
                        unclosedOpeningBrackets--;
                    }
                }

                return unclosedOpeningBrackets;
            }


        }
    }
}
