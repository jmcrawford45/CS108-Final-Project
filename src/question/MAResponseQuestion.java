package question;

public class MAResponseQuestion extends Question {
	

	public MAResponseQuestion(String question, Answer answers) {
		super(question, answers);
		this.type = "maresponse-question";
	}
	
	public int numAnswers(){
		return this.answers.numAnswers();
	}
	
	@Override
	public String getAdditional(){
		return Boolean.toString(this.answers.isOrdered());
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getQuestion();
		result += "<br><br> \r";
		for(int i = 0; i < numAnswers(); i++){
			result += "<input type=\"text\" name=\"input_" + i + "\">  <br>\r";
		}
		result += "<br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		
		result += getQuestion();
		result += "<br><br> \r";
		for(int i = 0; i < numAnswers(); i++){
			result += "<input type=\"text\" name=\"input" + index + "_" + i + "\"> <br>\r";
		}

		return result;
	}
	

}
