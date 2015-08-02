package Parser;

/**
 * A data-type class storing a single position of a char based on lines and chars.
 * Works as a cursor...
 */
public class CodePosition
{
    public int linePos;
    public int charPosByLine;
    public int netCharPos;

    public CodePosition() {
        linePos = 0;
        charPosByLine = 0;
    }

    public CodePosition(int linePos, int charPosByLine) {
        this.linePos = linePos;
        this.charPosByLine = charPosByLine;
    }

    /**
     * To increment the code position value based on a given String
     * Used like a cursor. Moves the position of the 'cursor' to proceed last char of the given String
     * @param stringAddOn The String of which the cursor should be proceeding the last char.
     */
    public void increment(String stringAddOn) {
        for(char c : stringAddOn.toCharArray()) {
            String s = String.valueOf(c);

            if(s.equals("\n")) {
                this.linePos ++;
                this.netCharPos ++;
                this.charPosByLine = 0;
            }
            else {
                this.charPosByLine++;
            }
        }
    }

    public String toString() {
        return "{ l: " + this.linePos + ", c: " + this.charPosByLine + " }";
    }
}
