import java.util.List;

/**
 * Created by Matthew on 28/7/2015.
 */
public class TokenGroup extends Token {
    public List<Token> tokens;

    public TokenGroup() {

    }

    public TokenGroup(List<Token> tokens) {
        this.tokens = tokens;
    }
}
