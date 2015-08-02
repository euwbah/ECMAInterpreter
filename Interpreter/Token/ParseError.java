package Token;

import Parser.Parser.*;

/**
 * Parse error class for passing errors during lexing and parsing.
 */
public class ParseError extends Token {
    public String errorMessage;

    public ParseError(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
