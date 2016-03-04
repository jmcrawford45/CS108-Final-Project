package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AnswerTest {
	
	Answer stringConstructor1;
	Answer stringConstructor2;
	Answer multiAnswer1;

	@Before
	public void setUp() throws Exception {
		stringConstructor1 = new Answer("Washington");
		stringConstructor2 = new Answer("George Washington|Washington");
		
		multiAnswer1 = new Answer("1|4");
		multiAnswer1.addAnswer("2");
		multiAnswer1.addAnswer("3");
		
	}

	@Test
	public void test() {
		assertEquals(1, stringConstructor1.numAnswers());
		assertEquals(1, stringConstructor2.numAnswers());
		assertEquals(3, multiAnswer1.numAnswers());
	}
	
	@Test
	public void checkAnswerTest(){
		assertTrue(stringConstructor1.isCorrectSingle("Washington"));
		assertTrue(stringConstructor1.isCorrectSingle("wAsHiNgToN"));
		assertTrue(!stringConstructor1.isCorrectSingle("George Washington"));
		
		assertTrue(stringConstructor2.isCorrectSingle("Washington"));
		assertTrue(stringConstructor2.isCorrectSingle("wAsHiNgToN"));
		assertTrue(stringConstructor2.isCorrectSingle("George Washington"));
		assertTrue(stringConstructor2.isCorrectSingle("george washington"));
		
	}
	
	@Test public void multiAnswerTest(){
		Answer multi = new Answer("1");
		multi.addAnswer("3");
		multi.addAnswer("2");
		
		assertTrue(multiAnswer1.isCorrect(multi, false));
		assertTrue(!multiAnswer1.isCorrect(multi));
		assertTrue(!multiAnswer1.isCorrect(multi, true));
		
		multi.replaceAnswer("4", 0);
		
		assertTrue(multiAnswer1.isCorrect(multi, false));
		assertTrue(!multiAnswer1.isCorrect(multi));
		assertTrue(!multiAnswer1.isCorrect(multi, true));
		
		multi.addAnswer("1");
		assertTrue(!multiAnswer1.isCorrect(multi, false));
		assertTrue(!multiAnswer1.isCorrect(multi));
		assertTrue(!multiAnswer1.isCorrect(multi, true));
		

	}

}
