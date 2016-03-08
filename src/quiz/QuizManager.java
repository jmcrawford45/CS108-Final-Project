package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class QuizManager {
	public Connection con;
	
	public QuizManager(Connection con) {
		this.con = con;
	}
	
	public void addQuiz(int quiz_id, int creator_id, int category_id, String description, boolean random, boolean one_page, boolean immediate, String name, boolean practice) {
		try {
			long time = System.currentTimeMillis();
			PreparedStatement ps = con.prepareStatement("INSERT into quizzes(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, quiz_id);
			ps.setInt(2, creator_id);
			ps.setInt(3, category_id);
			ps.setString(4, description);
			ps.setBoolean(5, random);
			ps.setBoolean(6, one_page);
			ps.setBoolean(7, immediate);
			ps.setLong(8, time);
			ps.setString(9, name);
			ps.setBoolean(10, practice);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Quiz getQuizByID(int quiz_id) {
		Quiz q = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE quiz_id = ?");
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				q = new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return q;
	}
	
	
	public ArrayList<Quiz> getQuizzesByCreator(int creator_id) {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ?");
			ps.setInt(1, creator_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt(1);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quizzes;
	}
	
	
	//gets quizzes from the last 24 hours
	public ArrayList<Quiz> getRecentQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE time_created > ?");
			ps.setLong(1, recent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt(1);
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(quizzes, new Comparator<Quiz>(){
		     public int compare(Quiz q1, Quiz q2){
		         return (int)(q1.time_created - q2.time_created);
		     }
		});
		
		return quizzes;
	}
	
}
