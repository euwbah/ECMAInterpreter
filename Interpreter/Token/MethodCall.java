package Token;

import java.util.ArrayList;

/**
 * Created by Matthew on 31/7/2015.
 */
public class MethodCall extends PrimaryOperator {
    public ArrayList<String> params;

    /**
     * Initialises an empty method call token with no parameters.
     */
    public MethodCall() {
        params = new ArrayList<String>();
    }

    /**
     * Initialises a method call token using a raw string of comma-separated parameters.
     * @param rawParams A single string containing comma-separated parameters
     */
    public MethodCall(String rawParams) {
        String[] splitParams = rawParams.split(",");

        this.params = new ArrayList<String>();
        for(String s : splitParams) params.add(s);
    }
}
