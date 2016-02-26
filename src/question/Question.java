package question;

import java.util.ArrayList;
import java.util.List;

public class Question {
	
	protected ArrayList <ArrayList<String>> answers;
	protected String question;
	protected boolean ordered;
	
	
	public Question(String question, ArrayList<String> answer){
		this.question = question;
		this.answers = new ArrayList<ArrayList<String>>();
		this.answers.add(answer);
	}
	
	public Question(String question, List<ArrayList<String>> answers){
		this.question = question;
		this.answers = (ArrayList<ArrayList<String>>) answers;
		
	}
	
	
	public boolean isCorrectAnswer(String input){
		ArrayList<String> answerPossibles = answers.get(0);
		for(int i = 0; i < answerPossibles.size(); i++){
			if(input.equals(answerPossibles.get(i))) return true;
		}
		return false;
		
	}
	
	public void overrideAnswers(ArrayList<String> newAnswer){
		ArrayList<ArrayList<String>> newAnswers = new ArrayList<ArrayList<String>>();
		newAnswers.add(newAnswer);
		answers = newAnswers;
	}
	
	public void overrideAnswers(List<ArrayList<String>> newAnswers){
		answers = (ArrayList<ArrayList<String>>) newAnswers;
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
		ArrayList<String> answerOptions = answers.get(0);
		return answerOptions.get(index);
	}
	
}
