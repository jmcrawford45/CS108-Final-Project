package question;

import java.util.ArrayList;

public class MCQuestion extends Question {
	
	String choices;
	String[] choiceArray;

	public MCQuestion(String question, Answer answer, String choices) throws InvalidMCException {

		super(question, answer);
		this.choices = choices;
		this.type = "mc-question";
		this.choiceArray = choices.split("\\|");
		if(!isValidChoice()) throw new InvalidMCException("Answer does not appear in choices.");
		}
	
	public MCQuestion(String question, String answer, String choices) throws InvalidMCException {

		super(question, answer);
		this.choices = choices;
		this.type = "mc-question";
		this.choiceArray = choices.split("\\|");
		if(!isValidChoice()) throw new InvalidMCException("Answer does not appear in choices.");

	}
	
	
	public int getNumChoices(){
		return choiceArray.length;
	}
	
	public String getChoice(int choiceIndex){
		return choiceArray[choiceIndex];
	}
	
	
	private boolean isValidChoice(){
		if(this.answers.hasMultipleAnswers()) return false;					//mult choice only has one solution
		for(int i = 0; i < choiceArray.length; i++){
			if(choiceArray[i].equals(answers.firstAnswer())) return true;		//if no options appear in answer, then not a valid MC question
		}
		return false;
	}
	
	public class InvalidMCException extends Exception {
		  public InvalidMCException() { super(); }
		  public InvalidMCException(String message) { super(message); }
		  public InvalidMCException(String message, Throwable cause) { super(message, cause); }
		  public InvalidMCException(Throwable cause) { super(cause); }
	}
	
}
