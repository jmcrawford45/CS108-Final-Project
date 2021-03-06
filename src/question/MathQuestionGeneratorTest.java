package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MathQuestionGeneratorTest {

	MathQuestionGenerator gen;
	
	@Before
	public void setUp() throws Exception {
		gen = new MathQuestionGenerator(2, 5);
		
	}

	@Test
	public void test() {
		for (int i = 0; i < 1000000; i++){
			ResponseQuestion curr = gen.generateQuestion();
			System.out.println(curr.getQuestion());
			System.out.println(curr.answersToString());
		}
	}

}
