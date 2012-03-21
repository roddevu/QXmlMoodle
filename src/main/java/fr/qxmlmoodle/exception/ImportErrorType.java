package fr.qxmlmoodle.exception;

public enum ImportErrorType {
    VALIDITY,
    /** Error concern Input/Output error. */
    /** Error concern XML syntax. */
    /** Error concern attribute. */
    /** Error concern element. */
    /** Error concern value (innerContent). */
    OTHER
}