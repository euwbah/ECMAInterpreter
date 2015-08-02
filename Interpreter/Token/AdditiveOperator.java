package Token;

/**
 * Created by Matthew on 28/7/2015.
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
