package fr.qxmlmoodle.exception;

/** Interface IError. for error */
public interface IError {

    /** @return the error type. */
    ImportErrorType getType();
    /** @return the error line */
    int getLine();
    /** @return the error tag */
    String getTag();
    /** @return the error description */
    String getDescription();


}
