package fr.qxmlmoodle.xml;


import nu.xom.Document;
import nu.xom.Builder;
/** Class XMLExporter XML exporter helper. */
    /** Attributes errors list of errors. */
    private final transient XMLErrors errors;
    /** Attributes document XOM. */

    /** Default constructor. */
        errors = new XMLErrors();
    }

    /** @return the errors. */
    public final boolean load(final String url) {

        errors.clear();
        /* Load the XML document with XOM */
        try {
            final Builder parser = new Builder();
            doc = parser.build(url);
        } catch (ValidityException e) {
            errors.add(new ImportValidityError(e.getLineNumber(),
            loadOk = false;
        } catch (ParsingException e) {
            errors.add(new ImportSyntaxError(e.getLineNumber(),
            loadOk = false;
        } catch (IOException e) {
            errors.add(new ImportIOError(0, e.getMessage()));
        }
        return loadOk;
    }

    /** Get the root XOM element.
        Element root = null;
        return root;
    }
    public final boolean isElementName(final Element elt, final String name) {
        return elt.getQualifiedName().equalsIgnoreCase(name);
    }

    /** Get direct value or the value in marker text.
        boolean bFoundText = false;
    }

    public final String getMoodleText(final Element elt) {
    }

    /** Return the string value of an XML XOM element.
        String str = elt.getValue();
        if (str == null) {
        return str;
    }
    /** Return the integer value of an XML XOM element.
        final String str = elt.getValue();
        if (str != null) {
        return val;
    }
    public final float getElementValue(final Element elt,
        final String str = elt.getValue();
    }
    public final boolean getElementValue(final Element elt,
        final String str = elt.getValue();
    }

}