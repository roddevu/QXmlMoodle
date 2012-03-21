package fr.qxmlmoodle.question;

import nu.xom.Element;
import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class AnswerList the list of the answer for the question. */
public class AnswerList extends MoodleXMLList<AbstractAnswer>
                        implements IXMLExport {

    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        boolean bExportOk = true;
        final int max = getCount();
        for (int i = 0; i < max; i++) {
            if (!(get(i).doExport(exporter, inParentElement))) {
                bExportOk = false;
                break;
            }
        }
        return bExportOk;
    }

    /** Load and Add an XML answer data.
     * @param importer the XML importer
     * @param xml element XOM
     * @param answer the answer to load and add
     * @return true if success, false otherwise
     */
    public final boolean doAnswerImport(final XMLImporter importer,
                                        final Element xml,
                                        final AbstractAnswer answer) {
        /* Try to Import */
        final boolean bImportOk = answer.doImport(importer, xml);
        /* Add the answer */
        if (bImportOk) {
            add(answer);
        }
        /* Return */
        return bImportOk;
    }

}
