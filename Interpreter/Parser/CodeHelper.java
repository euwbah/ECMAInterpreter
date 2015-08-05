package Parser;

/**
 * A code helper for fundamental syntax parsing
 * E.g. Check if is within a string literal.
 */
public class CodeHelper {
    public boolean currentlyIsString;
    private boolean isDoubleQuote;
    private boolean endStringFlag;
    private String checkedString;

    public CodeHelper() {
        this.currentlyIsString = false;
        this.isDoubleQuote = false;
        this.checkedString = "";

        endStringFlag = false;
    }

    /**
     * Scan method: Pass the input string to scan.
     * Attributes of the current string will be within {@link CodePosition} class itself
     *
     * @param s Input string to scan
     */
    public void scan(String s) {
        String usable = checkedString.trim().endsWith("\\") ? s.substring(1) : s;

        checkedString += s;

        //To make sure that the ending quotation marks won't appear...
        if(!currentlyIsString) endStringFlag = false;
        if(endStringFlag) currentlyIsString = false;

        for(int i = 0; i < usable.length(); i++) {
            String curr = String.valueOf(usable.charAt(i));

            if(!currentlyIsString) {
                if(curr.equals("'")) {
                    currentlyIsString = true;
                    isDoubleQuote = false;
                }
                else if(curr.equals("\"")) {
                    currentlyIsString = true;
                    isDoubleQuote = true;
                }
            }
            else {
                if((isDoubleQuote && curr.equals("\"")) ||
                        (!isDoubleQuote && curr.equals("'"))) {
                    endStringFlag = true;
                }
                else if(curr.equals("\\")) {
                    i++;//Skip the next char...
                }
            }
        }
    }
}
