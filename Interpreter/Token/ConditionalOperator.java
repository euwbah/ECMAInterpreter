package Token;

/**
 * Created by Matthew on 3/8/2015.
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
