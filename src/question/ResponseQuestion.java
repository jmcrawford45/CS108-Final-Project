package question;

import java.util.ArrayList;

public class ResponseQuestion extends Question {
	
	private boolean caseSensitive;

	public ResponseQuestion(String question, ArrayList<String> answers, boolean caseSensitive) {
		super(question, answers);
		this.caseSensitive = caseSensitive;
	}
	
	public boolean isCaseSensitive(){
		return caseSensitive;
	}
	
	@Override
	public boolean isCorrectAnswer(String answer){
		String inputAnswer = answer;
		if(!caseSensitive){
			inputAnswer = inputAnswer.toUpperCase();
		}
		return super.isCorrectAnswer(inputAnswer);
	}

}
