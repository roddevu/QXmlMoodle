package fr.qxmlmoodle.image;


import fr.qxmlmoodle.xml.IXMLExport;

/** Class Image represent a moodle image. */
public abstract class AbstractImage implements IXMLExport {

    /** Attribute format the format of the moodle XML image. */
    private final transient ImageFormat format;


    /** Constructor.
     * @param imageFormat the format of the moodle XML image
     */
    public AbstractImage(final ImageFormat imageFormat) {
        this.format = imageFormat;
    }

    /** @return the format of the image */
    public final ImageFormat getFormat() {
        return format;
    }



    /** Load the Image.data buffer using data format.
     * Method to override in different Image child class
     * @param imageData the moodle XML image data
     * @return true if success, false otherwise
     */
    public abstract boolean loadImage(final String imageData);


    /** Method toString.
     * @return string
     */
   public abstract String toString();


}
