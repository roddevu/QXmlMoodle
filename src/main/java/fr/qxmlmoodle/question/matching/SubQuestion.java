package fr.qxmlmoodle.question.matching;

import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.answer.AnswerGeneral;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class SubQuestion. */
public class SubQuestion implements IXMLImport, IXMLExport {

    /** Attributes text. */
    private String text;
    /** Attributes answer. */
    private AbstractAnswer answer;


    /** Default constructor. */
    public SubQuestion() {
        text = "";
        answer = new AnswerGeneral();
    }




    /** @return the text. */
    public final String getText() {
        return text;
    }
    /** @return the answer. */
    public final AbstractAnswer getAnswer() {
        return answer;
    }


    /** @param textValue the text to set. */
    public final void setText(final String textValue) {
        this.text = textValue;
    }
    /** @param answerValue the answer to set. */
    public final void setAnswer(final AbstractAnswer answerValue) {
        this.answer = answerValue;
    }



    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
     public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        boolean bExportOk = true;
        /* Create the main marker */
        final Element xmlMain = new Element("subquestion");

        /* Save the base properties */
        exporter.addXMLMarker(xmlMain, "text", this.text);
        if ((answer != null) && (!(answer.doExport(exporter, xmlMain)))) {
            bExportOk = false;
        }

        /* Return */
        if (bExportOk) {
            inParentElement.appendChild(xmlMain);
        }
        return bExportOk;
     }






     /** Load XML data.
      * @param importer the XML importer
      * @param xml element XOM
      * @return true if success, false otherwise
      */
     public final boolean doImport(final XMLImporter importer,
                                   final Element xml) {
        boolean bImportOk = true;
        /* Variable */
        boolean haveText = false, haveAnswer = false;
        /* Load properties */
        final Elements childs = xml.getChildElements();
        for (int i = 0; i < childs.size(); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "text")) {
                this.text = importer.getElementValue(child, "");
                haveText = true;
            } else if (importer.isElementName(child, "answer")) {
                if (!(this.answer.doImport(importer, child))) {
                    bImportOk = false;
                    break;
                }
                haveAnswer = true;
            } else {
                importer.getWarnings().addUnknonwElement(child);
            }
        }
        /* Return : check Name and Text */
        if (!haveText) {
            importer.getErrors().addMissingElement(xml, "SubQuestion/text");
        }
        if (!haveAnswer) {
            importer.getErrors().addMissingElement(xml, "SubQuestion/answer");
        }
        return (bImportOk && haveText && haveAnswer);
     }




     /** Method toString.
      * @return string
      */
    public final String toString() {
        return "SubQuestion [text=" + text + ", answer=" + answer + "]";
    }




}
