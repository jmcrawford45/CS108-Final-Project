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
