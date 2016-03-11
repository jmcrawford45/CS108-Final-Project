package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class CheckTables {

	@Test
	public void test() {
		Connection con = DBConnection.connect();
	
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM performances");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM quizzes");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM quiz_questions");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM reviews");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM questions");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
