package fr.qxmlmoodle.question;

import nu.xom.Element;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.answer.AnswerGeneral;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class QuestionShortAnswer Question type SHORTANSWER. */
public class QuestionShortAnswer extends AbstractQuestion {

    /** Attribute useCase. */
    private boolean useCase;


    /** Default constructor.
     */
    public QuestionShortAnswer() {
        super(QuestionType.SHORTANSWER);
        useCase = false;
    }


    /** Create the specific answer.
     * @return the specific answer
     */
    public final AbstractAnswer createAnswer() {
        return new AnswerGeneral();
    }


    /**
     * @return the useCase
     */
    public final boolean isUseCase() {
        return useCase;
    }
    /**
     * @param useCaseValue the useCase to set
     */
    public final void setUseCase(final boolean useCaseValue) {
        this.useCase = useCaseValue;
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
        /* Save the base properties */
        exporter.addXMLMarker(inParentElement, "usecase", useCase, "1", "0");
        /* Return OK */
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
        if (importer.isElementName(xml, "usecase")) {
            useCase = importer.getElementValue(xml, "1", false);
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        return true;
    }



    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "QuestionShortAnswer [" + super.questionToString() + ", useCase="
                                      + useCase + "]";
    }



}
