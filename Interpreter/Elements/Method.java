package Elements;

import Token.TokenGroup;

import java.util.ArrayList;

/**
 * Method class.
 * Contains a TokenGroup of all the statements to evaluate and execute,
 * and an ArrayList&lt;Class&gt; of local variables...
 * Function parameters are evaluated upon init of class, and their refNames are stored in localVariables
 */
public class Method extends Object{
    public TokenGroup tokens;
    public ArrayList<Class> localVariables;

    public Object doMethod(Class context) {
        //TODO
        return null;
    }
}
