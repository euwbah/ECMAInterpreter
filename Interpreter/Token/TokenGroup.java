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
                returnable += ((UnaryOperator) t).type.toString();
            }
        }

        return returnable;
    }
}
