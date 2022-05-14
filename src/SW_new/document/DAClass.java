package SW_new.document;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Dialogue acts enum - type of classes
 * @author Long
 * @version 1.0
 */
public enum DAClass {
    BACKCHANNEL("BACKCHANNEL"), BYE("BYE"), CLOSE("CLOSE"), COMMIT("COMMIT"), FEEDBACK("FEEDBACK"),
    GREET("GREET"), INFORM("INFORM"), NOT_CLASSIFIABLE("NOT_CLASSIFIABLE"), OFFER("OFFER"),
    ORDER("ORDER"), OR_QUESTION("OR_QUESTION"), POLITENESS_FORMULA("POLITENESS_FORMULA"),
    THANK("THANK"), WHY_QUESTION("WHY_QUESTION"), YES_NO_QUESTION("YES-NO_QUESTION");

    /** Name taken from text file */
    private final String className;
    /** Map to return correct DAClass by value */
    private static final Map<String, DAClass> classNames;

    // initializing Map
    static {
        classNames = new HashMap<>();
        for (DAClass daClass: EnumSet.allOf(DAClass.class)) {
            classNames.put(daClass.className, daClass);
        }
    }

    /**
     * DAClass enum constructor
     * @param s String name of DAClass
     */
    DAClass(String s) {
        this.className = s;
    }

    /**
     * Method returns DAClass by inputted String name
     * @param s name of DAClass to be returned
     * @return DAClass
     */
    public static DAClass getDAClass(String s) {
        return classNames.get(s);
    }
}
