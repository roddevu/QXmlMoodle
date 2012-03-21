package fr.qxmlmoodle.question;

import nu.xom.Element;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.answer.AnswerCalculated;
import fr.qxmlmoodle.question.calculated.DatasetDefinitions;
import fr.qxmlmoodle.question.calculated.Units;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class QuestionCalculated Question type CALCULATED. */
public class QuestionCalculated extends AbstractQuestion {


    /** Attribute units list of unit. */
    private Units units;
    /** Attribute datasetDefinitions list of dataset_definition. */
    private DatasetDefinitions datasetDefs;




    /** Default constructor. */
    public QuestionCalculated() {
        super(QuestionType.CALCULATED);
        units = new Units();
        datasetDefs = new DatasetDefinitions();
    }





    /** @return the units. */
    public final Units getUnits() {
        return units;
    }
    /** @return the datasetDefinitions. */
    public final DatasetDefinitions getDatasetDefinitions() {
        return datasetDefs;
    }
    /** @return the datasetDefinitions. (only for PMD) */
    public final DatasetDefinitions getDatasetDefs() {
        return datasetDefs;
    }


    /** @param unitsValue the units to set. */
    public final void setUnits(final Units unitsValue) {
        this.units = unitsValue;
    }
    /** @param value the datasetDefinitions to set */
    public final void setDatasetDefinitions(final DatasetDefinitions value) {
        this.datasetDefs = value;
    }
    /** @param value the datasetDefinitions to set. (only for PMD) */
    public final void setDatasetDefs(final DatasetDefinitions value) {
        this.datasetDefs = value;
    }




    /** Create the specific answer.
     * @return the specific answer
     */
    public final AbstractAnswer createAnswer() {
        return new AnswerCalculated();
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
        return (units.doExport(exporter, inParentElement)
                && datasetDefs.doExport(exporter, inParentElement));
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
        boolean bImportOk = true;
        if (importer.isElementName(xml, "units")) {
            bImportOk = units.doImport(importer, xml);
        } else if (importer.isElementName(xml, "dataset_definitions")) {
            bImportOk = datasetDefs.doImport(importer, xml);
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        return bImportOk;
    }





    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "QuestionCalculated [" + super.questionToString() + ", units="
              + units + ", datasetDefinitions="    + datasetDefs + "]";
    }


}
