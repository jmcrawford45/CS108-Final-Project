package question;

import java.util.ArrayList;

public class MAResponseQuestion extends Question {
	
	protected boolean ordered;
	protected boolean caseSensitive;
	protected int numAnswers;

	public MAResponseQuestion(String question, ArrayList<ArrayList<String>> answers, boolean caseSensitive, boolean ordered) {
		super(question, answers);
		this.caseSensitive = caseSensitive;
		if(!caseSensitive){
			ArrayList<ArrayList<String>> newAnswers = new ArrayList<ArrayList<String>>();
			for(int j = 0; j < answers.size(); j++){
				ArrayList<String> newAnswer = new ArrayList<String>();
				ArrayList<String> currAnswer = answers.get(j);
				for(int i = 0; i < currAnswer.size(); i++){
					String currVariation = currAnswer.get(i).toUpperCase();
					newAnswer.add(currVariation);
				
				}
			}
			overrideAnswers(newAnswers);
		}
		this.ordered = ordered;
		this.numAnswers = answers.size();
	}
	
	public int numAnswers(){
		return numAnswers;
	}
	
	public boolean isCaseSensitive(){
		return caseSensitive;
	}
	
	public boolean isOrdered(){
		return ordered;
	}
	
	public boolean isCorrectAnswer(ArrayList<String> inputs){
		//if ordered question, check each input answer to see if it matches with any of the answer possibilities for the corresponding answer
		if(ordered){
			answerIter: for(int answer = 0; answer < this.answers.size(); answer++){
				for(int variation = 0; variation < this.answers.get(answer).size(); variation++){
					if(inputs.get(answer).equals(this.answers.get(answer).get(variation))) continue answerIter;
				}
				return false;
			}
			return true;
			
		} else {
			//if not an ordered question, make sure every element of the answers element has a match
			
			nextAnswer: for(int answer = 0; answer < this.answers.size(); answer++){
				ArrayList<String> curr = this.answers.get(answer);
				for(int poss = 0; poss < curr.size(); poss++){
					for(int possInput = 0; possInput < inputs.size(); possInput++){
						if(curr.get(poss).equals(inputs.get(possInput))) continue nextAnswer;
					}
				}
				return false;
			}
			return true;
		}
	}
	
	
	
	

}
