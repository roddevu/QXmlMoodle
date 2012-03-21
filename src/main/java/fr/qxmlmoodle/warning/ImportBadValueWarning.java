package fr.qxmlmoodle.warning;

/** Class ImportBadValueWarning warning concern bad value. */
public class ImportBadValueWarning extends ImportWarning {


    /** Default constructor. */
    public ImportBadValueWarning() {
        super(ImportWarningType.BAD_VALUE);
    }

    /** Specific constructor.
     * @param line line of warning
     * @param value the bad value
     */
    public ImportBadValueWarning(final int line, final String value) {
        super(ImportWarningType.BAD_VALUE, line , "",
                "<" + value + "> is a bad value");
    }

    /** Specific constructor.
     * @param line line of warning
     * @param value the bad value
     * @param desc the description of the warning
     */
    public ImportBadValueWarning(final int line, final String value,
                                 final String desc) {
        super(ImportWarningType.BAD_VALUE, line , "",
                "<" + value + "> is a bad value : " + desc);
    }

}
