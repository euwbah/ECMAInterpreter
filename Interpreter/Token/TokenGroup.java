package Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 28/7/2015.
 */
public class TokenGroup extends Token {
    public ArrayList<Token> tokens;

    public TokenGroup() {
        this.tokens = new ArrayList<Token>();
    }

    public TokenGroup(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public String toString() {
        String returnable = "";
        for(Token t : tokens) {
            if(t instanceof UnaryOperator) {
                returnable += "{" + ((UnaryOperator) t).type.toString() + "}";
            }
            else if(t instanceof Literal) {
                returnable += "{Literal " + ((Literal) t).rawLiteralString + "::" + ((Literal) t).literalValue.type.toString() + "}";
            }
            else if(t instanceof Identifier) {
                returnable += "{Identifier " + ((Identifier) t).name + "}";
            }
        }

        return returnable;
    }

    public void add(Token token) {
        this.tokens.add(token);
    }

    public int size() {return this.tokens.size();}

    /**
     * Gets a Token from the given index. Nullable!
     * If null or out of range, returns null.
     * @param index The index of the Token in the ArrayList&lt;Token&gt;
     * @return Returns either a Token or null.
     */
    public Token get(int index) {
        return this.tokens != null && this.tokens.size() > index ? this.tokens.get(index) : null;
    }

    /**
     * Gets a TokenGroup from the specified range. Nullable!
     * If null or out of range, returns null.
     *
     * @param startIndex The zero-based index of the starting point
     * @param size The number of tokens to get starting from the startIndex
     * @return Returns either a TokenGroup or null.
     */
    public TokenGroup subList(int startIndex, int size) {
        TokenGroup returnable = new TokenGroup();
        for(int index = startIndex; index < startIndex + size - 1; index ++) {
            if(index < this.size() && index >= 0)
                returnable.add(this.get(index));
            else
                return null;
        }

        return returnable;
    }

    public TokenGroup subList(int fromIndex) {
        if(fromIndex < 0) return null;
        //else
        TokenGroup returnable = new TokenGroup();
        for(int index = fromIndex; index < this.size(); index++) {
            returnable.add(this.get(index));
        }

        return returnable;
    }
}
