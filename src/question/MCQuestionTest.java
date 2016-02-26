package question;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MCQuestionTest {

	MCQuestion mcquestion;
	
	@Before
	public void setUp() throws Exception {
		ArrayList<String> answer = new ArrayList<String>();
		answer.add("correct");
		ArrayList<String> possibles = new ArrayList<String>();
		possibles.add("incorrect");
		possibles.add("nope");
		possibles.add("correct");
		possibles.add("not this one either");
		
		mcquestion = new MCQuestion("Which one is correct?", answer, possibles);
		printQuestion(mcquestion);
		
		
		
	}

	public void printQuestion(MCQuestion question) {
		System.out.println(question.getQuestion());
		for (int i = 0; i < question.getNumChoices(); i++){
			System.out.println(question.getChoice(i));
		}
	}
	
	@Test
	public void mcQuestionTest(){
		assertEquals(4, mcquestion.getNumChoices());
		assertEquals(false, mcquestion.isCorrectAnswer("this doesn't even appear bro"));
		assertEquals(false, mcquestion.isCorrectAnswer("incorrect"));
		assertEquals(true, mcquestion.isCorrectAnswer("correct"));
		assertEquals(false, mcquestion.isCorrectAnswer("nope"));
		assertEquals(false, mcquestion.isCorrectAnswer("not this one either"));
	}

}
