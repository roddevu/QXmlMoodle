package fr.qxmlmoodle.exception;

/** Class ImportIOError error concern Input/Output. */public class ImportIOError extends ImportError {
    /** Default constructor. */    public ImportIOError() {
        super(ImportErrorType.IO);
    }    /** Specific constructor.     * @param line the line of the error     * @param tag the tag of the error     */
    public ImportIOError(final int line, final String tag) {
        super(ImportErrorType.IO, line, tag,                "Could not open the specified stream");
    }    /** Specific constructor.     * @param line the line of the error     * @param tag the tag of the error     * @param desc the description of the error     */
    public ImportIOError(final int line, final String tag, final String desc) {
        super(ImportErrorType.IO, line, tag, desc);
    }
}
