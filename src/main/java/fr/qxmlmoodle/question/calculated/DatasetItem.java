package fr.qxmlmoodle.question.calculated;

import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class DatasetItem represent an XML moodle dataset_item. */
public class DatasetItem implements IXMLImport, IXMLExport {

    /** Attribute value of the item. */
    private float value;


    /** Default constructor. */
    public DatasetItem() {
        value = 0.f;
    }



    /** @return the value. */
    public final float getValue() {
        return value;
    }

    /** @param itemValue the value to set. */
    public final void setValue(final float itemValue) {
        this.value = itemValue;
    }



    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @param number the number of the dataset_item
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement,
                                  final int number) {
        /* Create the main marker */
        final Element xmlMain  = new Element("dataset_item");
        /* Save the base properties */
        exporter.addXMLMarker(xmlMain, "number", number);
        exporter.addXMLMarker(xmlMain, "value", value);
        /* Return OK */
        inParentElement.appendChild(xmlMain);
        return true;
    }

    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        return doExport(exporter, inParentElement, 0);
    }



    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @param number the number of the dataset_item
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml,
                                  final int number) {
        /* Variable */
        boolean haveNumber = false;
        Element numberElement = null;
        int numberValue = 0;
        boolean haveValue = false;

        /* Load property */
        final Elements childs = xml.getChildElements();
        for (int i = 0; i < childs.size(); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "number")) {
                numberValue = importer.getElementValue(child, 0);
                haveNumber = true;
                numberElement = child;
            } else if (importer.isElementName(child, "value")) {
                value = importer.getElementValue(child, 0.f);
                haveValue = true;
            }
        }

        /* Return : check if item number is OK */
        if ((number > 0) && (haveNumber && (numberValue != number))) {
            importer.getWarnings().addBadValue(numberElement,
                                        String.valueOf(numberValue),
                                        "number waited " + number);

        }
        if (!haveValue) {
            importer.getErrors().addMissingElement(xml, "value");
        }
        return haveValue;
    }


    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        return doImport(importer, xml, 0);
    }




    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "DatasetItem [value=" + value + "]";
    }



}
