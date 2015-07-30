package Elements;

import sun.awt.SunToolkit;

/**
 * Created by Matthew on 30/7/2015.
 */
public class Error extends Object {
    public String errorMessage;
    public String codeInvokingError;

    /**
     * Error constructor
     * @param errorMessage The errorMessage to pass
     * @param codeInvokingError The specific identifier/operator/expression token of which caused the error.
     */
    public Error(String errorMessage, String codeInvokingError) {
        this.errorMessage = errorMessage;
        this.codeInvokingError = codeInvokingError;
    }

    /**
     * Not recommended. Use {@link #Error(String, String) Error(String, String)} instead.
     * @param errorMessage The errorMessage to pass.
     */
    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
