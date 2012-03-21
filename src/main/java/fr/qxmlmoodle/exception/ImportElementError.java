package fr.qxmlmoodle.exception;

/** Class ImportElementError error concern element. */public class ImportElementError extends ImportError {
    /** Default constructor. */    public ImportElementError() {
        super(ImportErrorType.ELEMENT);
    }    /** Specific constructor.     * @param line line of the error     * @param elementName the name of the wrong element     * @param waitedElement one of the correct name for the element     */
    public ImportElementError(final int line, final String elementName,                              final String waitedElement) {
        super(ImportErrorType.ELEMENT, line , "",                "<" + elementName                + "> is a bad element name, element waited is <"                + waitedElement + ">");
    }    /** Specific constructor.     * @param line line of the error     * @param elementName the name of the missing element     */
    public ImportElementError(final int line, final String elementName) {
        super(ImportErrorType.ELEMENT, line , "",                "<" + elementName + "> is missing");
    }

}
