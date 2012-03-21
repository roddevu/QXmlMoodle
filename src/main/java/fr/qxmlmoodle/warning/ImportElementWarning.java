package fr.qxmlmoodle.warning;


/** Class ImportElementWarning warning concern element. */
public class ImportElementWarning extends ImportWarning {


    /** Default constructor. */
    public ImportElementWarning() {
        super(ImportWarningType.UNKNOWN_ELEMENT);
    }

    /** Specific constructor.
     * @param line line of warning
     * @param elementName the unknown element
     */
    public ImportElementWarning(final int line, final String elementName) {
        super(ImportWarningType.UNKNOWN_ELEMENT, line , "",
                "<" + elementName + "> is an unknown element");
    }


}
