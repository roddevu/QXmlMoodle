
import fr.qxmlmoodle.*;
import fr.qxmlmoodle.question.AbstractQuestion;
import fr.qxmlmoodle.question.answer.AbstractAnswer;

public class Loader {

	/**
	 * Affiche les question et les reponse d un quiz
	 * 
	 * @param args agrs[1] chemin du fichier xml contenant le quiz
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		/* Verification des parametres du programme */
		if (args.length !=1){
			throw new Exception ("parametres incorects! Executer: Loader [inputXmlFile]");
		}
		
		Quiz quiz = new Quiz();
		
		/* Charge le fichier XML */
		if (quiz.load(args[0])) {
			System.out.println("Load OK");
			//System.out.println("Save = " + quiz.save("args[0]"));
		}
		/* Affiche les erreur dans le xml */
		if (quiz.getErrors().getCount() > 0) {
			System.out.println("-----------------");
			System.out.println(quiz.getErrors());
			return;
		}
		/* Affiche les warning dans le xml */
		if (quiz.getWarnings().getCount() > 0) {
			System.out.println("-----------------");
			System.out.println(quiz.getWarnings());
		}

		/** 
		 * show the quiz 
		 * all the questions and the possible answers
		 */
		System.out.println("------ Quiz -----");
		int i=1;
		/* pour chaque question du quiz */
		for(AbstractQuestion q: quiz.getQuestions() ){
			/* afficher le type de la question */
			System.out.println("question " + i + " (" + q.getType() + ")");
			/* afficher le texte de la question */
			System.out.println("\t" + q.getText().getContent());
			/* pour chaque question */
			for(AbstractAnswer ans : q.getAnswers()){
				if(!ans.getText().isEmpty()){
					/* afficher la réponse */
					String ansText = ans.getText();
					ansText = ansText.replaceAll("(\\r|\\n|\\t)", "");
					System.out.println("\t" + ansText);
					
					System.out.print("\t -> ");
					if (ans.getFraction() <=0)
						System.out.println("reponse incorrecte");
					else
						System.out.println("reponse correcte");
				}
			}
			System.out.println();
			i++;
		}
	}

}
