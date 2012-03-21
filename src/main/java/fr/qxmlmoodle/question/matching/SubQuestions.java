package fr.qxmlmoodle.question.matching;



import nu.xom.Element;
import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class SubQuestions contains all the subQuestion of a QuestionMatching. */
public class SubQuestions extends MoodleXMLList<SubQuestion>
                          implements IXMLImport, IXMLExport {


    /** Create a SubQuestion.
     * @return the SubQuestion created
     */
    public final SubQuestion create() {
        return new SubQuestion();
    }

    /** Add a SubQuestion to the question.
     * @return the SubQuestion created
     */
    public final SubQuestion add() {
        final SubQuestion subQuestion = create();
        if (subQuestion != null) {
            add(subQuestion);
        }
        return subQuestion;
    }



    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement parent element
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        boolean bExportOk = true;
        /* Save the base properties */
        final int max = getCount();
        for (int i = 0; i < max; i++) {
            if (!(get(i).doExport(exporter, inParentElement))) {
                bExportOk = false;
                break;
            }
        }
        /* Return */
        return bExportOk;
    }



    /** Load XML data.
     * @param importer the XML importer
     * @param xml the XOM element
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        boolean bImportOk = true;
        if (importer.isElementName(xml, "subquestion")) {
            final SubQuestion subQues = create();
            if (subQues.doImport(importer, xml)) {
                add(subQues);
            } else {
                bImportOk = false;
            }
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        return bImportOk;
    }



}
