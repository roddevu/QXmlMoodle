package fr.qxmlmoodle.question.answer;

import nu.xom.Attribute;
import nu.xom.Elements;
import nu.xom.Element;
import fr.qxmlmoodle.commons.Feedback;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class Answer. */
public abstract class AbstractAnswer implements IXMLImport, IXMLExport {

    /** Attribute Fraction. */
    private float fraction;
    /** Attribute Text. */
    private String text;
    /** Attribute FeedBack. */
    private Feedback feedback;



    /** Default constructor. */
    public AbstractAnswer() {
        fraction = 0.f;
        feedback = new Feedback();
        text = "";
    }



    /** @return the fraction. */
    public final float getFraction() {
        return fraction;
    }
    /** @return the text. */
    public final String getText() {
        return text;
    }
    /** @return the feedback. */
    public final Feedback getFeedback() {
        return feedback;
    }


    /** @param fractionValue the fraction to set. */
    public final void setFraction(final float fractionValue) {
        this.fraction = fractionValue;
    }
    /** @param textValue the text to set. */
    public final void setText(final String textValue) {
        this.text = textValue;
    }
    /** @param feedbackValue the feedback to set. */
    public final void setFeedback(final Feedback feedbackValue) {
        this.feedback = feedbackValue;
    }


    /** Save specific XML data.
     * Answer that have different attributes from base question
     * need to override this method.
     * @param exporter the XML exporter
     * @param inParentElement parent element
     * @return true if success, false otherwise
     */
    protected abstract boolean doSpecificExport(final XMLExporter exporter,
                                       final Element inParentElement); /* {
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
        /* Create the main marker */
        final Element xmlMain = new Element("answer");
        xmlMain.addAttribute(new Attribute("fraction",
                                           String.valueOf(fraction)));

        /* Save the base properties */
        exporter.addXMLMarker(xmlMain, "text", text);
        if ((feedback != null)
            && (!(feedback.doExport(exporter, xmlMain, "feedback")))) {
            bExportOk = false;
        }

        /* Save the specific properties */
        if ((bExportOk) && (!(doSpecificExport(exporter, xmlMain)))) {
            bExportOk = false;
        }

        /* Return */
        if (bExportOk) {
            inParentElement.appendChild(xmlMain);
        }
        return bExportOk;
    }




    /** Load specific XML data.
     * Answer that have different attributes from base answer need to override
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
        /* Prepare */
        boolean bImportOk = true;
        boolean haveImportText = false;
        /* Load fraction attribute */
        final String str = xml.getAttributeValue("fraction");
        if (str == null) {
            fraction = 0.f;
        } else {
            fraction = Float.parseFloat(str);
        }
        /* Load other attribute */
        final Elements childs = xml.getChildElements();
        for (int i = 0; i < childs.size(); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "text")) {
                text = child.getValue();
                haveImportText = true;
            } else if (importer.isElementName(child, "feedback")) {
                if (!(feedback.doImport(importer, child))) {
                    bImportOk = false;
                    break;
                }
            } else {
                if (!(doSpecificImport(importer, child))) {
                    bImportOk = false;
                    break;
                }
            }
        }
        /* Return : check if text not missing */
        if ((bImportOk) && (!haveImportText)) {
            importer.getWarnings().addMissing(xml, "text");
        }
        return bImportOk;
    }


    /** Method answerToString.
     * @return string */
    public final String answerToString() {
         return "Answer [fraction=" + fraction + ", text=" + text
                 + ", feedback=" + feedback + "]";
    }


}
