import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.AbstractAction;

import fr.qxmlmoodle.Quiz;
import fr.qxmlmoodle.question.AbstractQuestion;
import fr.qxmlmoodle.question.QuestionNumerical;
import fr.qxmlmoodle.question.QuestionType;
import fr.qxmlmoodle.question.answer.AbstractAnswer;
import fr.qxmlmoodle.question.answer.AnswerGeneral;
import fr.qxmlmoodle.question.answer.AnswerNumerical;

public class AnswerTheQuiz {

	/**
	 * Lance un quiz en ligne de commandes
	 * 
	 * @param args arg[0] chemin du fichier xml contenant le quiz
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		/* Verification des parametres du programme */
		if (args.length !=1){
			throw new Exception ("parametres incorects! Executer: Loader [inputXmlFile]");
		}
		
		/* Crée un quiz vide */
		Quiz quiz = new Quiz();
		
		/* Charge le fichier XML */
		if (quiz.load(args[0])) {
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
		
		double quizScore = 0;		
		double maxScore = 0;
		
		/* load the xml file */
		quiz.load(args[0]);

		System.out.println();
		System.out.println("------- Quiz -------");
		System.out.println();
		System.out.println("# Enter votre/vos réponse puis taper ok pour valider #");
		System.out.println();
		
		int i=1;
		double maxQuizScore = 0;
		
		/* Pour chaque question */
		for(AbstractQuestion q: quiz.getQuestions() ){
			
			/**
			 * On affiche les question sauf celles qui ne sont pas gérées dans cette exemple
			 */
			if(q.getType() != QuestionType.CALCULATED && q.getType() != QuestionType.CLOZE
					&& q.getType() != QuestionType.MATCHING){
				System.out.println("question " + i + " (" + q.getType() + ")");	
				System.out.println("\t" + q.getText().getContent());	
				
				int j =1;				
				double maxQuestionScore = 0;
				
				/* si il y a des réponses */
				if(q.getAnswers().getCount() > 0){					
					/* Pour chaque question */
					
					/**
					 * On affiche les reponses si besoin
					 */
					for(AbstractAnswer ans : q.getAnswers()){
						if(q.getType() != QuestionType.NUMERICAL &&
								q.getType() != QuestionType.MATCHING &&
								q.getType() != QuestionType.CALCULATED &&  
								q.getType() != QuestionType.ESSAY &&  
								q.getType() != QuestionType.SHORTANSWER ){
							String ansText = ans.getText();
					        ansText = ansText.replaceAll("(\\r|\\n)", "");
							System.out.println("\t" + j + ")" + ansText);
						}

						
						/**
						 * On calcul le score maximum de la question
						 */
						double ansScore = ans.getFraction();
						switch(q.getType()){
							/* si c est une question a plusieurs reponses */
							case MATCHING:
								if (ansScore > 0)
									/* On ajoute le score de reponse au score  maximum de la question */
									maxQuestionScore += ansScore;
								break;
							/* si c est une question a plusieurs reponses */
							case MULTICHOICE:
								if (ansScore > 0)
									/* On ajoute le score de reponse au score  maximum de la question */
									maxQuestionScore += ansScore;
								break;
							/* sinon le score maximum de la question est le plus grand score des reponse*/
							default: 
								maxQuestionScore= ansScore>=maxQuestionScore?ansScore:maxQuestionScore;
						}
						j++;
					}
					
					/**
					 * On traite la question
					 * et met a jour le score 
					 */
					quizScore += treatAnswer(q);
					/* on ajoute le score maximum de la question au maximum du quiz */
					maxQuizScore += maxQuestionScore;
				}	
				i++;
				System.out.println();
			}		
		}
		/* Ramener le score sur 20 */
		quizScore = quizScore/maxQuizScore * 20;
		System.out.println("votre score est de " + quizScore + "/20");

	}
	/**
	 * traite la reponse
	 * 
	 * @param q question
	 * @return score obtenu
	 * @throws IOException
	 */
	static private double treatAnswer(AbstractQuestion q) throws IOException{
		
		/* buffer d entrer */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String input;
		int intputInt = -1;
		
		/* flag */
		boolean answered=false;			// a repondu
		boolean okTyped=false;			// a tape "ok"
		boolean severalAnswer=false;	// question a plusieur reponse
		
		/* score de la reponse */
		double ansScore = 0;			
		
		/* lire le résultat entrer par l'utilisateur */
		do {
	
			/* lecture du buffer d entrer */
			input = in.readLine();
			
			/* Si l'utilisateur a rentre sa réponse et appuye sur ok */
			if (answered && input.equalsIgnoreCase("ok"))
				okTyped = true;
			/* si l'utilisateur essaye de repondre a plusieur question sur question a reponse unique */ 
			else if (answered && !input.equalsIgnoreCase("ok") && !severalAnswer)
				System.out.println("vous ne pouvez rentrer qu une reponse. Tapez \"ok\" pour valider.");
			/* sinon */
			else{				
				switch(q.getType()){
				
					/* question de type numérique */
					case NUMERICAL:
						try{			
							/* on essaye de convertir l entrer en flotant */				
							float intputFloat = Float.parseFloat(input);
							
							QuestionNumerical qN = ((QuestionNumerical) q);
							/** 
							 * On verifie la reponse
							 */							
							for(int i=0; i<q.getAnswers().getCount(); i++){
								AnswerNumerical ans = (AnswerNumerical)q.getAnswers().get(i);
								
								/* on recupere une des reponse possible */
								float ansFloat= Float.parseFloat(ans.getText());
								/* la tolerance qui correspond */
								float tolerance = ans.getTolerance();
								
								/* si la reponse donnée est dans la fourchette attendu */
								if(ansFloat+tolerance >= intputFloat && ansFloat - tolerance <= intputFloat){
									/* on récupere le score */
									ansScore += q.getAnswers().get(0).getFraction();
								}
								answered = true;
							}
						}
						/* si l entre ne peut pas etre converti en float */
						catch (NumberFormatException e){
							System.out.println("veuillez rentrer nombre comme réponse puis \"ok\"");
						}						
						break;
						
					/* question de type "courte" */
					case SHORTANSWER:
						int j= 0;
						for(int i=0; i<q.getAnswers().getCount(); i++){
							
							/**
							 * on compare la reponse entre et les reponse possible 
							 */
							AnswerGeneral ans = (AnswerGeneral)q.getAnswers().get(i);		
							String ansTextFormatted = ans.getText().replaceAll("(\\r|\\n|\\t|\\s)", "");
							String inputTextFormated = input.replaceAll("(\\r|\\n|\\t|\\s)", "");
							
							/* si c est identique */ 
							if(ansTextFormatted.equalsIgnoreCase(inputTextFormated)){
								/* on met a jour le score */
								ansScore += q.getAnswers().get(j).getFraction();
							}							
							answered = true;
							j++;
						}
					answered = true;
					break;
					/* question de type essaie */
					case ESSAY:
						/*
						 * TO FILL
						 * corriger l'essaye ou l'enregistrer...
						 */
						answered = true;
						break;
					/* question de type choix multiple */
					case MULTICHOICE :	
						severalAnswer = true;
					/* autre type question */
					default :
						try{
							intputInt = Integer.parseInt(input);
							/* si l'utilsateur a rentrer un chiffre qui correspond à une reponse */
							if(intputInt>=1 && intputInt<=q.getAnswers().getCount()){
								answered = true;
								/* on enregistre le score de la réponse (positif ou négatif) */
								ansScore += q.getAnswers().get(intputInt-1).getFraction();
							}
							/* Autrement on affiche un message d'erreur */
							else
								System.out.println(intputInt + " ne correspond pas a une reponse");
						}
						/* si l'utilisateur ne rentre pas un entier ou "ok"
						 *  on affiche un message d'erreur */
						catch (NumberFormatException e){
							System.out.println("veuillez rentrer le numero de votre/vos réponse(s) puis \"ok\"");
						}
				}
			}
		/* tant que il n a pas rentrer "ok" */
		}while (!okTyped);
		return ansScore;
	}

}
