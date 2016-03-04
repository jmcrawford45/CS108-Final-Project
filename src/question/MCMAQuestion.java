package question;

import question.MCQuestion.InvalidMCException;

public class MCMAQuestion extends Question {
	
	String choices;
	String[] choiceArray;

	public MCMAQuestion(String question, Answer answer, String choices) throws InvalidMCMAException {
		super(question, answer);
		this.type = "mcma-question";
		this.choices = choices;
		this.choiceArray = choices.split("\\|");
		this.answers.setIfOrdered(false);
		if(!isValidChoice()) throw new InvalidMCMAException("At least one answer does not appear in choices.");
	}

	public MCMAQuestion(String question, String answer, String choices) throws InvalidMCMAException {
		super(question, answer);
		this.type = "mcma-question";
		this.choices = choices;
		this.choiceArray = choices.split("\\|");
		this.answers.setIfOrdered(false);
		if(!isValidChoice()) throw new InvalidMCMAException("At least one answer does not appear in choices.");
	}
	
	public int getNumChoices(){
		return choiceArray.length;
	}
	
	public String getChoice(int choiceIndex){
		return choiceArray[choiceIndex];
	}
	
	
	private boolean isValidChoice(){
		if(numAnswers() > choiceArray.length) return false;
		iter: for(int i = 0; i < numAnswers(); i++){
			String curr = this.answers.getAnswerAt(i);
			for(int x = 0; x < choiceArray.length; x++){
				if(curr.equals(choiceArray[x])) continue iter;
			}
			return false;
		}
		return true;
	}

	
	public class InvalidMCMAException extends Exception {
		  public InvalidMCMAException() { super(); }
		  public InvalidMCMAException(String message) { super(message); }
		  public InvalidMCMAException(String message, Throwable cause) { super(message, cause); }
		  public InvalidMCMAException(Throwable cause) { super(cause); }
	}
}
