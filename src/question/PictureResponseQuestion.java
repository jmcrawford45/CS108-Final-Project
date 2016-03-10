package question;

import java.util.ArrayList;

public class PictureResponseQuestion extends ResponseQuestion {
	
	private String picURL;

	public PictureResponseQuestion(String question, Answer answer, String url) {
		super(question, answer);
		picURL = url;
		this.type = "pic-reponse-question";
	}
	
	public PictureResponseQuestion(String question, String answer, String url) {
		super(question, answer);
		picURL = url;
		this.type = "pic-reponse-question";
	}
	
	public String getPicURL(){
		return picURL;
	}
	
	public void changePicURLTo(String url){
		this.picURL = url;
	}
	
	@Override
	public String getAdditional(){
		return this.picURL;
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
	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"addResponseQuestion\" method=\"post\"> \r";
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\"/> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "Enter pic URL: <br> \r <input name=\"url\" type=\"text\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}

}
