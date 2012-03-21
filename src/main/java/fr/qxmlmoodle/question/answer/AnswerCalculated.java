package fr.qxmlmoodle.question.answer;

import nu.xom.Element;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class AnswerCalculated specific answer for QuestionCalculated. */
public class AnswerCalculated extends AbstractAnswer {

    /** Enumeration of different type of Tolerance (cf. moodle doc). */
    enum ToleranceType {
        /** Correct = if dx <= t. */
        NOMINAL,
        /** Correct = if dx/x <= t. */
        RELATIVE,
        /** Correct = if dx²/x² <= t. */
        GEOMETRIC
    }

    /** Attribute tolerance. */
    private float tolerance;
    /** Attribute toleranceType. */
    private ToleranceType toleranceType;
    /** Attribute correctAnswerFormat. */
    private String corAnswerFormat;
    /** Attribute correctAnswerLength. */
    private int corAnswerLength;


    /** Default constructor. */
    public AnswerCalculated() {
        super();
        tolerance = 0.f;
        toleranceType = ToleranceType.NOMINAL;
        corAnswerFormat = "";
        corAnswerLength = 0;
    }



    /** @return the tolerance. */
    public final float getTolerance() {
        return tolerance;
    }
    /** @return the toleranceType. */
    public final ToleranceType getToleranceType() {
        return toleranceType;
    }
    /** @return the correctAnswerFormat. */
    public final String getCorrectAnswerFormat() {
        return corAnswerFormat;
    }
    /** @return the correctAnswerLength. */
    public final int getCorrectAnswerLength() {
        return corAnswerLength;
    }
    /** @return the correctAnswerFormat. */
    public final String getCorAnswerFormat() {
        return corAnswerFormat;
    }
    /** @return the correctAnswerLength. */
    public final int getCorAnswerLength() {
        return corAnswerLength;
    }


    /** @param value the tolerance to set. */
    public final void setTolerance(final float value) {
        this.tolerance = value;
    }
    /** @param value the toleranceType to set. */
    public final void setToleranceType(final ToleranceType value) {
        this.toleranceType = value;
    }
    /** @param value the correctAnswerFormat to set. */
    public final void setCorrectAnswerFormat(final String value) {
        this.corAnswerFormat = value;
    }
    /** @param value the correctAnswerLength to set. */
    public final void setCorrectAnswerLength(final int value) {
        this.corAnswerLength = value;
    }
    /** @param value the correctAnswerFormat to set. */
    public final void setCorAnswerFormat(final String value) {
        this.corAnswerFormat = value;
    }
    /** @param value the correctAnswerLength to set. */
    public final void setCorAnswerLength(final int value) {
        this.corAnswerLength = value;
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

        String str;
        if (toleranceType == ToleranceType.NOMINAL) {
            str = "1";
        } else {
            str = toleranceType.toString().toLowerCase();
        }
        exporter.addXMLMarker(inParentElement, "tolerancetype", str);

        exporter.addXMLMarker(inParentElement, "correctanswerformat",
                                               corAnswerFormat);
        exporter.addXMLMarker(inParentElement, "correctanswerlength",
                                               corAnswerLength);
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
        boolean bImportOk = true;
        if (importer.isElementName(xml, "tolerance")) {
            tolerance = importer.getElementValue(xml, 0.f);
        } else if (importer.isElementName(xml, "tolerancetype")) {
            bImportOk = doImportToleranceType(importer, xml);
        } else if (importer.isElementName(xml, "correctanswerformat")) {
            corAnswerFormat = importer.getElementValue(xml, "");
        } else if (importer.isElementName(xml, "correctanswerlength")) {
            corAnswerLength = importer.getElementValue(xml, 0);
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        return bImportOk;
    }


    /** Load XML toleranceType.
     * @param importer the XML importer
     * @param xml the XOM element
     * @return true if success, false otherwise
     */
    private boolean doImportToleranceType(final XMLImporter importer,
                                          final Element xml) {
        boolean bImportOk = true;
        final String str = xml.getValue();
        if (str == null) {
            importer.getErrors().addMissingValue(xml, "nominal");
        } else if (("nominal".equalsIgnoreCase(str))
                    || ("1".equalsIgnoreCase(str))) {
            toleranceType = ToleranceType.NOMINAL;
        } else if (("relative".equalsIgnoreCase(str))
                    || ("2".equalsIgnoreCase(str))) {
            toleranceType = ToleranceType.RELATIVE;
        } else if (("geometric".equalsIgnoreCase(str))
                    || ("3".equalsIgnoreCase(str))) {
            toleranceType = ToleranceType.GEOMETRIC;
        } else {
            importer.getErrors().addBadValue(xml,
                                "nominal|1|relative|2|geometric|3");
            bImportOk = false;
        }
        return bImportOk;
    }


    /** Method toString classic.
     * @return string */
    public final String toString() {
        return "AnswerCalculated [tolerance=" + tolerance + ", toleranceType="
                + toleranceType + ", correctAnswerFormat="
                + corAnswerFormat + ", correctAnswerLength="
                + corAnswerLength + ", " + super.answerToString()
                + "]";
    }


}
