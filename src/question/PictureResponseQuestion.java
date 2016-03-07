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

}
