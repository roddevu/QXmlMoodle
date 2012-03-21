package fr.qxmlmoodle.question.answer;

import nu.xom.Element;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class AnswerGeneral. */
public class AnswerGeneral extends AbstractAnswer {



    /** Save specific XML data.
     * Answer that have different attributes from base question
     * need to override this method.
     * @param exporter the XML exporter
     * @param inParentElement parent element
     * @return true if success, false otherwise
     */
    protected final boolean doSpecificExport(final XMLExporter exporter,
                                       final Element inParentElement) {
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
        importer.getWarnings().addUnknonwElement(xml);
        return true;
    }


    /** Method toString classic.
     * @return string */
    @Override
    public final String toString() {
        return super.answerToString();
    }

}
