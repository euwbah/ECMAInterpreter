package Elements;

import Parser.Parser.P_Literal;

/**
 * Elements.NativeValue class.&nbsp;Used in Variable as well as Literals
 */
public class NativeValue {
    private double rawNumber;
    private boolean rawBoolean;
    /**
     * IMPORTANT: This rawString DOES contain escape sequences.
     */
    private String rawString;

    public NativeType type;

    /**
     * Infer from a literal, or assign as rawString if no match.
     *
     * @param literalOrString Either a literal token or a rawString filled with escape sequences.
     */
    public NativeValue(String literalOrString) {
        if (P_Literal.isBool(literalOrString)) {
            this.type = NativeType.bool;
            this.rawBoolean = Boolean.parseBoolean(literalOrString.trim());
        } else if (P_Literal.isNumber(literalOrString)) {
            this.type = NativeType.number;
            this.rawNumber = Double.parseDouble(literalOrString.trim());
        } else {//String, works both ways :P
            this.type = NativeType.string;
            this.rawString = literalOrString;
        }
    }

    /**
     * Directly set as number
     * @param rawNumber Number value to set as
     */
    public NativeValue(double rawNumber) {
        this.rawNumber = rawNumber;
        this.type = NativeType.number;
    }

    /**
     * Directly set as boolean
     * @param rawBoolean Boolean value to set as
     */
    public NativeValue(boolean rawBoolean) {
        this.rawBoolean = rawBoolean;
        this.type = NativeType.bool;
    }

    /**
     * Get the value of rawString
     * @param renderString When true, rawString will be rendered and output will be a true string.
     *                     Else, rawString will just be output as a literal.
     * @return Returns rawString as is, or rendered as a true string.
     */
    public String getRawString(boolean renderString) {
        String returnable = "";
        if (renderString) {
            for (int i = 0; i < rawString.length(); i++) {
                String curr = String.valueOf(rawString.charAt(i));
                String next = i + 1 < rawString.length() ? String.valueOf(rawString.charAt(i + 1)) : "";

                if (curr.equals("\\")) {
                    switch (next) {
                        case "\\":
                            returnable += "\\";
                            break;
                        case "n":
                            returnable += "\n";
                            break;
                        case "r":
                            returnable += "\r";
                            break;
                        case "t":
                            returnable += "\t";
                            break;
                        case "\'":
                            returnable += "\'";
                            break;
                        case "\"":
                            returnable += "\"";
                            break;
                    }
                    i++;
                }
                else
                {
                    returnable += curr;
                }
            }
        } else {
            returnable = rawString;
        }
        return returnable;
    }

    /**
     * Get number value
     * @return Returns the number value as a double
     */
    public double getRawNumber() {
        return this.rawNumber;
    }

    /**
     * Get boolean value
     * @return Returns the boolean value as a boolean
     */
    public boolean getRawBoolean() {
        return this.rawBoolean;
    }

    public enum NativeType {
        number, string, bool
    }
}