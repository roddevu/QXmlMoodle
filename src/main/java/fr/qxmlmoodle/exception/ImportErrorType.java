package fr.qxmlmoodle.exception;
/** Enumeration ImportErrorType enumerate the different type of error. */
public enum ImportErrorType {    /** Error concern XML validity. */
    VALIDITY,
    /** Error concern Input/Output error. */    IO,
    /** Error concern XML syntax. */    SYNTAX,
    /** Error concern attribute. */    ATTRIBUTE,
    /** Error concern element. */    ELEMENT,
    /** Error concern value (innerContent). */    VALUE,    /** Error concern other. */
    OTHER
}
