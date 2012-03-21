package fr.qxmlmoodle.exception;

/** Class ImportError error concern import. */public class ImportError implements IError {
    /** Attribute type the type of the error. */
    private final transient ImportErrorType type;
    /** Attribute line the line of the error. */    private int line;
    /** Attribute tag the tag of the error. */    private String tag;
    /** Attribute description the description the error. */    private String description = "<no error description found>";
    /** Default constructor. */    public ImportError() {
        this.type = ImportErrorType.OTHER;
    }    /** Specific constructor.     * @param typeValue type of the error     */
    public ImportError(final ImportErrorType typeValue) {
        this.type = typeValue;
    }    /** Specific constructor.     * @param typeValue type of the error     * @param lineValue the line of the error     * @param tagValue the tag of the error     */
    public ImportError(final ImportErrorType typeValue, final int lineValue,                       final String tagValue) {
        this.type = typeValue;
        this.line = lineValue;
        this.tag = tagValue;
    }    /** Specific constructor.     * @param typeValue type of the error     * @param lineValue the line of the error     * @param tagValue the tag of the error     * @param desc the description of the error     */
    public ImportError(final ImportErrorType typeValue, final int lineValue,                       final String tagValue, final String desc) {
        this.type = typeValue;
        this.line = lineValue;
        this.tag = tagValue;
        this.description = desc;
    }

    /** @return the type. */    public final ImportErrorType getType() {        return type;    }    /** @return the line. */    public final int getLine() {        return line;    }    /** @return the tag. */    public final String getTag() {        return tag;    }    /** @return the description. */    public final String getDescription() {        return description;    }    /** @param lineValue the line to set. */    public final void setLine(final int lineValue) {        this.line = lineValue;    }    /**     * @param tagValue the tag to set     */    public final void setTag(final String tagValue) {        this.tag = tagValue;    }    /**     * @param descriptionValue the description to set     */    public final void setDescription(final String descriptionValue) {        this.description = descriptionValue;    }    /** Method toString.     * @return string     */   public final String toString() {       String text;       if (line > 0) {           text = "Error [line=" + line + ", type=" + type + ", tag=" + tag           + ", description=" + description + "]";       } else {           text = "Error [type=" + type + ", tag=" + tag                    + ", description=" + description + "]";       }       return text;
    }

}
