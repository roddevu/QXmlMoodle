package fr.qxmlmoodle.exception;
/** Class ImportAttributeError error concern attribute. */
public class ImportAttributeError extends ImportError {
    /** Default constructor. */    public ImportAttributeError() {
        super(ImportErrorType.ATTRIBUTE);
    }    /** Specific constructor.     * @param line line of error     * @param attrName attribute name     * @param attrValue value of attribute     * @param waitedValue the waited value or list of value for the attribute     */
    public ImportAttributeError(final int line, final String attrName,                                final String attrValue,                                final String waitedValue) {
        super(ImportErrorType.ATTRIBUTE, line , "",                "<" + attrName + ">=\"" + attrValue                + "\" is a bad value, value waited is <" + waitedValue + ">");
    }
}
