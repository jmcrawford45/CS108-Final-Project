package quiz;


import java.sql.Connection;

import org.junit.Test;

public class FeedbackTest {

	@Test
	public void test() {

		
			Connection con = DBConnection.connect();
			QuizManager qm = new QuizManager(con);
		
			qm.addFeedback(567, 1111, 2222, 3, "great", 0);
			Feedback f = qm.getFeedbackbyID(567);
			if (f != null) System.out.println("wooo");

		






	}

}
