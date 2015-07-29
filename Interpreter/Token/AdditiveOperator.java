package Token;

/**
 * Created by Matthew on 28/7/2015.
 */
public class AdditiveOperator extends Token{
    public AdditiveOperatorType type;

    public AdditiveOperator(AdditiveOperatorType type){
        this.type = type;
    }

    public enum AdditiveOperatorType
    {
        Addition, Subtraction
    }
}
