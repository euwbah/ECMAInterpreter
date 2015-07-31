package Elements;

import java.util.ArrayList;

/**
 * Created by Matthew on 30/7/2015.
 */
public class Class extends Object {

    /**
     * The functioning type of this class.
     * Aka, whether if it is a variable or a type waiting to be instantiated with the 'new' keyword.
     */
    public Type type;
    /**
     * The NativeValue, if any.
     * Only used when Class.Type = Class.Type.NativeObject
     */
    public NativeValue nativeValue;

    public Method initialisationMethod;

    public ArrayList<Class> localVariables;
    public ArrayList<Method> localMethods;

    public boolean isStatic;

    /**
     * Instantiates a new native object variable.
     * @param nativeValue
     */
    public Class(NativeValue nativeValue) {
        this.type = Type.NativeObject;
        isStatic = false;
        this.localVariables = new ArrayList<Class>();
        this.localMethods = new ArrayList<Method>();
    }

    /**
     * Defines a new custom class Type.
     * @param localVariables
     */
    public Class(ArrayList<Class> localVariables) {
        this.localVariables = new ArrayList<Class>();
        this.localMethods = new ArrayList<Method>();
    }

    /**
     * Creates an empty null object variable class.
     */
    public Class() {
        this.type = Type.Null;
        this.localVariables = new ArrayList<>();
        this.localMethods = new ArrayList<>();
    }

    public Object Instantiate() {
        //TODO;
        Class instance;

        return new Error("Unable to instantiate");
    }

    /**
     * The functioning type of this class.
     * Types are the pure declarations of the classes - such types of Class should be initialised with the initialise function
     * Objects are already initialised classes and are treated as variables.
     *
     */
    public enum Type {
        ClassType, ClassObject, NativeType, NativeObject,
        /**
         * Important: Null is a kind of ClassObject, just that its value is not set
         */
        Null
    }
}
