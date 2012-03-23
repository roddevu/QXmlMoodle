import fr.qxmlmoodle.Category;
import fr.qxmlmoodle.Quiz;
import fr.qxmlmoodle.commons.Feedback;
import fr.qxmlmoodle.question.*;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.text.*;


public class XmlCreator {

	/**
	 * Créer un fichier xml contenant deux question
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String outPath = args[0];
		System.out.println(outPath);
		
		/* Crée un quiz vide */
		Quiz quiz = new Quiz();
		
		/** 
		 * Crée une question vrai/faux
		 */
		AbstractQuestion qTR = new QuestionTrueFalse();
		/* ajout de la question au quiz */
		quiz.getQuestions().add(qTR);
		/* Crée le text de la question */
		QuestionText qText = new QuestionText();
		/* Ajout du text de la question */
		qText.setContent("Est-ce qu'on peut créer une question de type true/false?");
		qTR.setText(qText);		
		/* ajoute la category vari/faux*/
		qTR.setCategory(new Category("TRUEFALSE"));
		
		/**
		 * Crée une réponse 
		 */ 
		AbstractAnswer ans = qTR.createAnswer();
		/* ajoute le texte a la reponse */ 
		ans.setText("oui");
		/* crée le feedback */
		Feedback feedback = new Feedback();
		feedback.setText("Bien joué!");
		ans.setFeedback(feedback);
		/* ajoute une note */
		ans.setFraction(1);
		/* Ajout de la réponse à la question */
		qTR.getAnswers().add(ans);
				
		/** 
		 * Créer une deuxième réponse (sans feedback)
		 */ 
		ans = qTR.createAnswer();
		/* ajoute le texte a la reponse */ 
		ans.setText("non");
		/* ajoute une note */	
		ans.setFraction(1);
		/* Ajout de la réponse à la question */
		qTR.getAnswers().add(ans);		
		
		/* Affiche la question créé */
		showQuestion(qTR);
		
		
		/** 
		 * Crée une question à choix multiple
		 */
		qTR = new QuestionMultiChoice();
		/* ajout de la question au quiz */
		quiz.getQuestions().add(qTR);
		/* Crée le text de la question */
		qText = new QuestionText();
		/* Ajout su text de la question */
		qText.setContent("Quels Types de question peut on créer grace à cette API");
		qTR.setText(qText);		
		/* ajoute la category vari/faux*/
		qTR.setCategory(new Category("MULTICHOICE"));
		
		
		/**
		 * Crée une réponse 
		 */ 
		ans = qTR.createAnswer();
		/* ajoute le texte a la reponse */ 
		ans.setText("Des questions de type vrai/faux");
		/* ajoute une note */
		ans.setFraction(1);
		/* Ajout de la réponse à la question */
		qTR.getAnswers().add(ans);
		
				
		/** 
		 * Créer une deuxième réponse 
		 * (sans feedback)
		 */ 
		ans = qTR.createAnswer();
		/* ajoute le texte a la reponse */ 
		ans.setText("Des questions de type calcul");
		/* ajoute une note */	
		ans.setFraction(1);
		/* Ajout de la réponse à la question */
		qTR.getAnswers().add(ans);
		
		
		/** 
		 * Créer une troisième réponse -> fausse
		 */ 
		ans = qTR.createAnswer();
		/* ajoute le texte a la reponse */ 
		ans.setText("Il n'y a que deux type de question");
		/* ajoute une note (négative car la réponse est fausse) */
		ans.setFraction((float) -0.5);
		/* Ajout de la réponse à la question */
		qTR.getAnswers().add(ans);		

		/* Affiche la question créé */
		showQuestion(qTR);
		

		/* Sauvegarde le quiz dans xml */
		quiz.save(outPath);
	}
	/**
	 * Affichage de la question 
	 * @param q question à afficher
	 */
	private static void showQuestion(AbstractQuestion q){	
		/** 
		 * Affichage de la question
		 */
		System.out.println(q.getText().getContent());
		for(AbstractAnswer a: q.getAnswers()){
			System.out.println("\t" + a.getText());
			if (!a.getFeedback().getText().isEmpty())
				System.out.println("\t(feedback: " + a.getFeedback() + ")");
			System.out.println("\t(fraction: " + a.getFraction() + ")");
		}
		System.out.println();
	}

}
