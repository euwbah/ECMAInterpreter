package Token;

/**
 * Created by Matthew on 29/7/2015.
 */
public class UnaryOperator extends Operator {
    public UnaryOperatorType type;

    public UnaryOperator(UnaryOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Unary;
    }

    public enum UnaryOperatorType {
        Positive, Negative, Negation,
    }
}
