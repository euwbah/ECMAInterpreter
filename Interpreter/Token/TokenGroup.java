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
}
