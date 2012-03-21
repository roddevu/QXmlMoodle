package fr.qxmlmoodle.question;


import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Attribute;

import fr.qxmlmoodle.Category;
import fr.qxmlmoodle.commons.Feedback;
import fr.qxmlmoodle.image.AbstractImage;
import fr.qxmlmoodle.image.ImageBase64;
import fr.qxmlmoodle.image.ImageURL;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.text.QuestionText;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class Question base for all question type. */
public abstract class AbstractQuestion implements IXMLImport, IXMLExport {

    /** Attribute type of the question. */
    private QuestionType type;
    /** Attribute name. */
    private String name;
    /** Attribute text. */
    private QuestionText text;
    /** Attribute image. */
    private AbstractImage image;
    /** Attribute category. */
    private Category category;

    /** Attribute penalty. */
    private float penalty;
    /** Attribute generalFeedback. */
    private Feedback generalFeedback;
    /** Attribute defaultGrade. */
    private float defaultGrade;
    /** Attribute hidden. */
    private boolean hidden;

    /** Attribute shuffleanswers random order of answer. */
    private boolean shuffleanswers;
    /** Attribute answers list of answers. */
    private final transient AnswerList answers;


    /** Construct and set the question Type.
     * You set the question type only in the constructor and it can be modified
     * after (no default constructor)
     * @param questionType type of the question
     */
    public AbstractQuestion(final QuestionType questionType) {
        type = questionType;
        name = "";
        text = new QuestionText();
        penalty = 0.f;
        generalFeedback = new Feedback();
        defaultGrade = 0.f;
        hidden = false;

        shuffleanswers = false;
        answers = new AnswerList();
    }


    /** Create the specific answer depend of question type.
     * Question can have different kind of answer, so each question
     * needs to code this method
     * @return the answer depend of question
     */
    public abstract AbstractAnswer createAnswer();



    /** @return the answers. */
    public final AnswerList getAnswers() {
        return answers;
    }

    /** @return the type. */
    public final QuestionType getType() {
        return type;
    }
    /** @return the name. */
    public final String getName() {
        return name;
    }
    /** @return the text. */
    public final QuestionText getText() {
        return text;
    }
    /** @return the image. */
    public final AbstractImage getImage() {
        return image;
    }
    /** @return the category. */
    public final Category getCategory() {
        return category;
    }
    /** @return the penalty. */
    public final float getPenalty() {
        return penalty;
    }
    /** @return the generalFeedback. */
    public final Feedback getGeneralFeedback() {
        return generalFeedback;
    }
    /** @return the defaultGrade. */
    public final float getDefaultGrade() {
        return defaultGrade;
    }
    /** @return the hidden. */
    public final boolean isHidden() {
        return hidden;
    }
    /** @return the shuffleanswers. */
    public final boolean isShuffleanswers() {
        return shuffleanswers;
    }


    /** @param typeValue the type to set. */
    public final void setType(final QuestionType typeValue) {
        this.type = typeValue;
    }
    /** @param nameValue the name to set. */
    public final void setName(final String nameValue) {
        this.name = nameValue;
    }
    /** @param textValue the text to set. */
    public final void setText(final QuestionText textValue) {
        this.text = textValue;
    }
    /** @param imageValue the image to set. */
    public final void setImage(final AbstractImage imageValue) {
        this.image = imageValue;
    }
    /** @param categoryValue the category to set. */
    public final void setCategory(final Category categoryValue) {
        this.category = categoryValue;
    }
    /** @param penaltyValue the penalty to set. */
    public final void setPenalty(final float penaltyValue) {
        this.penalty = penaltyValue;
    }
    /** @param value the generalFeedback to set. */
    public final void setGeneralFeedback(final Feedback value) {
        this.generalFeedback = value;
    }
    /** @param defaultGradeValue the defaultGrade to set. */
    public final void setDefaultGrade(final float defaultGradeValue) {
        this.defaultGrade = defaultGradeValue;
    }
    /** @param hiddenValue the hidden to set. */
    public final void setHidden(final boolean hiddenValue) {
        this.hidden = hiddenValue;
    }
    /** @param value the shuffleanswers to set. */
    public final void setShuffleanswers(final boolean value) {
        this.shuffleanswers = value;
    }


    /** Add an answer.
     * @return the answer added
     */
    public final AbstractAnswer addAnswer() {
        final AbstractAnswer answer = createAnswer();
        if (answer != null) {
            answers.add(answer);
        }
        return answer;
    }



