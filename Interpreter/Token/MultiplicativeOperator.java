package Token;

/**
 * 3rd order
 */
public class MultiplicativeOperator extends Operator {
    public MultiplicativeOperatorType type;

    public MultiplicativeOperator(MultiplicativeOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Multiplicative;
    }

    public enum MultiplicativeOperatorType {
        Multiplication, Division, Modulo
    }
}
