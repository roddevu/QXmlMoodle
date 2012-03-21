package fr.qxmlmoodle.question.text;

import nu.xom.Attribute;
import nu.xom.Element;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class QuestionText represent an XML moodle QuestionText. */
public class QuestionText implements IXMLImport, IXMLExport {

    /** Attribute format of the QuestionText. */
    private QuestionTextFormat format;
    /** Attribute content of the QuestionText. */
    private String content;


    /** Default constructor. */
    public QuestionText() {
        this.format = QuestionTextFormat.HTML;
        this.content = "";
    }



    /** @return the format. */
    public final QuestionTextFormat getFormat() {
        return format;
    }
    /** @return the content. */
    public final String getContent() {
        return content;
    }


    /** @param formatValue the format to set. */
    public final void setFormat(final QuestionTextFormat formatValue) {
        this.format = formatValue;
    }
    /** @param contentValue the content to set. */
    public final void setContent(final String contentValue) {
        this.content = contentValue;
    }



    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        /* Create the main marker */
        final Element questionText = new Element("questiontext");
        questionText.addAttribute(new Attribute("format",
                                  format.toString().toLowerCase()));
        /* Save the base properties */
        exporter.createXMLMoodleText(questionText, content);
        /* Return OK */
        inParentElement.appendChild(questionText);
        return true;
    }




    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        boolean bErrorFormat = false;
        /* Load format */
        final String str = xml.getAttributeValue("format");
        if (str == null) {
            format = QuestionTextFormat.HTML;
        } else if ("moodle_auto_format".equals(str)) {
            format = QuestionTextFormat.MOODLE_AUTO_FORMAT;
        } else if ("plain_text".equals(str)) {
            format = QuestionTextFormat.PLAIN_TEXT;
        } else if ("markdown".equals(str)) {
            format = QuestionTextFormat.MARKDOWN;
        } else if ("html".equals(str)) {
            format = QuestionTextFormat.HTML;
        } else {
            importer.getErrors().addAttrError(xml, "format", str,
                                 "html|moodle_auto_format|plain_text|markdown");
            bErrorFormat = true;
        }
        /* Load content */
        if (!bErrorFormat) {
            content = importer.getMoodleText(xml);
        }
        /* Return */
        return ((!bErrorFormat) && (content != null));
    }



    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "QuestionText [format=" + format + ", content=" + content + "]";
    }


}
