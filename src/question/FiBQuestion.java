package question;

import java.util.ArrayList;

public class FiBQuestion extends Question {
	
	String questionAfterBlank;
	String[] questionParts;

	public FiBQuestion(String question, Answer answer) throws InvalidFiBException{
		super(question, answer);
		questionParts = question.split("\\|");
		if(questionParts.length != 2) throw new InvalidFiBException("Fill-in-Blank question must have two parts, separated by '|'");
		this.type = "fib-question";
	}
	
	public FiBQuestion(String question, String answer) throws InvalidFiBException{
		super(question, answer);
		questionParts = question.split("\\|");
		if(questionParts.length != 2) throw new InvalidFiBException("Fill-in-Blank question must have two parts, separated by '|'");
		this.type = "fib-question";
	}
	
	public String getPreText(){
		return questionParts[0];
	}
	
	public String getPostText(){
		return questionParts[1];
	}

	public class InvalidFiBException extends Exception {
		  public InvalidFiBException() { super(); }
		  public InvalidFiBException(String message) { super(message); }
		  public InvalidFiBException(String message, Throwable cause) { super(message, cause); }
		  public InvalidFiBException(Throwable cause) { super(cause); }
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getPreText() + " ";
		result += "<input name=\"input\" type=\"text\"/> " + getPostText() + "\r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		result += getPreText() + " ";
		result += "<input name=\"input" + index + "\" type=\"text\"/> " + getPostText() + "\r";
		return result;
	}
	
}
