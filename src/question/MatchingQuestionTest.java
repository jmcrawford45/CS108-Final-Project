package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatchingQuestionTest {

	MatchingQuestion question;
	
	@Before
	public void setUp() throws Exception {
		question = new MatchingQuestion("Match the pairs correctly.");
		
		question.addPair("1 + 2", "3");
		question.addPair("4 + 2", "6");
		question.addPair("4 - 3", "1");
		question.addPair("6 x 2", "12");
		question.addPair("16 - 8", "8");
		
		System.out.println(question.returnHTMLQuestion(2));
		
		System.out.println();
		System.out.println(MatchingQuestion.returnHTMLBlankTemplate(7));
		
		
		
		
	}

	@Test
	public void test() {
		assertEquals(5, question.numPairs());
		assertEquals(5, question.numAnswers());
	}

}
