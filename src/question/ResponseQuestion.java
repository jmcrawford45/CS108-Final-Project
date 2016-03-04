package question;

import java.util.ArrayList;

public class ResponseQuestion extends Question {

	public ResponseQuestion(String question, Answer answer) {
		super(question, answer);
		this.type = "response-question";
	}
	
	public ResponseQuestion(String question, String answer) {
		super(question, answer);
		this.type = "response-question";
	}
	
	
}
