package fr.qxmlmoodle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import nu.xom.Element;
import nu.xom.Document;
import nu.xom.Elements;

import fr.qxmlmoodle.question.AbstractQuestion;
import fr.qxmlmoodle.question.QuestionType;
import fr.qxmlmoodle.xml.XMLErrors;
import fr.qxmlmoodle.xml.XMLExporter;
import fr.qxmlmoodle.xml.XMLImporter;
import fr.qxmlmoodle.xml.XMLWarnings;


/** Class Quiz representing in java object an moodle quiz. */
public class Quiz implements Iterable<AbstractQuestion> {

    /** Attributes questions the list of question. */
    private final transient QuestionList questions;
    /** Attributes category the list of category. */
    private final transient List<Category> category;
    /** Attributes xmlImporter the XML importer. */
    private final transient XMLImporter xmlImporter;
    /** Attributes xmlExporter the XML exporter. */
    private final transient XMLExporter xmlExporter;


    /** Default Constructor. */
    public Quiz() {
        questions = new QuestionList();
        category = new ArrayList<Category>();
        xmlImporter = new XMLImporter();
        xmlExporter = new XMLExporter();
    }


    /** Load XML Quiz moodle format.
     * @param url url of the XML format of an moodle quiz
     * @return true if import success false otherwise
     */
    public final boolean load(final String url) {
        boolean bImportOK = true;
        /* Clear all the question */
        questions.clear();
        /* Load the XML document */
        bImportOK = xmlImporter.load(url);

        /* Import */
        if (bImportOK && (!privLoadQuiz())) {
            questions.clear();
            bImportOK = false;
        }
        /* Load completed */
        return bImportOK;
    }


    /** Save the quiz in XML moodle format.
     * @return XML code
     */
    public final String exportToString() {
        /* Create XML context */
        boolean bExportOK = true;
        final Element root = xmlExporter.createXMLContext("quiz");
        if (root == null) {
            bExportOK = false;
        }

        /* Save all the question */
        Category currentCat = null;
        for (AbstractQuestion q : questions) {

            /* Check if new category */
            final Category quesCat = q.getCategory();
            if (!(quesCat.equals(currentCat))) {
                if (!(quesCat.doExport(xmlExporter, root))) {
                    bExportOK = false;
                    break;
                }
                currentCat = quesCat;
            }
            /* Save the question */
            if (!(q.doExport(xmlExporter, root))) {
                bExportOK = false;
                break;
            }

        }


        /* Create the XML Document */
        String result = "";
        if (bExportOK) {
            final Document doc = new Document(root);
            result = doc.toXML();
        }
        return result;
    }


    /** Save the quiz in XML moodle format in a file.
     * @param url the url where to save the quiz
     * @return true if success
     */
    public final boolean save(final String url) {
        /* Variable */
        boolean saveOk = true;
        File file = null;
        Writer fileWrite = null;

        /* Create XML Data */
        final String xml = exportToString();
        if (xml.isEmpty()) {
            saveOk = false;
        }

        /* Open the file */
        try {
            if (saveOk) {
                file = new File(url);
                fileWrite = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(file), "UTF8"));

                /* Create XML data */
                fileWrite.write(xml);
                fileWrite.flush();
                fileWrite.close();
            }
        } catch (UnsupportedEncodingException iox) {
            saveOk = false;
        } catch (IOException iox) {
            saveOk = false;
        } catch (Exception iox) {
            saveOk = false;
        } finally {
            if (fileWrite != null) {
                try {
                    fileWrite.close();
                } catch (IOException e) {
                    saveOk = false;
                }
            }
        }

        /* Return */
        return saveOk;
    }



    /** @return the questions. */
    public final QuestionList getQuestions() {
        return questions;
    }


    /** @return the errors. */
    public final XMLErrors getErrors() {
        return xmlImporter.getErrors();
    }
    /** @return the warnings. */
    public final XMLWarnings getWarnings() {
        return xmlImporter.getWarnings();
    }


    /** Load XML category.
     * @param xml element XOM
     * @return Category if success, null otherwise
     */
    private Category doImportCategory(final Element xml) {
        /* Load the category */
        Category retCat = null;
        final Category cat = new Category();
        if (cat.doImport(xmlImporter, xml)) {
            category.add(cat);
            retCat = cat;
        }
        return retCat;
    }


    /** Allow the usage of java for-each syntax with question.
     * @return a valid iterator for question */
    public final Iterator<AbstractQuestion> iterator() {
        return questions.iterator();
    }


    /** Load a XML quiz.
     * @return true if success false otherwise
     */
    private boolean privLoadQuiz() {
        /* Variable */
        boolean bLoadOk = true;
        Category currentCategory = null;
        /* Check that the root element is "quiz" */
        final Element root = xmlImporter.getRootElement();

        if (!(xmlImporter.isElementName(root, "quiz"))) {
            xmlImporter.getErrors().addBadElement(root, "quiz");
            bLoadOk = false;
        }

        /* Load all the questions if OK */
        final Elements childs = root.getChildElements();
        for (int i = 0; (bLoadOk) && (i < childs.size()); i++) {
            final Element child = childs.get(i);

            if (xmlImporter.isElementName(child, "question")) {

                final String str = child.getAttributeValue("type");
                if (str.equalsIgnoreCase("category")) {
                    /* Load category */
                    currentCategory = doImportCategory(child);
                    /* Check if loaded */
                    if (currentCategory == null) {
                        bLoadOk = false;
                        break;
                    }
                } else {
                    final QuestionType type =
                                        questions.stringToquestionType(str);
                    if (type == null) {
                        xmlImporter.getErrors().addAttrError(child, "type", str,
                                                 "multichoice|truefalse|"
                                                 + "shortanswer|matching|cloze|"
                                                 + "description|calculated|"
                                                 + "numerical|essay|category");
                    } else {
                        /* Create the question */
                        final AbstractQuestion ques =
                                                questions.createQuestion(type);
                        ques.setCategory(currentCategory);
                        /* Load the question */
                        if (!ques.doImport(xmlImporter, child)) {
                            bLoadOk = false;
                            break;
                        }
                        /* Add the question */
                        questions.add(ques);
                    }
                }

            } else {
                xmlImporter.getWarnings().addUnknonwElement(child);
            }

        }
        /* Return */
        return bLoadOk;
    }


    /** Method toString.
     * @return string
     */
   public final String toString() {
       return "Quiz [questions=" + questions + ", category=" + category + "]";
    }




}
