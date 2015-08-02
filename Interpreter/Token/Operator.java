/**
 * Created by Matthew on 28/7/2015.
 */

package Token;

/**
 * Main operator class.
 *
 * @see AdditiveOperator
 * @see PrimaryOperator
 * @see UnaryOperator
 */
public class Operator extends Token{
    public OperatorGroup opGrp;

    public enum OperatorGroup {
        /**
         * 1st order; split by '.&nbsp;'.
         * f(x) | x[i] | . | x++ | x-- | new
         */
        Primary,
        /**
         * 2nd order; no split.
         * ++x | --x | !x | +x | -x
         */
        Unary,
        /**
         * 3rd order; split by all operators
         * * | / | %
         */
        Multiplicative,
        /**
         * 4th order; split by all operators
         * + | -
         */
        Additive,
        /**
         * 5th order; split by all operators
         * &lt | &lt= | &gt | &gt=
         */
        Relational,
        /**
         * 6th order; split by all operators
         * == | !=
         */
        Equatorial,
        /**
         * 7th order; split by all operators
         */
        ConditionalAND,
        /**
         * 8th order; split by all operators
         */
        ConditionalOR,
        /**
         * 9th order; split by all operators
         * condition ? true : false
         */
        Conditional,
        /**
         * 10th order; split by all operators. Right-associative
         * = | += | -= | *= | /= | %=
         */
        Assignement
    }

    public String toString() {
        String returnable = "Undefined";
        switch(opGrp) {
            case Primary:
                returnable = "Primary::";
                returnable += ((PrimaryOperator) this).type.toString();
                break;
            case Unary:
                returnable = "Unary::";
                returnable += ((UnaryOperator) this).type.toString();
                break;
            case Multiplicative:
                returnable = "Multiplicative::";
                break;
            case Additive:
                returnable = "Additive::";
                returnable += ((AdditiveOperator) this).type.toString();
                break;
            case Relational:
                returnable = "Relational::";
                break;
            case Equatorial:
                returnable = "Equatorial::";
                break;
            case ConditionalAND:
                returnable = "Conditional And::";
                break;
            case ConditionalOR:
                returnable = "Conditional Or::";
                break;
            case Conditional:
                returnable = "Conditional[?:]::";
                break;
            case Assignement:
                returnable = "Assignment::";
                break;
        }

        return returnable;
    }
}
