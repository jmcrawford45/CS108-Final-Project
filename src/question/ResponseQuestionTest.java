package question;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class ResponseQuestionTest {
	
	Question work;

	@Before
	public void setUp() throws Exception {
		work = new ResponseQuestion("Does this work?", "yes|y");
		System.out.println(work.returnHTMLSingleQuestion());
		System.out.println();
		System.out.println(work.returnHTMLQuestion(1));
		System.out.println();
		
		System.out.println(ResponseQuestion.returnHTMLBlankTemplate());
	}

	@Test
	public void test() {
		assertTrue("Does this work?".equals(work.getQuestion()));
		assertTrue(work.isCorrectSingleAnswer("y"));
		assertTrue(work.isCorrectSingleAnswer("Y"));
		assertTrue(work.isCorrectSingleAnswer("yEs"));
		assertTrue(work.isCorrectSingleAnswer("yes"));
		
		assertFalse(work.isCorrectSingleAnswer("YYes"));
		
	}
	
	@Test
	public void invalidAnswerTest(){
		Answer wrongNumber = new Answer("yes");
		wrongNumber.addAnswer("y");
		
		assertFalse(work.isCorrectAnswer(wrongNumber));
		
	}
	

}
