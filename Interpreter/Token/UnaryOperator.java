package Token;

/**
 * Created by Matthew on 29/7/2015.
 */
public class UnaryOperator extends Operator {
    public UnaryOperatorType type;

    public UnaryOperator(UnaryOperatorType type) {
        this.type = type;
    }

    public enum UnaryOperatorType {
        positive, negative, negation,
    }
}
