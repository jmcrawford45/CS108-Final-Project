package quiz;

import java.util.ArrayList;

import question.Question;
import question.ResponseQuestion;

public class Test {
	
	public static ArrayList<Question> questions = qs();

			
	static ArrayList<Question> qs() {
		ArrayList<Question> qs = new ArrayList<Question>();
		for (int i = 0; i< 5; i++) {
			Question q = null;
			q = new ResponseQuestion("Question "+ i, "a"+i);
			qs.add(q);
		}
		return qs;
	}
	
	
}
