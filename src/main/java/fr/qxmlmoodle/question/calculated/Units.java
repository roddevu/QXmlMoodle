package fr.qxmlmoodle.question.calculated;

import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class DatasetItems container for unit. */
public class Units extends MoodleXMLList<Unit>
                   implements IXMLImport, IXMLExport {



    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        boolean bExportOk = true;
        /* Create the main marker */
        final Element xmlMain = new Element("units");

        /* Save the base properties */
        final int max = getCount();
        for (int i = 0; i < max; i++) {
            if (!(get(i).doExport(exporter, xmlMain))) {
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
        /* Load unit items */
        final Elements childs = xml.getChildElements();
        for (int i = 0; i < childs.size(); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "unit")) {
                if (!(doImportUnit(importer, child))) {
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


    /** Load XML Unit.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    private boolean doImportUnit(final XMLImporter importer,
                                       final Element xml) {
        boolean bImportOk = false;
        final Unit unit = new Unit();
        if (unit.doImport(importer, xml)) {
            add(unit);
            bImportOk = true;
        }
        return bImportOk;
    }

}