    /** Save specific XML data.
     * Question that have different attributes from base question
     * need to override this method.
     * @param exporter the XML exporter
     * @param inParentElement parent element
     * @return true if success, false otherwise
     */
    protected abstract boolean doSpecificExport(final XMLExporter exporter,
                                                final Element inParentElement);
    /* {
        return true;
    }*/


    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        boolean bExportOk = true;
        /* Create the Question */
        final Element question = new Element("question");
        question.addAttribute(new Attribute("type",
                                            type.toString().toLowerCase()));

        /* Save the base properties */
        exporter.addXMLTextMarker(question, "name", name);

        bExportOk = text.doExport(exporter, question);
        if ((image != null) && (bExportOk)) {
            bExportOk = image.doExport(exporter, question);
        }

        if (bExportOk) {
            exporter.addXMLMarker(question, "penalty", penalty);
            if (generalFeedback != null) {
                bExportOk = generalFeedback.doExport(exporter, question,
                                                        "generalfeedback");
            }
        }
        if (bExportOk) {
            exporter.addXMLMarker(question, "defaultGrade", defaultGrade);
            exporter.addXMLMarker(question, "hidden", hidden, "1", "0");
            exporter.addXMLMarker(question, "shuffleanswers", shuffleanswers,
                                                              "1", "0");
        }
        /* Save the specific properties */
        if (bExportOk) {
            bExportOk = doSpecificExport(exporter, question);
        }


        /* Save the answer */
        if (bExportOk) {
            bExportOk = answers.doExport(exporter, question);
        }

        /* Return  */
        if (bExportOk) {
            inParentElement.appendChild(question);
        }
        return bExportOk;
    }





    /** Load specific XML data.
     * Question that have different attributes from base answer need to override
     * this method.
     * @param importer the XML importer
     * @param xml the XOM element
     * @return true if success, false otherwise
     */
    protected abstract boolean doSpecificImport(final XMLImporter importer,
                                             final Element xml); /* {
        importer.addUnknonwElementWarning(xml);
        return true;
    }*/

    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        /* Variable */
        boolean bImportOk = true;
        boolean haveName = false, haveText = false;
        /* Load the commons property of a question */
        final Elements childs = xml.getChildElements();
        for (int i = 0; (bImportOk) && (i < childs.size()); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "name")) {
                this.name = importer.getMoodleText(child);
                haveName = (this.name != null);
                bImportOk = haveName;
            } else if (importer.isElementName(child, "questiontext")) {
                haveText = text.doImport(importer, child);
                bImportOk = haveText;
            } else if (importer.isElementName(child, "answer")) {
                /* Create and load an answer */
                final AbstractAnswer answer = createAnswer();
                bImportOk = answers.doAnswerImport(importer, child, answer);
            } else if (doPropertiesImport(importer, child)) {
                /* Load other property separate in other function due to PMD */
                bImportOk = true;
            } else if (!(doSpecificImport(importer, child))) {
                bImportOk = false;
            }
        }
        /* Return true but check if the question have name or text */
        if (!haveName) {
            importer.getErrors().addMissingElement(xml, "question/name");
        }
        if (!haveText) {
            importer.getErrors().addMissingElement(xml,
                                                   "question/questiontext");
        }
        return (bImportOk) && (haveName && haveText);
    }



    /** Load XML properties.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if property found, false otherwise
     */
    private boolean doPropertiesImport(final XMLImporter importer,
                                         final Element xml) {
        boolean bPropertyFound = true;
        if (importer.isElementName(xml, "image")) {
            setImage(new ImageURL(xml.getValue()));
        } else if (importer.isElementName(xml, "image_base64")) {
            setImage(new ImageBase64(xml.getValue()));
        } else if (importer.isElementName(xml, "generalfeedback")) {
            if (generalFeedback == null) {
                generalFeedback = new Feedback();
            }
            generalFeedback.doImport(importer, xml);
        } else if (importer.isElementName(xml, "penalty")) {
            penalty = importer.getElementValue(xml, 0.f);
        } else if (importer.isElementName(xml, "defaultgrade")) {
            defaultGrade = importer.getElementValue(xml, 0.f);
        } else if (importer.isElementName(xml, "hidden")) {
            hidden = importer.getElementValue(xml, "1", false);
        } else if (importer.isElementName(xml, "shuffleanswers")) {
            shuffleanswers = importer.getElementValue(xml, "1", false);
        } else {
            bPropertyFound = false;
        }
        return bPropertyFound;
    }



    /** Method toString.
     * @return string
     */
   public final String questionToString() {
       return "Question [type=" + type + ", name=" + name + ", text=" + text
                + ", image=" + image + ", category=" + category + ", penalty="
                + penalty + ", generalFeedback=" + generalFeedback
                + ", defaultGrade=" + defaultGrade + ", hidden=" + hidden
                + ", shuffleanswers=" + shuffleanswers + ", answers=" + answers
                + "]";
    }




}
