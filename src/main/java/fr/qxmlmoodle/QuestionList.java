package fr.qxmlmoodle;


import java.util.Locale;

import fr.qxmlmoodle.commons.MoodleXMLList;
import fr.qxmlmoodle.question.AbstractQuestion;
import fr.qxmlmoodle.question.QuestionType;

/** Class QuestionList contains all the question of a quiz. */
public class QuestionList extends MoodleXMLList<AbstractQuestion> {



    /** A factory that create question in function of the type.
     * @param type the type of the question to be create
     * @return The question if quiz support the type of the question
     */
    public final AbstractQuestion createQuestion(final QuestionType type) {
        return null;
    }

    /** Convert a string to QuestionType if the question type supported.
     * @param type the string representation of a question type
     * @return QuestionType corresponding of the string otherwise null
     */
    public final  QuestionType stringToquestionType(final String type) {
        /* Use of this variable due to PMD which doesn't allow
         * typeRet = null in the catch statements and doesn't allow to
         * let blank ...
         */
        final Locale loc = Locale.getDefault();
        boolean bTypeFound = true;
        QuestionType typeReturn = null;
        QuestionType typeRet = null;
        try {
            typeRet = QuestionType.valueOf(type.toUpperCase(loc));
        } catch (IllegalArgumentException err) {
            bTypeFound = false;
        }
        if (bTypeFound) {
            typeReturn = typeRet;
        }
        return typeReturn;
    }


}
