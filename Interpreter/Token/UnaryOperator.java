package Token;

/**
 * 2nd order
 */
public class UnaryOperator extends Operator {
    public UnaryOperatorType type;

    public UnaryOperator(UnaryOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Unary;
    }

    public enum UnaryOperatorType {
        Positive, Negative, Negation, PreIncrement, PreDecrement
    }
}
