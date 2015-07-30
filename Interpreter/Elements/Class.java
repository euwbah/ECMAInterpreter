package Elements;

/**
 * Created by Matthew on 30/7/2015.
 */
public class Class extends Object {

    public Type type;
    public NativeValue nativeValue;

    public Class(NativeValue nativeValue) {
        this.type = Type.NativeObject;
    }

    /**
     * The functioning type of this class.
     * Types are the pure declarations of the classes - such types of Class should be initialised with the initialise function
     * Objects are already initialised classes and are treated as variables.
     *
     */
    public enum Type {
        ClassType, ClassObject, NativeType, NativeObject
    }
}
