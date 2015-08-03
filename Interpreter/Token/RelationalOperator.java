package Token;

/**
 * 5th order
 */
public class RelationalOperator extends Operator {
    public RelationalOperatorType type;

    public RelationalOperator(RelationalOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Relational;
    }

    public enum RelationalOperatorType {
        LessThan, LessThanOrEqualTo, MoreThanOrEqualTo, MoreThan
    }
}
