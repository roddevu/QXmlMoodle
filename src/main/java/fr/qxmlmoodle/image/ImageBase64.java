package fr.qxmlmoodle.image;

import fr.qxmlmoodle.xml.XMLExporter;
import nu.xom.Element;


/** Class ImageBase64 represent a moodle image encoded in base64. */
public class ImageBase64 extends AbstractImage {

    /** Attribute base64Code. */
    private transient String base64Code;


    /** Constructor.
     * @param base64CodeValue base64 code
     */
    public ImageBase64(final String base64CodeValue) {
        super(ImageFormat.BASE64);
        this.base64Code = base64CodeValue;
    }


    /** Load the Image.data buffer using base64.
     * @param imageData base64 code of image
     * @return true if load success
     */
    public final boolean loadImage(final String imageData) {
        base64Code = imageData;
        // TODO the base64 convert
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
        exporter.addXMLMarker(inParentElement, "image_base64", base64Code);
        /* Return OK */
        return true;
    }





    /** Method toString.
     * @return string
     */
   public final String toString() {
       return base64Code;
    }

}
