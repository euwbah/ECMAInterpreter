package Token;

import Elements.*;

/**
 * A Literal token containing a NativeValue of the literal, since it's constant.
 */
public class Literal extends Token
{
    /**
     * The constant NativeValue of the literal
     */
	public NativeValue literalValue;
    /**
     * Just the pure literal string
     */
    public String rawLiteralString;

    /**
     * Creates a new Literal using a raw String to parse and assign Literal.literalValue;
     * @param rawLiteralString
     */
    public Literal(String rawLiteralString)
    {
        this.rawLiteralString = rawLiteralString.trim();//Don't need no whitespace..
        this.literalValue = new NativeValue(this.rawLiteralString);
    }
}
