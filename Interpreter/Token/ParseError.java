package Token;

import Parser.Parser.*;

/**
 * Parse error class for passing errors during lexing and parsing.
 */
public class ParseError extends Token {
    public String errorMessage;
    public ParseErrorType type;

    public ParseError(String errorMessage, ParseErrorType type) {
        this.errorMessage = errorMessage;
        this.type = type;
    }

    public enum ParseErrorType {
        NativeError
    }
}
