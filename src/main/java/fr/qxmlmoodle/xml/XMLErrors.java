package fr.qxmlmoodle.xml;

import nu.xom.Element;
import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.exception.IError;
import fr.qxmlmoodle.exception.ImportAttributeError;
import fr.qxmlmoodle.exception.ImportElementError;
import fr.qxmlmoodle.exception.ImportError;
import fr.qxmlmoodle.exception.ImportValueError;

/** Class XMLErrors contains all the error. */
public class XMLErrors extends MoodleXMLList<IError> {


    /** Generate a Bad value error.
     * @param elt element XOM
     * @param waitedValue the value waited
     */
    public final void addBadValue(final Element elt,
                                       final String waitedValue) {
        final ImportError err = new ImportValueError(0,
                                        elt.getQualifiedName(), waitedValue);
        err.setTag(elt.toString());
        add(err);
    }
    /** Generate a missing value error.
     * @param elt element XOM
     * @param waitedValue the value waited
     */
    public final void addMissingValue(final Element elt,
                                           final String waitedValue) {
        final ImportError err = new ImportValueError(0, waitedValue);
        err.setTag(elt.toString());
        add(err);
    }
    /** Generate a Bad marker error.
     * @param elt element XOM
     * @param waitedElement the element waited
     */
    public final void addBadElement(final Element elt,
                                         final String waitedElement) {
        final ImportError err = new ImportElementError(0,
                                    elt.getQualifiedName(), waitedElement);
        err.setTag(elt.toString());
        add(err);
    }
    /** Generate a Missing marker error.
     * @param elt element XOM
     * @param waitedElement the element waited
     */
    public final void addMissingElement(final Element elt,
                                             final String waitedElement) {
        final ImportError err = new ImportElementError(0, waitedElement);
        err.setTag(elt.toString());
        add(err);
    }
    /** Generate a Attribute error.
     * @param elt element XOM
     * @param attrName the attribute
     * @param attrValue the attribute current value
     * @param attrWaited the value waited
     */
    public final void addAttrError(final Element elt, final String attrName,
                                   final String attrValue,
                                   final String attrWaited) {
        final ImportError err = new ImportAttributeError(0, attrName,
                                                      attrValue, attrWaited);
        err.setTag(elt.toString());
        add(err);
    }


}
