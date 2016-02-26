package question;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class ResponseQuestionTest {
	
	Question workInsensitive;
	
	Question nameSensitive;
	
	
	@Before
	public void setUp() throws Exception {
		
		ArrayList<String> answerWorkInsensitive = new ArrayList<String>();
		answerWorkInsensitive.add("yes");
		answerWorkInsensitive.add("y");
		answerWorkInsensitive.add("yes!");
		
		workInsensitive = new ResponseQuestion("Does this work?", answerWorkInsensitive, false);
		
		
		ArrayList<String> answerNameSensitive = new ArrayList<String>();
		answerNameSensitive.add("Jack");
		nameSensitive = new ResponseQuestion("The correct answer is \"Jack\"", answerNameSensitive, true);
		
		
	}

	@Test
	public void caseInsensitiveTest() {
		assertEquals(false, workInsensitive.isCorrectAnswer("no"));
		assertEquals(true, workInsensitive.isCorrectAnswer("y"));
		assertEquals(true, workInsensitive.isCorrectAnswer("yES"));
		assertEquals(true, workInsensitive.isCorrectAnswer("yEs!"));
		assertEquals(false, workInsensitive.isCorrectAnswer("nYes"));
		assertTrue("Does this work?".equals(workInsensitive.getQuestion()));
	}
	
	@Test
	public void caseSensitiveTest(){
		assertEquals(false, nameSensitive.isCorrectAnswer("jack"));
		assertEquals(true, nameSensitive.isCorrectAnswer("Jack"));
		assertEquals(false, nameSensitive.isCorrectAnswer("JAck"));
		assertTrue("The correct answer is \"Jack\"".equals(nameSensitive.getQuestion()));
	}

}
