package fr.qxmlmoodle.question.calculated;

import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class DatasetItem represent an XML moodle unit. */
public class Unit implements IXMLImport, IXMLExport {

    /** Attribute multiplier. */
    private float multiplier;
    /** Attribute unit_name. */
    private String unitName;


    /** Default constructor. */
    public Unit() {
        multiplier = 1.f;
        unitName = "";
    }




    /** @return the multiplier. */
    public final float getMultiplier() {
        return multiplier;
    }
    /** @return the unit_name. */
    public final String getUnitName() {
        return unitName;
    }


    /** @param value the multiplier to set. */
    public final void setMultiplier(final float value) {
        this.multiplier = value;
    }
    /** @param value the unit_name to set. */
    public final void setUnitName(final String value) {
        unitName = value;
    }



    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        /* Create the main marker */
        final Element xmlMain = new Element("unit");
        /* Save the base properties */
        exporter.addXMLMarker(xmlMain, "multiplier", multiplier);
        exporter.addXMLMarker(xmlMain, "unit_name", unitName);
        /* Return OK */
        inParentElement.appendChild(xmlMain);
        return true;
    }




    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        /* Variable */
        boolean haveMultiplier = false, haveUnitName = false;
        /* Load properties */
        final Elements childs = xml.getChildElements();
        for (int i = 0; i < childs.size(); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "multiplier")) {
                multiplier = importer.getElementValue(child, 0.f);
                haveMultiplier = true;
            } else if (importer.isElementName(child, "unit_name")) {
                unitName = importer.getElementValue(child, "");
                haveUnitName = true;
            } else {
                importer.getWarnings().addUnknonwElement(child);
            }
        }
        /* Return : check if properties loaded  */
        if (!haveMultiplier) {
            importer.getErrors().addMissingElement(xml, "unit/multiplier");
        }
        if (!haveUnitName) {
            importer.getErrors().addMissingElement(xml, "unit/unit_name");
        }
        return (haveMultiplier && haveUnitName);
    }




    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "Unit [multiplier=" + multiplier + ", unit_name=" + unitName
                + "]";
    }


}
