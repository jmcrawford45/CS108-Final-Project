package question;

import java.util.ArrayList;

public class Answer {

	private ArrayList<String> answers;
	int answerID;
	
	public Answer(String answer){
		ArrayList<String> array = new ArrayList<String>();
		array.add(answer);
		this.answers = array;
	}
	
	public Answer(ArrayList<String> answers){
		this.answers = answers;
	}
	
	public boolean hasMultipleAnswers(){
		return (answers.size() > 1);
	}
	
	public int numAnswers(){
		return answers.size();
	}
	
	public String getAnswerAt(int index){
		return answers.get(index);
	}
	
	public String[] getAnswerArrayAt(int index){
		return answers.get(index).split("|");
	}
	
	public void addAnswer(String newAnswer){
		this.answers.add(newAnswer);
	}
	
	public void replaceAnswer(String switchedAnswer, int index){
		this.answers.set(index, switchedAnswer);
	}
	
	public void removeAnswer(int index){
		this.answers.remove(index);
	}
	
	public boolean isCorrectSingle(String input){
		String[] answer = answers.get(0).split("\\|");
		return isCorrectSingleAnswer(answer, input);
	}
	
	public boolean isCorrect(Answer userInput){
		if(userInput.numAnswers() != numAnswers()) return false;
		else {
			for (int a = 0; a < numAnswers(); a++){
				String[] answer = answers.get(a).split("|");
				if (!isCorrectSingleAnswer(answer,userInput.getAnswerAt(a))) return false;
			}
			return true;
		}
	}
	
	public boolean isCorrect(Answer userInput, boolean ordered){
		if(userInput.numAnswers() != numAnswers()) return false;
		if(ordered){
			return isCorrect(userInput);
		} else {
			iter: for (int a = 0; a < numAnswers(); a++){
				String[] answer = answers.get(a).split("|");
				for (int i = 0; i < userInput.numAnswers(); i++){
					if (isCorrectSingleAnswer(answer, userInput.getAnswerAt(i))) continue iter;
				}
				return false;
			}
			return true;
		}
	}
	
	private boolean isCorrectSingleAnswer(String [] options, String input){
		for (int i = 0; i < options.length; i++){
			if (options[i].toUpperCase().equals(input.toUpperCase())) return true;
		}
		return false;
	}
	
	
}
