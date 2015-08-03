package Token;

/**
 * 4th order
 */
public class AdditiveOperator extends Operator{
    public AdditiveOperatorType type;

    public AdditiveOperator(AdditiveOperatorType type){
        this.type = type;
        this.opGrp = OperatorGroup.Additive;
    }

    public enum AdditiveOperatorType
    {
        Addition, Subtraction
    }
}
