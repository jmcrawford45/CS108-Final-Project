package question;


public class ResponseQuestion extends Question {

	public ResponseQuestion(String question, Answer answer) {
		super(question, answer);
		this.type = "response-question";
	}
	
	public ResponseQuestion(String question, String answer) {
		super(question, answer);
		this.type = "response-question";
	}

	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getQuestion();
		result += "<br><br> \r";
		result += "<input name=\"input\" type=\"text\"/> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		result += getQuestion();
		result += "<br><br> \r";
		result += "<input name=\"input" + index + "\" type=\"text\"/> \r";
		return result;
	}
	
	
	
	
}
