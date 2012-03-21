package fr.qxmlmoodle.warning;

/** Interface IWarning for warning. */
public interface IWarning {

    /** @return the warning type. */
    ImportWarningType getType();
    /** @return the warning line. */
    int getLine();
    /** @return the warning tag. */
    String getTag();
    /** @return the warning description. */
    String getDescription();



}
