package fr.qxmlmoodle;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;
import fr.qxmlmoodle.xml.IXMLExport;
import fr.qxmlmoodle.xml.IXMLImport;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;

/** Class Category represented by a specific question type in moodle xml quiz.*/
public class Category implements IXMLImport, IXMLExport {

    /** Attributes name of the category. */
    private String name;
    /** Attributes XMLMarkerName only for PMD. */
    private static final String XMLMARKERNAME = "category";


    /** Default constructor. */
    public Category() {
        this.name = "";
    }
    /** Construct the category with a name.
     * @param nameValue the name of the category
     */
    public Category(final String nameValue) {
        this.name = nameValue;
    }


    /** @return the name. */
    public final String getName() {
        return name;
    }
    /** @param nameValue the name to set. */
    public final void setName(final String nameValue) {
        this.name = nameValue;
    }


    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        /* Save the base properties */
        final Element question = new Element("question");
        question.addAttribute(new Attribute("type", XMLMARKERNAME));
        exporter.addXMLTextMarker(question, XMLMARKERNAME, name);
        inParentElement.appendChild(question);
        /* Return OK */
        return true;
    }




    /** Load XML data.
     * @param importer the XML importer
     * @param xml element XOM
     * @return true if success, false otherwise
     */
    public final boolean doImport(final XMLImporter importer,
                                  final Element xml) {
        boolean bImportOk = true;
        /* Check if first child of Question is category */
        final Elements childs = xml.getChildElements();
        if ((childs != null) && (childs.size() > 0)) {
            if (importer.isElementName(childs.get(0), XMLMARKERNAME)) {
                name = importer.getMoodleText(childs.get(0));
            } else {
                importer.getErrors().addMissingElement(xml, XMLMARKERNAME);
                bImportOk = false;
            }
        }
        /* Return true if name is OK */
        return (bImportOk && (name != null));
    }



    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "Category [name=" + name + "]";
    }







}
