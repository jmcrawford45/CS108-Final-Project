package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import tableabstraction.TableAbstraction;

public class CheckTables {

	@Test
	public void test() {
		Connection con = DBConnection.connect();
	
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT into "
					+ "questions(question_id, type, question, answer, additional) "
					+ "values(?, ?, ?, ?, ?)");
			int id = TableAbstraction.getID(con);
			ps.setInt(1, id);
			ps.setString(2, "response-question");
			ps.setString(3, "Question 1");
			ps.setString(4, "Answer 1");
			ps.setString(5, "");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "
					+ "questions WHERE type = ?");
			ps.setString(1, "response-question");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("question"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
