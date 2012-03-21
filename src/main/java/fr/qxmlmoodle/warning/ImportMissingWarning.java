package fr.qxmlmoodle.warning;


/** Class ImportMissingWarning warning concern missing. */
public class ImportMissingWarning extends ImportWarning {


    /** Default constructor. */
    public ImportMissingWarning() {
        super(ImportWarningType.MISSING);
    }


    /** Specific constructor.
     * @param line line of warning
     * @param missingValue the missing value
     */
    public ImportMissingWarning(final int line, final String missingValue) {
        super(ImportWarningType.MISSING, line , "",
                "<" + missingValue + "> is missing");
    }


}
