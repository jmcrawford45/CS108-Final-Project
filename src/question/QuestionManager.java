package question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import jdk.nashorn.internal.codegen.types.Type;
import question.FiBQuestion.InvalidFiBException;
import question.MCMAQuestion.InvalidMCMAException;
import question.MCQuestion.InvalidMCException;
import quiz.Quiz;

public class QuestionManager {

	public Connection con;
	
	public QuestionManager(Connection con) {
		this.con = con;
	}
	
	
	public Question getQuestion(int id) {
		Question result = null;
		
		try {
			String type;
			String question;
			String answerString;
			String additional;
			int quizid;
			PreparedStatement ps = con.prepareStatement("SELECT * FROM questions WHERE question_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				type = rs.getString(2);
				question = rs.getString(3);
				answerString = rs.getString(4);
				additional = rs.getString(5);
				quizid = rs.getInt(6);
				
				Answer answer = Answer.convertStringToAnswer(answerString);
				
				if(type.equals("response-question")){
					result = new ResponseQuestion(question, answer);
				} else if (type.equals("fib-question")){
					result = new FiBQuestion(question, answer);
				} else if (type.equals("maresponse-question")){
					result = new MAResponseQuestion(question, answer);
				} /*else if (type.equals("matching-question")){
					result = new MatchingQuestion
				} */
				else if (type.equals("mcma-question")){
					result = new MCMAQuestion(question, answer, additional);
				} else if(type.equals("mc-question")){
					result = new MCQuestion(question, answer, additional);
				} else if(type.equals("pic-reponse-question")){
					result = new PictureResponseQuestion(question, answer, additional);
				} else {
					System.out.println("UNACCOUNTED FOR QUESTION");
				}
				result.setQuizID(quizid);
			}
			
			
		} catch (SQLException | InvalidFiBException | InvalidMCMAException | InvalidMCException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void addQuestion(Question q) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT into questions(?, ?, ?, ?, ?, ?)");
			ps.setInt(1, q.getID());
			ps.setString(2, q.getType());
			ps.setString(3, q.getQuestion());
			ps.setString(4, q.getAnswer().convertAnswerToString());
			ps.setString(5, q.getAdditional());
			ps.setInt(6, q.getQuizID());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
