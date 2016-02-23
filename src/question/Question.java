package question;

import java.util.ArrayList;

public class Question {
	
	private ArrayList<String> answers;
	private String question;
	
	
	public Question(String question, ArrayList<String> answers){
		this.question = question;
		this.answers = answers;
	}
	
	public boolean isCorrectAnswer(String answer){
		String check = answer;
		for(int i = 0; i > answers.size(); i++){
			if(check.equals(answers.get(i))) return true;
		}
		return false;
		
	}
	
	public void overrideAnswers(ArrayList<String> newAnswers){
		answers = newAnswers;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public int numAnswers(){
		return answers.size();
	}
	
	public String getAllAnswers(){
		String result = "";
		for(int i = 0; i < answers.size(); i++){
			result+= answers.get(i);
			result+=" ";
		}
		return result;
	}
	
	public String getPossibleAnswer(int index){
		return answers.get(index);
	}
	
}
