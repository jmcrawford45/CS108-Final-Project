package question;

import java.util.ArrayList;

public class MAResponseQuestion extends Question {
	

	public MAResponseQuestion(String question, Answer answers) {
		super(question, answers);
		this.type = "maresponse-question";
	}
	
	@Override
	public String getAdditional(){
		return Boolean.toString(this.answers.isOrdered());
	}
	

}
