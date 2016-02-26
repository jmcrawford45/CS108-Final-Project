package question;

import java.util.ArrayList;

public class FiBQuestion extends Question {
	
	boolean caseSensitive;
	String questionAfterBlank;

	public FiBQuestion(String question, String questionAfterBlank, ArrayList<String> answer, boolean caseSensitive) {
		super(question, answer);
		this.caseSensitive = caseSensitive;
		if(!caseSensitive){
			ArrayList<String> newAnswers = new ArrayList<String>();
			for(int i = 0; i < answer.size(); i++){
				String curr = answer.get(i).toUpperCase();
				newAnswers.add(curr);
			}
			overrideAnswers(newAnswers);
		}
		this.questionAfterBlank = questionAfterBlank;
	}
	
	public String getPreText(){
		return getQuestion();
	}
	
	public String getPostText(){
		return questionAfterBlank;
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
