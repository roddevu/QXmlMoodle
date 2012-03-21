package fr.qxmlmoodle.question;



import nu.xom.Element;

import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.matching.SubQuestions;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class QuestionMatching Question type MATCHING. */
public class QuestionMatching extends AbstractQuestion {

    /** Attribute subQuestions (list of SubQuestion). */
    private transient SubQuestions subQuestions;


    /** Default constructor. */
    public QuestionMatching() {
        super(QuestionType.MATCHING);
        subQuestions = new SubQuestions();
    }






    /** @return the subQuestions. */
    public final SubQuestions getSubQuestions() {
        return subQuestions;
    }
    /** @param value the subQuestions to set. */
    public final void setSubQuestions(final SubQuestions value) {
        this.subQuestions = value;
    }






    /** No answer use SubQuestion which contain their answer.
     * @return null
     */
    public final AbstractAnswer createAnswer() {
        /* use SubQuestion */
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
        return subQuestions.doExport(exporter, inParentElement);
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
        return subQuestions.doImport(importer, xml);
    }




    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "QuestionMatching [" + super.questionToString()
                 + ", subQuestions=" + subQuestions + "]";
    }

}
