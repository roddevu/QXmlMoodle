package fr.qxmlmoodle.question.calculated;

import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;


/** Class DatasetDefinition represent an XML moodle dataset_definition. */
public class DatasetDefinition implements IXMLImport, IXMLExport {

    /** Enumeration of different type of Status (cf. moodle doc). */
    public enum Status {
        /** (default status). */
        PRIVATE,
        /** shared status. */
        SHARED
    }
    /** Enumeration of different type of Distribution (cf. moodle doc). */
    public enum Distribution {
        /** (default distribution). */
        UNIFORM,
        /** log uniform distribution. */
        LOGUNIFORM
    }
    /** Enumeration of different type of DataSet Definition Type.
     * (cf. moodle doc) */
    public enum Type {
        /** (default type). */
        CALCULATED
    }



    /** Attribute status of the dataset_definition. */
    private Status status;
    /** Attribute name of the dataset_definition. */
    private String name;
    /** Attribute type of the dataset_definition. */
    private Type type;
    /** Attribute distribution of the dataset_definition. */
    private Distribution distribution;
    /** Attribute minimum of the dataset_definition. */
    private float minimum;
    /** Attribute maximum of the dataset_definition. */
    private float maximum;
    /** Attribute decimals of the dataset_definition. */
    private int decimals;
    /** Attribute items of the dataset_definition. */
    private DatasetItems items;


    /** Default constructor. */
    public DatasetDefinition() {
        status = Status.PRIVATE;
        name = "";
        type = Type.CALCULATED;
        distribution = Distribution.UNIFORM;
        minimum = 0.f;
        maximum = 0.f;
        decimals = 0;
        items = new DatasetItems();
    }






    /** @return the status. */
    public final Status getStatus() {
        return status;
    }
    /** @return the name. */
    public final String getName() {
        return name;
    }
    /** @return the type. */
    public final Type getType() {
        return type;
    }
    /** @return the distribution. */
    public final Distribution getDistribution() {
        return distribution;
    }
    /** @return the minimum. */
    public final float getMinimum() {
        return minimum;
    }
    /** @return the maximum. */
    public final float getMaximum() {
        return maximum;
    }
    /** @return the decimals. */
    public final int getDecimals() {
        return decimals;
    }
    /** @return the items. */
    public final DatasetItems getItems() {
        return items;
    }



