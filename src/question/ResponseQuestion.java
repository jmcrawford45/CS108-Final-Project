package question;

import java.util.ArrayList;

public class ResponseQuestion extends Question {
	
	private boolean caseSensitive;

	public ResponseQuestion(String question, ArrayList<String> answer, boolean caseSensitive) {
		super(question, answer);
		this.caseSensitive = caseSensitive;
		if(!caseSensitive){
			ArrayList<String> newAnswer = new ArrayList<String>();
			for(int i = 0; i < answer.size(); i++){
				String curr = answer.get(i).toUpperCase();
				System.out.println("Added: " + curr);
				newAnswer.add(curr);
			}
			overrideAnswers(newAnswer);
		}
	}
	
	public boolean isCaseSensitive(){
		return caseSensitive;
	}
	
	@Override
	public boolean isCorrectAnswer(String answer){
		String inputAnswer = answer;
		if(!caseSensitive){
			inputAnswer = answer.toUpperCase();
		}
		return super.isCorrectAnswer(inputAnswer);
	}

}
