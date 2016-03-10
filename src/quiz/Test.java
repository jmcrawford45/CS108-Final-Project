package quiz;

import java.util.ArrayList;

import question.Question;
import question.ResponseQuestion;

public class Test {
	
	public static ArrayList<Question> questions = qs();
	public static Quiz quiz = q();
			
	static Quiz q(){
		Quiz q = new Quiz(555, 444, 333, "desc", false, false, false, 0, "name", false);
		return q;
	}
			
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
