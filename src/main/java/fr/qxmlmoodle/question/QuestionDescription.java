package fr.qxmlmoodle.question;

import nu.xom.Element;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class QuestionDescription Question type DESCRIPTION. */
public class QuestionDescription extends AbstractQuestion {


    /** Default constructor. */
    public QuestionDescription() {
        super(QuestionType.DESCRIPTION);
    }


    /** No answer for description question.
     * @return null
     */
    public final AbstractAnswer createAnswer() {
        return null;
    }




    /** Save specific XML data.
     * Question that have different attributes from base question
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
     * Question that have different attributes from base answer need to override
     * this method.
     * @param importer the XML importer
     * @param xml the XOM element
     * @return true if success, false otherwise
     */
    protected final boolean doSpecificImport(final XMLImporter importer,
                                       final Element xml) {
        return true;
    }


}
