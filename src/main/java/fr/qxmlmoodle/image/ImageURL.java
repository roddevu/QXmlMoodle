package fr.qxmlmoodle.image;

import nu.xom.Element;
import fr.qxmlmoodle.xml.XMLExporter;

/** Class ImageURL represent a moodle image URL. */
public class ImageURL extends AbstractImage {

    /** Attribute URL of the image. */
    private transient String url;

    /** Constructor.
     * @param urlValue the URL of the image
     */
    public ImageURL(final String urlValue) {
        super(ImageFormat.URL);
        this.url = urlValue;
    }


    /** Load the Image.data buffer using URL.
     * @param imageData URL of image file
     * @return true if load success
     */
    public final boolean loadImage(final String imageData) {
        url = imageData;
        // TODO Load image data by URL
        return true;
    }




    /** Save XML data.
     * @param exporter the XML exporter
     * @param inParentElement the XOM element container
     * @return true if success, false otherwise
     */
    public final boolean doExport(final XMLExporter exporter,
                                  final Element inParentElement) {
        /* Save the base properties */
        exporter.addXMLMarker(inParentElement, "image", url);
        /* Return OK */
        return true;
    }




    /** Method toString.
     * @return string
     */
   public final String toString() {
       return url;
    }

}
