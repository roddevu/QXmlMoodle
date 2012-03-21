package fr.qxmlmoodle.xml;

import nu.xom.Element;
import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.warning.IWarning;
import fr.qxmlmoodle.warning.ImportBadValueWarning;
import fr.qxmlmoodle.warning.ImportElementWarning;
import fr.qxmlmoodle.warning.ImportMissingWarning;
import fr.qxmlmoodle.warning.ImportWarning;

/** Class XMLWarnings contains all the warning. */
public class XMLWarnings extends MoodleXMLList<IWarning> {

    /** Generate a Unknown marker warning.
     * @param elt element XOM
     */
    public final void addUnknonwElement(final Element elt) {
        final ImportWarning warning = new ImportElementWarning(0,
                                                        elt.getQualifiedName());
        warning.setTag(elt.toString());
        add(warning);
    }
    /** Generate a Bad value warning.
     * @param elt element XOM
     * @param value the value
     * @param desc the description
     */
    public final void addBadValue(final Element elt, final String value,
                                                          final String desc) {
        final ImportWarning warning = new ImportBadValueWarning(0, value, desc);
        warning.setTag(elt.toString());
        add(warning);
    }
    /** Generate an missing warning.
     * @param elt element XOM
     * @param missingName the name which missing
     */
    public final void addMissing(final Element elt,
                                        final String missingName) {
        final ImportWarning warning = new ImportMissingWarning(0, missingName);
        warning.setTag(elt.toString());
        add(warning);
    }

}
