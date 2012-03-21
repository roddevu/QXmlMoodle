package fr.qxmlmoodle.commons;

import nu.xom.Element;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class Feedback contains moodle feedback format. */
public class Feedback implements IXMLImport, IXMLExport {

    /** Attribute text of the feedback. */
    private String text;


    /** Default constructor. */
    public Feedback() {
        text = "";
    }

    /** Construct the Feedback and set text.
     * @param textValue the text of the feedback
     */
    public Feedback(final String textValue) {
        this.text = textValue;
    }


    /** @return the text. */
    public final String getText() {
        return text;
    }
    /** @param textValue the text to set */
    public final void setText(final String textValue) {
        this.text = textValue;
    }




    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @param feedbackName the marker name for feedback
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement,
                                  final String feedbackName) {
        /* Save the base properties */
        exporter.addXMLTextMarker(inParentElement, feedbackName, text);
        /* Return OK */
        return true;
    }


    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        return doExport(exporter, inParentElement, "feedback");
    }




    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        text = importer.getOptionalMoodleText(xml, "");
        return true;
    }



    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "Feedback [text=" + text + "]";
    }



}
