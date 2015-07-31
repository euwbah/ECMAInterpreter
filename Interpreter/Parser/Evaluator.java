package Parser;

import Token.*;

/**
 * Master evaluator class.
 * Given a context and some expressions or statements, will evaluate it.
 */
public class Evaluator {
    /**
     * Evaluate a expression in the form of a TokenGroup.
     * @param expression A TokenGroup; a tokenized version of the expression
     * @param context A Class which denotes where should all the identifiers derive from.
     * @return Returns an Object. If successful, instanceof Class, else Error.
     */
    public static Object EvaluateExpression(TokenGroup expression, Class context) {
        Object returnable;

        //The current split in this iteration of the recursion.
        Operator.OperatorGroup currentOperatorSplit = Parser.Helper.getLowestPrecedenceOperator(expression);
        switch(currentOperatorSplit) {
            case Primary:
                break;
            case Unary:

                for(Token t : expression.tokens) {
                    /*
                     * In this case, there should only be two tokens in this format:
                     * UnaryOperator | TokenGroup ==> - ( 13 * 4 + !derp ? 3 : 4 ) ~~~ Note that TokenGroup can also contain just a single token
                     */
                    if(t instanceof Operator) {

                    }
                }
                break;
            case Multiplicative:
                break;
            case Additive:
                break;
            case Relational:
                break;
            case Equatorial:
                break;
            case ConditionalAND:
                break;
            case ConditionalOR:
                break;
            case Conditional:
                break;
            case Assignement:
                break;
        }
    }
}
