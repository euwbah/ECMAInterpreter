package Token;

/**
 * Created by Matthew on 28/7/2015.
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
