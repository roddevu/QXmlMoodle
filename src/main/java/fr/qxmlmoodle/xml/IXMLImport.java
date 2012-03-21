package fr.qxmlmoodle.xml;

import nu.xom.Element;

/** Interface IXMLImport interface for importing quiz in XML. */
public interface IXMLImport {

    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    boolean doImport(XMLImporter importer, Element xml);


}
