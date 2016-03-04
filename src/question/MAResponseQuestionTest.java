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
		ArrayList<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
		String question = "Enter 'a', 'b', and 'c', but no other letter or word.";
		answers.add(new ArrayList<String>());
		answers.add(new ArrayList<String>());
		answers.add(new ArrayList<String>());
		answers.get(0).add("a");
		answers.get(1).add("b");
		answers.get(2).add("c");
		unorderedQuestion = new MAResponseQuestion(question, answers, false, false);
		System.out.println(unorderedQuestion.numAnswers());

		ArrayList<ArrayList<String>> answers2 = new ArrayList<ArrayList<String>>(3);
		String questionOrdered = "Enter 'a', 'b', and 'c' in that order.";
		answers2.add(new ArrayList<String>());
		answers2.add(new ArrayList<String>());
		answers2.add(new ArrayList<String>());
		answers2.get(0).add("a");
		answers2.get(1).add("b");
		answers2.get(2).add("c");
		orderedQuestion = new MAResponseQuestion(questionOrdered, answers2, false, true);
		System.out.println(orderedQuestion.numAnswers());

		
		
		
	}

	@Test
	public void unorderedTest() {
		ArrayList<String> correctAnswers = new ArrayList<String>();
		correctAnswers.add("a");
		correctAnswers.add("b");
		correctAnswers.add("c");
		assertTrue(unorderedQuestion.isCorrectAnswer(correctAnswers));
		
		ArrayList<String> correctAnswersUnordered = new ArrayList<String>();
		correctAnswersUnordered.add("c");
		correctAnswersUnordered.add("a");
		correctAnswersUnordered.add("b");
		assertTrue(unorderedQuestion.isCorrectAnswer(correctAnswersUnordered));
		
		ArrayList<String> incorrectAnswersWrongNumber = new ArrayList<String>();
		incorrectAnswersWrongNumber.add("b");
		incorrectAnswersWrongNumber.add("c");
		incorrectAnswersWrongNumber.add("a");
		incorrectAnswersWrongNumber.add("a");
		assertTrue(!unorderedQuestion.isCorrectAnswer(incorrectAnswersWrongNumber));
		
		ArrayList<String> incorrectAnswersWrongEntries = new ArrayList<String>();
		incorrectAnswersWrongEntries.add("b");
		incorrectAnswersWrongEntries.add("d");
		incorrectAnswersWrongEntries.add("c");
		assertTrue(!unorderedQuestion.isCorrectAnswer(incorrectAnswersWrongEntries));
	}

}
