package Token;

/**
 * Created by Matthew on 28/7/2015.
 */
public class PrimaryOperator extends Operator {
    public PrimaryOperatorType type;

    public PrimaryOperator() {};
    public PrimaryOperator(PrimaryOperatorType type) {
        this.type = type;
    }

    public enum PrimaryOperatorType {
        possession, methodCall, arrayIndex, postIncrement, postDecrement, initialiser
    }
}
