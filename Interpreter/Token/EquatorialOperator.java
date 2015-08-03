package Token;

/**
 * 6th order
 */
public class EquatorialOperator extends Operator {
    public EquatorialOperatorType type;

    public EquatorialOperator(EquatorialOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Equatorial;
    }

    public enum EquatorialOperatorType {
        EqualsTo, NotEqualsTo
    }
}
