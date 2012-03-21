package fr.qxmlmoodle.exception;

/** Class ImportSyntaxError error concern XML syntax. */public class ImportSyntaxError extends ImportError {

    /** Default constructor. */    public ImportSyntaxError() {
        super(ImportErrorType.SYNTAX);
    }    /** Specific constructor.     * @param line the line of the error     * @param tag the tag of the error     */
    public ImportSyntaxError(final int line, final String tag) {
        super(ImportErrorType.SYNTAX, line , tag, "Syntax Error");
    }    /** Specific constructor.     * @param line the line of the error     * @param tag the tag of the error     * @param desc the description of the error     */
    public ImportSyntaxError(final int line, final String tag,                             final String desc) {
        super(ImportErrorType.SYNTAX, line , tag, desc);
    }
}
