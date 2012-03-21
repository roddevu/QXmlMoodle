package fr.qxmlmoodle.exception;



/** Class ImportValueError error concern value. */
public class ImportValueError extends ImportError {

    /** Default constructor. */
    public ImportValueError() {
        super(ImportErrorType.VALUE);
    }


    /** Specific constructor.
     * @param line the line of the error
     * @param value the bad value
     * @param waitedValue the value waited
     */
    public ImportValueError(final int line, final String value,
                            final String waitedValue) {
        super(ImportErrorType.VALUE, line , "",
                "<" + value + "> is a bad value, value waited is <"
                + waitedValue + ">");
    }

    /** Specific constructor.
     * @param line the line of the error
     * @param value the value missing
     */
    public ImportValueError(final int line, final String value) {
        super(ImportErrorType.VALUE, line , "", "<" + value + "> is missing");
    }


}
