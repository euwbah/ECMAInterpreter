package Token;

import ObjectTypes.*;

public class Literal extends Token
{
	public NativeValue literalValue;
    public String rawLiteralString;

    public Literal(String rawLiteralString)
    {
        this.rawLiteralString = rawLiteralString;
        this.literalValue = new NativeValue(rawLiteralString);
    }
}
