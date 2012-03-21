package fr.qxmlmoodle.xml;
import java.io.IOException;
import fr.qxmlmoodle.exception.ImportIOError;import fr.qxmlmoodle.exception.ImportSyntaxError;import fr.qxmlmoodle.exception.ImportValidityError;
import nu.xom.Document;import nu.xom.Element;import nu.xom.Elements;
import nu.xom.Builder;import nu.xom.ValidityException;import nu.xom.ParsingException;
/** Class XMLExporter XML exporter helper. */public class XMLImporter {
    /** Attributes errors list of errors. */
    private final transient XMLErrors errors;    /** Attributes warnings list of warnings. */    private final transient XMLWarnings warnings;
    /** Attributes document XOM. */    private transient Document doc;    /** Constant "text" for PMD. */    private static final transient String XMLTEXTMARKER = "text";

    /** Default constructor. */    public XMLImporter() {
        errors = new XMLErrors();        warnings = new XMLWarnings();
    }

    /** @return the errors. */    public final XMLErrors getErrors() {        return errors;    }    /** @return the warnings. */    public final XMLWarnings getWarnings() {        return warnings;    }    /** Load an moodle XML quiz document with XOM.     * @param url the XML quiz moodle URL     * @return true if success, false otherwise     */
    public final boolean load(final String url) {
        boolean loadOk = true;        /* Clear all the errors and warnings */
        errors.clear();        warnings.clear();
        /* Load the XML document with XOM */
        try {
            final Builder parser = new Builder();
            doc = parser.build(url);
        } catch (ValidityException e) {
            errors.add(new ImportValidityError(e.getLineNumber(),                                        "ErrorCount = " + e.getErrorCount()));
            loadOk = false;
        } catch (ParsingException e) {
            errors.add(new ImportSyntaxError(e.getLineNumber(),                                             e.getMessage()));
            loadOk = false;
        } catch (IOException e) {
            errors.add(new ImportIOError(0, e.getMessage()));            loadOk = false;
        }        /* Return */
        return loadOk;
    }

    /** Get the root XOM element.     * @return the root XOM element     */    public final Element getRootElement() {
        Element root = null;        if (doc != null) {            root = doc.getRootElement();        }
        return root;
    }    /** Check if the marker is name.     * @param elt element XOM     * @param name the name to test     * @return true if marker = name     */
    public final boolean isElementName(final Element elt, final String name) {
        return elt.getQualifiedName().equalsIgnoreCase(name);
    }

    /** Get direct value or the value in marker text.     * @param elt element XOM     * @param defaultValue the default value to return if no text marker     * @return text value     */    public final String getOptionalMoodleText(final Element elt,                                              final String defaultValue) {
        boolean bFoundText = false;        String value = defaultValue;        /* Search a child marker text */        final Elements childs = elt.getChildElements();        for (int i = 0; i < childs.size(); i++) {            final Element child = childs.get(i);            if (isElementName(child, XMLTEXTMARKER)) {                value = child.getValue();                bFoundText = true;                break;            } else {                warnings.addUnknonwElement(elt);            }        }        /* If not found and have child problem */        if (!bFoundText) {            if (childs.size() > 0) {                warnings.addMissing(elt, XMLTEXTMARKER);            } else {                /* Value = the Inner Content of element */                value = elt.getValue();            }        }        /* Return */        return value;
    }
    /** Get direct value or the value in marker text.     *  And if element have child and no text marker generate an error     * @param elt element XOM     * @return text value     */
    public final String getMoodleText(final Element elt) {        boolean bFoundText = false;        String value = null;        /* Search a child marker text */        final Elements childs = elt.getChildElements();        for (int i = 0; i < childs.size(); i++) {            final Element child = childs.get(i);            if (isElementName(child, XMLTEXTMARKER)) {                value = child.getValue();                bFoundText = true;                break;            } else {                warnings.addUnknonwElement(elt);            }        }        /* If not found and have child problem */        if (!bFoundText) {            if (childs.size() > 0) {                warnings.addMissing(elt, XMLTEXTMARKER);            } else {                /* Value = the Inner Content of element */                value = elt.getValue();            }        }        /* Return */        return value;
    }

    /** Return the string value of an XML XOM element.     * @param elt element XOM     * @param defaultValue the defaultValue returned if no InnerContent     * @return InnerContent of the XOM element     */    public final String getElementValue(final Element elt,                                        final String defaultValue) {
        String str = elt.getValue();
        if (str == null) {            str = defaultValue;        }
        return str;
    }
    /** Return the integer value of an XML XOM element.     * @param elt element XOM     * @param defaultValue the defaultValue returned if no InnerContent     * @return InnerContent of the XOM element     */    public final int getElementValue(final Element elt,                                     final int defaultValue) {
        final String str = elt.getValue();        int val = defaultValue;
        if (str != null) {            val = Integer.parseInt(str);        }
        return val;
    }    /** Return the float value of an XML XOM element.     * @param elt element XOM     * @param defaultValue the defaultValue returned if no InnerContent     * @return InnerContent of the XOM element     */
    public final float getElementValue(final Element elt,                                       final float defaultValue) {
        final String str = elt.getValue();        float val = defaultValue;        if (str != null) {            val = Float.parseFloat(str);        }        return val;
    }    /** Return the boolean value of an XML XOM element.     * @param elt element XOM     * @param trueValue the value which represent true     * @param defaultValue the defaultValue returned if no InnerContent     * @return InnerContent of the XOM element     */
    public final boolean getElementValue(final Element elt,                                         final String trueValue,                                         final boolean defaultValue) {
        final String str = elt.getValue();        boolean val = defaultValue;        if (str != null) {            val = str.equals(trueValue);        }        return val;
    }

}
