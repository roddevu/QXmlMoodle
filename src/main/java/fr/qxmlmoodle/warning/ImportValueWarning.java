package fr.qxmlmoodle.warning;


/** Class ImportValueWarning warning concern value. */
public class ImportValueWarning extends ImportWarning {


    /** Default constructor. */
    public ImportValueWarning() {
        super(ImportWarningType.UNKNOWN_VALUE);
    }


    /** Specific constructor.
     * @param line line of warning
     * @param value the unknown value
     */
    public ImportValueWarning(final int line, final String value) {
        super(ImportWarningType.UNKNOWN_VALUE, line , "",
                "<" + value + "> is an unknown value");
    }


}
