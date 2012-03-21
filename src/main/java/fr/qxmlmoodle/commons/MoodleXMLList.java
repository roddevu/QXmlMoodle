package fr.qxmlmoodle.commons;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** Class MoodleXMLList<T> is a generic class for stock element.
 * @param <T> type of the element */
public class MoodleXMLList<T> implements Iterable<T> {

    /** Attribute lst liste of element. */
    private final transient List<T> lst;


    /** Default constructor. */
    public MoodleXMLList() {
        lst = new ArrayList<T>();
    }


    /** Clear all the element. */
    public final void clear() {
        lst.clear();
    }


    /** Add element.
     * @param element element to add
     */
    public final void add(final T element) {
        if (element != null) {
            lst.add(element);
        }
    }
    /** Insert element at position.
     * @param element element to insert
     * @param position the position where insert the element
     */
    public final void add(final T element, final int position) {
        /* Correct the position if necessary */
        int positionCorrected = position;
        if (positionCorrected < 0) {
            positionCorrected = 0;
        }
        if (positionCorrected > lst.size()) {
            positionCorrected = lst.size();
        }
        /* Insert if not null */
        if (element != null) {
            lst.add(positionCorrected, element);
        }
    }


    /** Remove the element at id.
     * @param position the position of the element
     * @return true if element removed, false if id not valid
     */
    public final boolean remove(final int position) {
        boolean bRemoveOK = true;
        /* Check if position is valid */
        if ((position < 0) || (position >= lst.size())) {
            bRemoveOK = false;
        }
        /* Remove */
        if (bRemoveOK) {
            lst.remove(position);
        }
        return bRemoveOK;
    }


    /** Remove the first occurrence of an element.
     * @param element element to remove
     * @return false if not removed
     */
    public final boolean remove(final T element) {
        int pos = 0;
        boolean bFoundElement = false;
        /* Search the element */
        for (T e : lst) {
            pos++;
            if (e == element) {
                /* Found so remove the element and return true */
                lst.remove(pos);
                bFoundElement = true;
                break;
            }
        }
        /* If not found return false */
        return bFoundElement;
    }


    /** Get the number of element.
     * @return element count
     */
    public final int getCount() {
        return lst.size();
    }



    /** Get a element.
     * @param position the position of the element
     * @return element at position or null if not found
     */
    public final T get(final int position) {
        T element = null;
        /* Check if position is valid */
        if ((position >= 0) && (position < lst.size())) {
            element = lst.get(position);
        }
        /* Return the element */
        return element;
    }


    /** Change the element at position.
     * @param position the position of the element
     * @param element new element to set
     * @return true if position is valid
     */
    public final boolean set(final int position, final T element) {
        boolean bSetOk = false;
        /* Check if position is valid */
        if ((position > 0) && (position < lst.size())) {
            lst.set(position, element);
            bSetOk = true;
        }
        /* Set */
        return bSetOk;
    }


    /** Allow the usage of java for-each syntax with element.
     * @return a valid iterator for element */
    public final Iterator<T> iterator() {
        return lst.iterator();
    }


    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "MoodleXMLList [lst=" + lst + "]";
    }



}
