package Token;

import java.util.ArrayList;

/**
 * Created by Matthew on 31/7/2015.
 */
public class MethodCall extends PrimaryOperator {
    /**
     * A list of TokenGroups containing fully parsed expressions..
     */
    public ArrayList<TokenGroup> listOfParameterExpressions;

    /**
     * Initialises an empty method call token with no parameters.
     */
    public MethodCall() {
        listOfParameterExpressions = new ArrayList<>();
    }

    /**
     * Initialises a method call token using a raw string of comma-separated parameters.
     * @param rawParams A single string containing comma-separated parameters
     */
    public MethodCall(String rawParams) {

    }
}