    /** @param value the status to set. */
    public final void setStatus(final Status value) {
        this.status = value;
    }
    /** @param value the name to set. */
    public final void setName(final String value) {
        this.name = value;
    }
    /** @param value the type to set. */
    public final void setType(final Type value) {
        this.type = value;
    }
    /** @param value the distribution to set. */
    public final void setDistribution(final Distribution value) {
        this.distribution = value;
    }
    /** @param value the minimum to set. */
    public final void setMinimum(final float value) {
        this.minimum = value;
    }
    /** @param value the maximum to set. */
    public final void setMaximum(final float value) {
        this.maximum = value;
    }
    /** @param value the decimals to set. */
    public final void setDecimals(final int value) {
        this.decimals = value;
    }
    /** @param value the items to set. */
    public final void setItems(final DatasetItems value) {
        this.items = value;
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
        final Element xmlMain = new Element("dataset_definition");
        /* Save the base properties */
        exporter.addXMLTextMarker(xmlMain, "status",
                                            status.toString().toLowerCase());
        exporter.addXMLTextMarker(xmlMain, "name", name);
        exporter.addXMLMarker(xmlMain, "type", type.toString().toLowerCase());
        exporter.addXMLTextMarker(xmlMain, "distribution",
                                  distribution.toString().toLowerCase());
        exporter.addXMLTextMarker(xmlMain, "minimum", String.valueOf(minimum));
        exporter.addXMLTextMarker(xmlMain, "maximum", String.valueOf(maximum));
        exporter.addXMLTextMarker(xmlMain, "decimals",
                                  String.valueOf(decimals));
        /* Save the DataSet_Items */
        exporter.addXMLMarker(xmlMain, "itemcount", items.getCount());
        if (items.doExport(exporter, xmlMain)) {
            exporter.addXMLMarker(xmlMain, "number_of_items", items.getCount());
            inParentElement.appendChild(xmlMain);
        } else {
            bExportOk = false;
        }
        /* Return */
        return bExportOk;
    }




    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        /* Variable */
        boolean bImportOk = true;
        boolean haveName = false;
        boolean haveItemCount = false;
        int itemCount = 0;
        Element itemCountElement = null;
        /* Load property */
        final Elements childs = xml.getChildElements();
        for (int i = 0; (bImportOk) && (i < childs.size()); i++) {
            final Element child = childs.get(i);
            if (importer.isElementName(child, "name")) {
                name = importer.getMoodleText(child);
                haveName = (name != null);
                bImportOk = haveName;
            } else if (importer.isElementName(child, "itemcount")) {
                /* Keep the number of item to check if the real number
                 * of item is correct */
                final String str = importer.getMoodleText(child);
                bImportOk = (str != null);

                itemCount = Integer.parseInt(str);
                haveItemCount = bImportOk;
                itemCountElement = child;

            } else if (importer.isElementName(child, "dataset_items")) {
                bImportOk = items.doImport(importer, child);

            } else {
                /* Load other property */
                bImportOk = doPropertiesImport(importer, child);
            }
        }
        /* Return : check if there is a name and if the count of item is OK */
        if (bImportOk) {
            if (!haveName) {
                importer.getErrors().addMissingElement(xml,
                                                     "dataset_definition/name");
            }
            if (haveItemCount && (itemCount != items.getCount())) {
                importer.getWarnings().addBadValue(itemCountElement,
                                            String.valueOf(itemCount),
                                            "waited " + items.getCount());
            }
        }
        return (bImportOk) && (haveName);
    }


    /** Load XML properties of dataset_definition.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    private boolean doPropertiesImport(final XMLImporter importer,
                                         final Element xml) {
        boolean bImportOk = true;
        if (importer.isElementName(xml, "status")) {
            /* Get status */
            final String str = importer.getOptionalMoodleText(xml, "private");
            if ("private".equalsIgnoreCase(str)) {
                status = Status.PRIVATE;
            } else if ("shared".equalsIgnoreCase(str)) {
                status = Status.SHARED;
            } else {
                importer.getErrors().addBadValue(xml, "private|shared");
            }
        } else if (importer.isElementName(xml, "distribution")) {
            /* Get distribution */
            final String str = importer.getOptionalMoodleText(xml,
                                                              "uniform");
            if ("uniform".equalsIgnoreCase(str)) {
                distribution = Distribution.UNIFORM;
            } else if ("loguniform".equalsIgnoreCase(str)) {
                distribution = Distribution.LOGUNIFORM;
            } else {
                importer.getErrors().addBadValue(xml,
                                                 "uniform|loguniform");
            }
        } else {
            bImportOk = doPropertiesImport2(importer, xml);
        }
        /* Return */
        return bImportOk;
    }


    /** Load XML other properties of dataset_definition (... due to PMD limit).
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    private boolean doPropertiesImport2(final XMLImporter importer,
                                          final Element xml) {
        boolean bImportOk = true;
        if (importer.isElementName(xml, "type")) {
            /* Get type */
            final String str = importer.getOptionalMoodleText(xml,
                                                                "calculated");
            if ("calculated".equalsIgnoreCase(str)) {
                type = Type.CALCULATED;
            } else {
                importer.getErrors().addBadValue(xml, "calculated");
            }
        } else if (importer.isElementName(xml, "minimum")) {
            final String str = importer.getMoodleText(xml);
            if (str == null) {
                bImportOk = false;
            } else {
                minimum = Float.parseFloat(str);
            }
        } else if (importer.isElementName(xml, "maximum")) {
            final String str = importer.getMoodleText(xml);
            if (str == null) {
                bImportOk = false;
            } else {
                maximum = Float.parseFloat(str);
            }
        } else if (importer.isElementName(xml, "decimals")) {
            final String str = importer.getMoodleText(xml);
            if (str == null) {
                bImportOk = false;
            } else {
                decimals = Integer.parseInt(str);
            }
        } else {
            importer.getWarnings().addUnknonwElement(xml);
        }
        /* Return */
        return bImportOk;
    }


    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "DatasetDefinition [status=" + status + ", name=" + name
                + ", type=" + type + ", distribution=" + distribution
                + ", minimum=" + minimum + ", maximum=" + maximum
                + ", decimals=" + decimals + ", items=" + items + "]";
    }


}
