package fr.qxmlmoodle.question.calculated;

import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class DatasetItems container for dataset_item. */
public class DatasetItems extends MoodleXMLList<DatasetItem>
                          implements IXMLImport, IXMLExport {


    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        /* Create the main marker */
        boolean bExportOk = true;
        final Element xmlMain = new Element("dataset_items");

        /* Save the base properties */
        final int max = getCount();
        for (int i = 0; i < max; i++) {
            if (!(get(i).doExport(exporter, xmlMain, i + 1))) {
                bExportOk = false;
                break;
            }
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
        /* Load dataset_item */
        final Elements childs = xml.getChildElements();
        for (int i = 0; i < childs.size(); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "dataset_item")) {
                if (!(doImportDatasetItem(importer, child))) {
                    bImportOk = false;
                    break;
                }
            } else {
                importer.getWarnings().addUnknonwElement(child);
            }
        }
        /* Return */
        return bImportOk;
    }


    /** Load XML dataset_item.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    private boolean doImportDatasetItem(final XMLImporter importer,
                                        final Element xml) {
        boolean bImportOk = false;
        final DatasetItem dsIt = new DatasetItem();
        if (dsIt.doImport(importer, xml)) {
            add(dsIt);
            bImportOk = true;
        }
        return bImportOk;
    }


}
