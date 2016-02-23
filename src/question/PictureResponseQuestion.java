package question;

import java.util.ArrayList;

public class PictureResponseQuestion extends ResponseQuestion {
	
	private String picURL;

	public PictureResponseQuestion(String question, ArrayList<String> answers, boolean caseSensitive, String url) {
		super(question, answers, caseSensitive);
		picURL = url;
	}
	
	public String getPicURL(){
		return picURL;
	}

}
