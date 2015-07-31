package Elements;

/**
 * The master Object class of which everything is derived from...
 * The following classes extend the Object class:
 * @see Class
 * @see Error
 * @see Method
 */
public class Object {
    /**
     * The object's reference name.
     */
    public String refName;

    public Object(String refName)
    {
        this.refName = refName;
    }

    public Object() {

    }
}
