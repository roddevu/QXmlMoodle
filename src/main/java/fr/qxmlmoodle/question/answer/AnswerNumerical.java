package fr.qxmlmoodle.question.answer;

import nu.xom.Element;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class AnswerNumerical specific answer for QuestionNumerical. */
public class AnswerNumerical extends AbstractAnswer {

    /** Attribute tolerance. */
    private float tolerance;


    /** Default constructor. */
    public AnswerNumerical() {
        super();
        tolerance = 0.f;
    }



    /** @return the tolerance. */
    public final float getTolerance() {
        return tolerance;
    }
    /** @param value the tolerance to set. */
    public final void setTolerance(final float value) {
        this.tolerance = value;
    }





    /** Save specific XML data.
     * Answer that have different attributes from base question
     * need to override this method.
     * @param exporter the XML exporter
     * @param inParentElement parent element
     * @return true if success, false otherwise
     */
    protected final boolean doSpecificExport(final XMLExporter exporter,
                                       final Element inParentElement) {
        /* Save the base properties */
        exporter.addXMLMarker(inParentElement, "tolerance", tolerance);

        /* Return OK */
        return true;
    }



    /** Load specific XML data.
     * Answer that have different attributes from base answer need to override
     * this method.
     * @param importer the XML importer
     * @param xml the XOM element
     * @return true if success, false otherwise
     */
    protected final boolean doSpecificImport(final XMLImporter importer,
                                       final Element xml) {
        if (importer.isElementName(xml, "tolerance")) {
            tolerance = importer.getElementValue(xml, 0.f);
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        return true;
    }



    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "AnswerNumerical [tolerance=" + tolerance + ", "
              + super.answerToString() + "]";
    }


}
