package Token;

/**
 * 1st Order
 */
public class PrimaryOperator extends Operator {
    public PrimaryOperatorType type;

    public PrimaryOperator() {
        this.opGrp = OperatorGroup.Primary;
    }
    public PrimaryOperator(PrimaryOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Primary;
    }

    public enum PrimaryOperatorType {
        possession, methodCall, arrayIndex, postIncrement, postDecrement, initialiser
    }
}
