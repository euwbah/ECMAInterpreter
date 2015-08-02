package Parser;

import Elements.*;
import Elements.Class;
import Elements.Object;
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
        Object returnable = new Object();
        return returnable;
    }
}
