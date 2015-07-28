package Token;

/**
 * Created by Matthew on 28/7/2015.
 */
public class PossessionOperator extends PrimaryOperator{
    public PossessionOperator(){
        this.opGrp = OperatorGroup.Primary;
        this.type = PrimaryOperatorType.possession;
    }
}
