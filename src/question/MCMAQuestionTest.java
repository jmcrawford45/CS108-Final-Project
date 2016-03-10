package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import question.MCMAQuestion.InvalidMCMAException;

public class MCMAQuestionTest {
	
	MCMAQuestion singleAnswerQuestion;
	MCMAQuestion multiAnswerQuestion;

	@Before
	public void setUp() throws Exception {
		singleAnswerQuestion = new MCMAQuestion("Only option c is correct.", "c", "a|b|c|d");
		
		System.out.println(singleAnswerQuestion.returnHTMLQuestion(2));
		System.out.println();
		
		System.out.println(singleAnswerQuestion.returnHTMLSingleQuestion());
		System.out.println();
		
		System.out.println(MCMAQuestion.returnHTMLBlankTemplate());
		
		//Answer multiAnswer = new Answer();
		//multiAnswer.addAnswer("c");
		//multiAnswer.addAnswer("a");
		
		Answer multiAnswer = Answer.convertStringToAnswer("c||a");
		
		multiAnswerQuestion = new MCMAQuestion("Options a and c are correct.", multiAnswer, "a|b|c|d");
	}

	@Test
	public void test() {
		Answer singleAnswerInput = new Answer();
		singleAnswerInput.addAnswer("c");
		Answer singleAnswerIncorrectInput1 = new Answer();
		singleAnswerIncorrectInput1.addAnswer("b");
		Answer multiIncorrectInput = new Answer();
		multiIncorrectInput.addAnswer("c");
		multiIncorrectInput.addAnswer("a");
		
		assertTrue(singleAnswerQuestion.isCorrectAnswer(singleAnswerInput));
		assertFalse(singleAnswerQuestion.isCorrectAnswer(singleAnswerIncorrectInput1));
		assertFalse(singleAnswerQuestion.isCorrectAnswer(multiIncorrectInput));

		assertFalse(multiAnswerQuestion.isCorrectAnswer(singleAnswerInput));
		assertFalse(multiAnswerQuestion.isCorrectAnswer(singleAnswerIncorrectInput1));
		assertTrue(multiAnswerQuestion.isCorrectAnswer(multiIncorrectInput));
		
		Answer multiCorrectInputUnordered = new Answer();
		multiCorrectInputUnordered.addAnswer("a");
		multiCorrectInputUnordered.addAnswer("c");
		
		assertTrue(multiAnswerQuestion.isCorrectAnswer(multiCorrectInputUnordered));
		
		multiCorrectInputUnordered.addAnswer("d");
		assertFalse(multiAnswerQuestion.isCorrectAnswer(multiCorrectInputUnordered));
		
	}
	
	@Test
	public void testFailure1(){
		try {
			MCMAQuestion fail = new MCMAQuestion("This one contains incorrect answer.", "e", "a|b|c|d");
			fail();
		} catch (InvalidMCMAException e) {
			//succeeded
		}
	}
	
	@Test
	public void testFailure2(){
		try {
			Answer invalidAnswers = new Answer();
			invalidAnswers.addAnswer("a");
			invalidAnswers.addAnswer("e");
			MCMAQuestion fail = new MCMAQuestion("This one contains single incorrect answer", invalidAnswers, "a|b|c|d");
			fail();
		} catch (InvalidMCMAException e) {
			//succeeded
		}
	}
	
	@Test
	public void testFailure3(){
		try {
			Answer invalidAnswers = new Answer();
			invalidAnswers.addAnswer("a");
			invalidAnswers.addAnswer("b");
			invalidAnswers.addAnswer("c");
			invalidAnswers.addAnswer("d");
			invalidAnswers.addAnswer("a");
			MCMAQuestion fail = new MCMAQuestion("This one contains all correct answers, but too many.", invalidAnswers, "a|b|c|d");
			fail();
		} catch (InvalidMCMAException e) {
			//succeeded
		}
	}
	
	@Test
	public void toStringMethods(){
		System.out.println(singleAnswerQuestion.getAnswer().convertAnswerToString());
		System.out.println(singleAnswerQuestion.getAdditional());
		System.out.println();
		System.out.println(multiAnswerQuestion.getAnswer().convertAnswerToString());
		System.out.println(multiAnswerQuestion.getAdditional());
	}

}
