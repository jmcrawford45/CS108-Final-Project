package question;

import java.util.ArrayList;

public class MatchingQuestion {
	
	ArrayList<ResponseQuestion> pairs;
	String instructions;
	String type;

	public MatchingQuestion(String instructions) {
		this.instructions = instructions;
		this.type = "matching-question";
	}
	
	public MatchingQuestion(String instructions, ArrayList<ResponseQuestion> pairs){
		this.instructions = instructions;
		this.pairs = pairs;
		this.type = "matching-question";
	}
	
	public void addPair(ResponseQuestion pair){
		pairs.add(pair);
	}
	
	public void addPair(String question, String answer){
		ResponseQuestion pair = new ResponseQuestion(question, answer);
		pairs.add(pair);
	}
	
	public String getType(){
		return this.type;
	}
	
	public ResponseQuestion getPairAt(int index){
		return pairs.get(index);
	}
	
	public int getIndexQuestionExistsAt(String question){
		for (int i = 0; i < numPairs(); i++){
			ResponseQuestion curr = pairs.get(i);
			if(curr.getQuestion().equals(question)) return i;
		}
		return -1;
	}
	
	public boolean questionExists(String question){
		if (getIndexQuestionExistsAt(question) == -1) return false;
		else return true;
	}
	
	public boolean checkAnswerFor(String question, String answer){
		int index = getIndexQuestionExistsAt(question);
		ResponseQuestion curr = pairs.get(index);
		return curr.isCorrectSingleAnswer(answer);
	}
	
	public int numPairs(){
		return pairs.size();
	}
	
	public String getInstructions(){
		return instructions;
	}

}
