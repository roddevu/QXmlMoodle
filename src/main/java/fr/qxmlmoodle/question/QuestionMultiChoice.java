package fr.qxmlmoodle.question;

import nu.xom.Element;
import fr.qxmlmoodle.commons.Feedback;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.answer.AnswerGeneral;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class QuestionMultiChoice Question type MULTICHOICE. */
public class QuestionMultiChoice extends AbstractQuestion {

    /** Enumeration of different type of AnswerNumbering. */
    enum AnswerNumbering {
        /** none. */
        NONE,
        /** abc. */
        LETTER,
        /** ABC. */
        LETTER_CAPITAL,
        /** 123. */
        NUMBER
    }

    /** Attribute single. */
    private boolean single;
    /** Attribute correctFeedback. */
    private Feedback correctFeedback;
    /** Attribute partiallyCorrectFeedback. */
    private Feedback partialCorFeed;
    /** Attribute incorrectFeedback. */
    private Feedback incorrectFeedback;
    /** Attribute answerNumbering. */
    private AnswerNumbering answerNumbering;



    /** Default constructor. */
    public QuestionMultiChoice() {
        super(QuestionType.MULTICHOICE);
        single = true;
        correctFeedback = new Feedback();
        partialCorFeed = new Feedback();
        incorrectFeedback = new Feedback();
        answerNumbering = AnswerNumbering.NONE;
    }



    /** Create the specific answer.
     * @return the specific answer
     */
    public final AbstractAnswer createAnswer() {
        return new AnswerGeneral();
    }



    /** @return the single. */
    public final boolean isSingle() {
        return single;
    }
    /** @return the correctFeedback. */
    public final Feedback getCorrectFeedback() {
        return correctFeedback;
    }
    /** @return the partiallyCorrectFeedback. */
    public final Feedback getPartiallyCorrectFeedback() {
        return partialCorFeed;
    }
    /** @return the partiallyCorrectFeedback. (only for PMD) */
    public final Feedback getPartialCorFeed() {
        return partialCorFeed;
    }
    /** @return the incorrectFeedback. */
    public final Feedback getIncorrectFeedback() {
        return incorrectFeedback;
    }
    /** @return the answerNumbering. */
    public final AnswerNumbering getAnswerNumbering() {
        return answerNumbering;
    }


    /** @param singleValue the single to set. */
    public final void setSingle(final boolean singleValue) {
        this.single = singleValue;
    }
    /** @param value the correctFeedback to set. */
    public final void setCorrectFeedback(final Feedback value) {
        this.correctFeedback = value;
    }
    /** @param value the partiallyCorrectFeedback to set. */
    public final void setPartiallyCorrectFeedback(final Feedback value) {
        this.partialCorFeed = value;
    }
    /** @param value the partiallyCorrectFeedback to set. (only for PMD) */
    public final void setPartialCorFeed(final Feedback value) {
        this.partialCorFeed = value;
    }
    /** @param value the incorrectFeedback to set. */
    public final void setIncorrectFeedback(final Feedback value) {
        this.incorrectFeedback = value;
    }
    /** @param value the answerNumbering to set. */
    public final void setAnswerNumbering(final AnswerNumbering value) {
        this.answerNumbering = value;
    }

    /** Save feedback XML data.
     * @param exporter the XML exporter
     * @param inParentElement parent element
     * @return true if success, false otherwise
     */
    private boolean doFeedbackExport(final XMLExporter exporter,
                                     final Element inParentElement) {
        boolean bExportOk = true;
        /* CorrectFeedBack */
        if ((correctFeedback != null)
            && (!(correctFeedback.doExport(exporter, inParentElement,
                                           "correctfeedback")))) {
            bExportOk = false;
        }
        /* PartiallyCorrectFeedBack */
        if ((bExportOk) && (partialCorFeed != null)
             && (!(partialCorFeed.doExport(exporter,
                                                  inParentElement,
                                                  "partiallycorrectfeedback"
                                                   )))) {
            bExportOk = false;
        }
        /* InCorrectFeedBack */
        if ((bExportOk) && (incorrectFeedback != null)
             && (!(incorrectFeedback.doExport(exporter, inParentElement,
                    "incorrectfeedback")))) {
            bExportOk = false;
        }
        /* Return */
        return bExportOk;
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
        boolean bExportOk = true;
        /* Save the base properties */
        exporter.addXMLMarker(inParentElement, "single", single, "true",
                                                                 "false");

        bExportOk = doFeedbackExport(exporter, inParentElement);

        if (bExportOk) {
            exporter.addXMLMarker(inParentElement, "answernumbering",
                                                getExportAnswerNumbering());
        }

        /* Return OK */
        return bExportOk;
    }

    /** @return the export string corresponding to answer numbering. */
    private String getExportAnswerNumbering() {
        String str;
        switch (answerNumbering) {
        case LETTER: str = "abc"; break;
        case LETTER_CAPITAL: str = "ABC"; break;
        case NUMBER: str = "123"; break;
        default: str = "none"; break;
        }
        return str;
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
        boolean bImportOk = true;
        if (importer.isElementName(xml, "single")) {
            single = importer.getElementValue(xml, "true", true);
        } else if (importer.isElementName(xml, "correctfeedback")) {
            if (correctFeedback == null) {
                correctFeedback = new Feedback();
            }
            bImportOk = correctFeedback.doImport(importer, xml);
        } else if (importer.isElementName(xml, "partiallycorrectfeedback")) {
            if (partialCorFeed == null) {
                partialCorFeed = new Feedback();
            }
            bImportOk = partialCorFeed.doImport(importer, xml);
        } else if (importer.isElementName(xml, "incorrectfeedback")) {
            if (incorrectFeedback == null) {
                incorrectFeedback = new Feedback();
            }
            bImportOk = incorrectFeedback.doImport(importer, xml);
        } else if (importer.isElementName(xml, "answernumbering")) {
            bImportOk = doImportAnswerNumbering(importer, xml);
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        return bImportOk;
    }


    /** Load XML feedback.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    private boolean doImportAnswerNumbering(final XMLImporter importer,
                                      final Element xml) {
        boolean bImportOk = true;
        final String str = xml.getValue();
        if (str == null) {
            importer.getErrors().addMissingValue(xml, "none");
        } else if ("none".equalsIgnoreCase(str)) {
            answerNumbering = AnswerNumbering.NONE;
        } else if ("ABC".equals(str)) {
            answerNumbering = AnswerNumbering.LETTER_CAPITAL;
        } else if ("abc".equalsIgnoreCase(str)) {
            answerNumbering = AnswerNumbering.LETTER;
        } else if ("123".equalsIgnoreCase(str)) {
            answerNumbering = AnswerNumbering.NUMBER;
        } else {
            importer.getErrors().addBadValue(xml, "none|abc|ABC|123");
            bImportOk = false;
        }
        return bImportOk;
    }


    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "QuestionMultiChoice [" + super.questionToString() + ", single="
                + single + ", correctFeedback=" + correctFeedback
                + ", partiallyCorrectFeedback=" + partialCorFeed
                + ", incorrectFeedback=" + incorrectFeedback
                + ", answerNumbering=" + answerNumbering + "]";
    }



}
