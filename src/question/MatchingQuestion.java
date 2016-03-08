package question;


import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class MatchingQuestion extends Question {
	
	ArrayList<ResponseQuestion> pairs;


	public MatchingQuestion(String instructions) {
		super(instructions, "");
		this.type = "matching-question";
	}
	
	public MatchingQuestion(String instructions, ArrayList<ResponseQuestion> pairs){
		super(instructions, "");
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
	
	public String getInstructions(){
		return getQuestion();
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
	
	
	@Override
	public String getAdditional(){
		String result = "";
		for(int i = 0; i < pairs.size(); i++){
			result += getPairAt(i).getID();
			result += "|";
		}
		return result;
	}
	
	public static ArrayList<ResponseQuestion> getPairsFromString(String input, Connection con){
		QuestionManager man = new QuestionManager(con);
		ArrayList<ResponseQuestion> result = new ArrayList<ResponseQuestion>();
		String[] inputArray = input.split("\\|");
		for (int i = 0; i < inputArray.length; i++){
			int id = Integer.parseInt(inputArray[i]);
			result.add((ResponseQuestion)man.getQuestion(id));
		}
		return result;
		
	}

}
