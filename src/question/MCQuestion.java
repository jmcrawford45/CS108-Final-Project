package question;

import java.util.ArrayList;

public class MCQuestion extends Question {
	
	ArrayList<String> choices;

	public MCQuestion(String question, ArrayList<String> answer, ArrayList<String> choices) throws InvalidMCException {

		super(question, answer);
		if(!isValidChoice(answer, choices)) throw new InvalidMCException("Answer does not appear in choices.");
		this.choices = choices;
	}
	
	
	public int getNumChoices(){
		return choices.size();
	}
	
	public String getChoice(int choiceIndex){
		return choices.get(choiceIndex);
	}
	
	@Override
	public boolean isCorrectAnswer(String input){
		System.out.println("Comparing " + input + " against " + answers.get(0).get(0) + " resulting in " + input.equals(answers.get(0).get(0)));
		if (input.equals(answers.get(0).get(0))) return true;
		else return false;
	}
	
	
	private boolean isValidChoice(ArrayList<String> answer, ArrayList<String> options){
		if(answer.size() > 1) return false;					//mult choice only has one solution
		for(int i = 0; i < options.size(); i++){
			if(options.get(i).equals(answer.get(0))) return true;		//if no options appear in answer, then not a valid MC question
		}
		return false;
	}
	
	private class InvalidMCException extends Exception {
		  public InvalidMCException() { super(); }
		  public InvalidMCException(String message) { super(message); }
		  public InvalidMCException(String message, Throwable cause) { super(message, cause); }
		  public InvalidMCException(Throwable cause) { super(cause); }
	}
	
}
