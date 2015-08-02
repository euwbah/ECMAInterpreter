package Token;

import Parser.CodePosition;

/**
 * Created by Matthew on 28/7/2015.
 */
public class Token {

    private CodePosition position;

    /**
     * Set the position of the token.
     * Usually this position refers to the position of the first character of this token.
     * @param pos
     */
    public void setPosition(CodePosition pos) {
        //Create an unreferenced clone, since CodePosition.increment is a referenced function.
        this.position = new CodePosition(pos);
    }

    /**
     * Nullable and Referenced! Gets the position of the token.
     * Not applicable for TokenGroups, only intended for Operators, Identifiers, Literals and Structures.
     * @return returns a {@link CodePosition} or null.
     */
    public CodePosition getPosition() {
        if (position != null) {
            return this.position;
        }
        return null;
    }
}
