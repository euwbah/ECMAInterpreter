package Token;

import Parser.Parser.*;

/**
 * Parse error class for passing errors during lexing and parsing.
 */
public class ParseError extends Token {
    public String errorMessage;
    public String errorDenomination;
    public ParseErrorType type;

    public ParseError(String errorMessage, ParseErrorType type, String errorDenomination) {
        this.errorMessage = errorMessage;
        this.type = type;
        this.errorDenomination = errorDenomination;
    }

    public enum ParseErrorType {
        NativeError, SyntaxError
    }
}
