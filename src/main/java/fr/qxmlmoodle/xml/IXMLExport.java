package fr.qxmlmoodle.xml;

import nu.xom.Element;

/** Interface IXMLExport interface for exporting quiz in XML. */
public interface IXMLExport {

    /** Save XML Data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    boolean doExport(XMLExporter exporter, Element inParentElement);


}
