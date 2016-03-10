package question;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import question.MCQuestion.InvalidMCException;

public class MCQuestionTest {

	MCQuestion mcquestion;
	
	@Before
	public void setUp() throws Exception {
		mcquestion = new MCQuestion("Which one is correct?", "this one", "no|not this one|not this one either|this one");
		printQuestion(mcquestion);
		
		System.out.println();
		System.out.println(mcquestion.returnHTMLSingleQuestion());
		System.out.println();
		System.out.println(mcquestion.returnHTMLQuestion(2));
		
		
		
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
		assertEquals(false, mcquestion.isCorrectSingleAnswer("this doesn't even appear bro"));
		assertEquals(false, mcquestion.isCorrectSingleAnswer("incorrect"));
		assertEquals(false, mcquestion.isCorrectSingleAnswer("correct"));
		assertEquals(false, mcquestion.isCorrectSingleAnswer("nope"));
		assertEquals(false, mcquestion.isCorrectSingleAnswer("not this one either"));
		assertEquals(true, mcquestion.isCorrectSingleAnswer("this one"));
	}
	
	@Test
	public void failureTest(){
		try {
			MCQuestion fail = new MCQuestion("This is a question.", "this is an answer that does not appear", "these are answers|answer|ans");
			fail();
		} catch (InvalidMCException e) {
			// this is a success
		}
	}
	
	@Test
	public void failureTest2(){
		try {
			MCQuestion fail2 = new MCQuestion("This is another question", "this answer|has two answers", "this answer|has two answers|but is invalid");
			fail();
		} catch (InvalidMCException e) {
			// this is a success
		}
	}

}
