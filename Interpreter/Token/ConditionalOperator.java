package Token;

/**
 * 9th order
 */
public class ConditionalOperator extends Operator {
    public ConditionalOperatorType type;

    public ConditionalOperator(ConditionalOperatorType type) {
        this.type = type;
        this.opGrp = OperatorGroup.Conditional;
    }

    public enum ConditionalOperatorType {
        /**
         * AKA the Question Mark '?'
         */
        ConditionalMarker,
        /**
         * AKA the Colon ':'
         */
        IfElseCaseMarker
    }
}
