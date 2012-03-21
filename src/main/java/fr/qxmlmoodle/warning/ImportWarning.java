package fr.qxmlmoodle.warning;



/** Class ImportWarning error concern warning. */
public class ImportWarning implements IWarning {


    /** Attribute type the type of the warning. */
    private final transient ImportWarningType type;
    /** Attribute line the line of the warning. */
    private int line;
    /** Attribute tag the tag of the warning. */
    private String tag;
    /** Attribute description the description the warning. */
    private String description = "<no warning description found>";



    /** Default constructor. */
    public ImportWarning() {
        this.type = ImportWarningType.OTHER;
    }

    /** Specific constructor.
     * @param typeValue the type of warning
     */
    public ImportWarning(final ImportWarningType typeValue) {
        this.type = typeValue;
    }


    /** Specific constructor.
     * @param typeValue the type of warning
     * @param lineValue the line of the warning
     * @param tagValue the tag of the warning
     */
    public ImportWarning(final ImportWarningType typeValue, final int lineValue,
                         final String tagValue) {
        this.type = typeValue;
        this.line = lineValue;
        this.tag = tagValue;
    }

    /** Specific constructor.
     * @param typeValue the type of warning
     * @param lineValue the line of the warning
     * @param tagValue the tag of the warning
     * @param desc the description of the warning
     */
    public ImportWarning(final ImportWarningType typeValue, final int lineValue,
                         final String tagValue, final String desc) {
        this.type = typeValue;
        this.line = lineValue;
        this.tag = tagValue;
        this.description = desc;
    }



    /** @return the type. */
    public final ImportWarningType getType() {
        return type;
    }
    /** @return the line. */
    public final int getLine() {
        return line;
    }
    /** @return the tag. */
    public final String getTag() {
        return tag;
    }
    /** @return the description. */
    public final String getDescription() {
        return description;
    }


    /** @param lineValue the line to set. */
    public final void setLine(final int lineValue) {
        this.line = lineValue;
    }
    /** @param tagValue the tag to set. */
    public final void setTag(final String tagValue) {
        this.tag = tagValue;
    }
    /** @param descriptionValue the description to set. */
    public final void setDescription(final String descriptionValue) {
        this.description = descriptionValue;
    }



    /** Method toString.
     * @return string
     */
   public final String toString() {
       String text;
       if (line > 0) {
           text = "Warning [line=" + line + ", type=" + type + ", tag=" + tag
           + ", description=" + description + "]";
       } else {
           text = "Warning [type=" + type + ", tag=" + tag
                    + ", description=" + description + "]";
       }
       return text;
    }







}
