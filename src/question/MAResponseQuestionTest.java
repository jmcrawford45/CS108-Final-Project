package question;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MAResponseQuestionTest {
	
	MAResponseQuestion unorderedQuestion;
	MAResponseQuestion orderedQuestion;

	@Before
	public void setUp() throws Exception {
		Answer answers = new Answer(false);
		String question = "Enter 'a', 'b', and 'c', but no other letter or word.";
		answers.addAnswer("a");
		answers.addAnswer("b");
		answers.addAnswer("c");
	
		unorderedQuestion = new MAResponseQuestion(question, answers);
		
		System.out.println(unorderedQuestion.returnHTMLQuestion(3));
		System.out.println();
		System.out.println(unorderedQuestion.returnHTMLSingleQuestion());
		System.out.println();
		System.out.println(MAResponseQuestion.returnHTMLBlankTemplate());

		
		String questionOrdered = "Enter 'a', 'b', and 'c' only in that order.";
		Answer answers2 = new Answer(true);
		answers2.addAnswer("a");
		answers2.addAnswer("b");
		answers2.addAnswer("c");
		orderedQuestion = new MAResponseQuestion(questionOrdered, answers2);

		
		
		
	}

	@Test
	public void unorderedTest() {
		Answer correctAnswers = new Answer();
		correctAnswers.addAnswer("a");
		correctAnswers.addAnswer("b");
		correctAnswers.addAnswer("c");
		assertTrue(unorderedQuestion.isCorrectAnswer(correctAnswers));
		
		Answer correctAnswersUnordered = new Answer();
		correctAnswersUnordered.addAnswer("c");
		correctAnswersUnordered.addAnswer("a");
		correctAnswersUnordered.addAnswer("b");
		assertTrue(unorderedQuestion.isCorrectAnswer(correctAnswersUnordered));
		
		Answer incorrectAnswersWrongNumber = new Answer();
		incorrectAnswersWrongNumber.addAnswer("b");
		incorrectAnswersWrongNumber.addAnswer("c");
		incorrectAnswersWrongNumber.addAnswer("a");
		incorrectAnswersWrongNumber.addAnswer("a");
		assertFalse(unorderedQuestion.isCorrectAnswer(incorrectAnswersWrongNumber));
		
		Answer incorrectAnswersWrongEntries = new Answer();
		incorrectAnswersWrongEntries.addAnswer("b");
		incorrectAnswersWrongEntries.addAnswer("d");
		incorrectAnswersWrongEntries.addAnswer("c");
		assertFalse(unorderedQuestion.isCorrectAnswer(incorrectAnswersWrongEntries));
	}
	
	@Test
	public void orderedTest() {
		Answer correctAnswers = new Answer();
		correctAnswers.addAnswer("a");
		correctAnswers.addAnswer("b");
		correctAnswers.addAnswer("c");
		assertTrue(orderedQuestion.isCorrectAnswer(correctAnswers));
		
		Answer correctAnswersUnordered = new Answer();
		correctAnswersUnordered.addAnswer("c");
		correctAnswersUnordered.addAnswer("a");
		correctAnswersUnordered.addAnswer("b");
		assertFalse(orderedQuestion.isCorrectAnswer(correctAnswersUnordered));
		
		Answer incorrectAnswersWrongNumber = new Answer();
		incorrectAnswersWrongNumber.addAnswer("b");
		incorrectAnswersWrongNumber.addAnswer("c");
		incorrectAnswersWrongNumber.addAnswer("a");
		incorrectAnswersWrongNumber.addAnswer("a");
		assertFalse(unorderedQuestion.isCorrectAnswer(incorrectAnswersWrongNumber));
		
		Answer incorrectAnswersWrongEntries = new Answer();
		incorrectAnswersWrongEntries.addAnswer("b");
		incorrectAnswersWrongEntries.addAnswer("d");
		incorrectAnswersWrongEntries.addAnswer("c");
		assertFalse(unorderedQuestion.isCorrectAnswer(incorrectAnswersWrongEntries));
	}

}
