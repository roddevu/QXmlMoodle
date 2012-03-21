package fr.qxmlmoodle.exception;

/** Class ImportValidityError error concern XML validity. */public class ImportValidityError extends ImportError {
    /** Default constructor. */    public ImportValidityError() {
        super(ImportErrorType.VALIDITY);
    }    /** Specific constructor.     * @param line the line of the error     * @param tag the tag of the error     */
    public ImportValidityError(final int line, final String tag) {
        super(ImportErrorType.VALIDITY, line, tag,                "XML document is mallformed");
    }    /** Specific constructor.     * @param line the line of the error     * @param tag the tag of the error     * @param desc the description of the error     */
    public ImportValidityError(final int line, final String tag,                               final String desc) {
        super(ImportErrorType.VALIDITY, line , tag, desc);
    }

}
