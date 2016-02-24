package quiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import com.mysql.jdbc.Connection;

import question.Question;

public class QuizManager {
	
	public Connection con;
	public static final int MAX_ENTRIES = 5;
	
	public QuizManager(Connection con) {
		this.con = con;
	}
	
	
	public Quiz getQuiz(int id) {
		Quiz q = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE quiz_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String name = rs.getString(4);
				String description = rs.getString(5);
				boolean random = rs.getBoolean(6);
				boolean one_page = rs.getBoolean(7);
				boolean immediate = rs.getBoolean(8);
				long time_created = rs.getLong(9);
				boolean practice = rs.getBoolean(10);
				q = new Quiz(id, creator_id, category_id, name, description, random, one_page, immediate, time_created, getQuestions(id), getPerformances(id), practice);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return q;
	}
	
	public void addQuiz(Quiz q) {
		try {
			long time = System.currentTimeMillis();
			q.setTime(time);
			PreparedStatement ps = con.prepareStatement("INSERT into quizzes(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, q.id);
			ps.setInt(2, q.creator_id);
			ps.setInt(3, q.category_id);
			ps.setString(4, q.name);
			ps.setString(5, q.description);
			ps.setBoolean(6, q.random);
			ps.setBoolean(7, q.one_page);
			ps.setBoolean(8, q.immediate);
			ps.setLong(9, time);
			ps.setBoolean(10, q.practice);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void addPerformance(Performance p) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT into scores(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, p.id);
			ps.setInt(2, p.user_id);
			ps.setInt(3, p.quiz_id);
			ps.setInt(4, p.score);
			ps.setLong(5, p.start);
			ps.setLong(6, p.time);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Quiz> getQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int quiz_id = rs.getInt(1);
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String name = rs.getString(4);
				String description = rs.getString(5);
				boolean random = rs.getBoolean(6);
				boolean one_page = rs.getBoolean(7);
				boolean immediate = rs.getBoolean(8);
				long time_created = rs.getLong(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, name, description, random, one_page, immediate, time_created, getQuestions(quiz_id), getPerformances(quiz_id), practice));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quizzes;
	}
	
	public ArrayList<Quiz> getCategoryQuizzes(int category_id) {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE category_id = ?");
			ps.setInt(1, category_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int quiz_id = rs.getInt(1);
				int creator_id = rs.getInt(2);
				String name = rs.getString(4);
				String description = rs.getString(5);
				boolean random = rs.getBoolean(6);
				boolean one_page = rs.getBoolean(7);
				boolean immediate = rs.getBoolean(8);
				long time_created = rs.getLong(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, name, description, random, one_page, immediate, time_created, getQuestions(quiz_id), getPerformances(quiz_id), practice));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quizzes;
	}
	
	public ArrayList<Quiz> getCreatorQuizzes(int creator_id) {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ?");
			ps.setInt(1, creator_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int quiz_id = rs.getInt(1);
				int category_id = rs.getInt(3);
				String name = rs.getString(4);
				String description = rs.getString(5);
				boolean random = rs.getBoolean(6);
				boolean one_page = rs.getBoolean(7);
				boolean immediate = rs.getBoolean(8);
				long time_created = rs.getLong(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, name, description, random, one_page, immediate, time_created, getQuestions(quiz_id), getPerformances(quiz_id), practice));
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quizzes;
	}
	
	
	public ArrayList<Quiz> getRecentCreatorQuizzes(int creator_id) {
		ArrayList<Quiz> quizzes = getCreatorQuizzes(creator_id);
		Collections.sort(quizzes, new Comparator<Quiz>(){
		     public int compare(Quiz q1, Quiz q2){
		         return (int)(q1.time_created - q2.time_created);
		     }
		});
		return (ArrayList<Quiz>)quizzes.subList(0, MAX_ENTRIES);
	}
	
	
	
	public ArrayList<Quiz> getRecentQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE time_created > ?");
			ps.setLong(1, recent);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int quiz_id = rs.getInt(1);
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String name = rs.getString(4);
				String description = rs.getString(5);
				boolean random = rs.getBoolean(6);
				boolean one_page = rs.getBoolean(7);
				boolean immediate = rs.getBoolean(8);
				long time_created = rs.getLong(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, name, description, random, one_page, immediate, time_created, getQuestions(quiz_id), getPerformances(quiz_id), practice));
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
	
	public ArrayList<Performance> getPerformances(int quiz_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM scores WHERE quiz_id = ?");
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int p_id = rs.getInt(1);
				int user_id = rs.getInt(2);
				int score = rs.getInt(4);
				long start = rs.getLong(5);
				long time = rs.getLong(6);
				Performance p = new Performance(p_id, user_id, quiz_id, score, start, time);
				performances.add(p);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return performances;
	}
	
	public ArrayList<Question> getQuestions(int quiz_id) {
		ArrayList<Question> qs = new ArrayList<Question>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM questions WHERE quiz_id = ?");
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String a = rs.getString("answers");
				String[] b = a.split(" ");
				ArrayList<String> answers = (ArrayList<String>) Arrays.asList(b);
				Question q = new Question(name, answers);
				qs.add(q);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qs;
	}
	
	
	public ArrayList<Performance> getUserPerformance(int user_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM scores WHERE user_id = ?");
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int p_id = rs.getInt(1);
				int quiz_id = rs.getInt(3);
				int score = rs.getInt(4);
				long start = rs.getLong(5);
				long time = rs.getLong(6);
				Performance p = new Performance(p_id, user_id, quiz_id, score, start, time);
				performances.add(p);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return performances;
	}
	
	public ArrayList<Performance> getRecentUserPerformance(int user_id) {
		ArrayList<Performance> results = getUserPerformance(user_id);
		Collections.sort(results, new Comparator<Performance>(){
		     public int compare(Performance p1, Performance p2){
		         return (int)(p1.start - p2.start);
		     }
		});
		return (ArrayList<Performance>)results.subList(0, MAX_ENTRIES);
	}
	
	
	public ArrayList<Performance> getUserQuizPerformance(int quiz_id, int user_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM scores WHERE quiz_id = ? AND user_id = ?");
			ps.setInt(1, quiz_id);
			ps.setInt(2, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int p_id = rs.getInt(1);
				int score = rs.getInt(4);
				long start = rs.getLong(5);
				long time = rs.getLong(6);
				Performance p = new Performance(p_id, user_id, quiz_id, score, start, time);
				performances.add(p);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(performances);
		return (ArrayList<Performance>)performances.subList(0, MAX_ENTRIES);
	}
	
	public ArrayList<Performance> getLeaderBoard(int quiz_id) {
		ArrayList<Performance> leaders = getPerformances(quiz_id);
		Collections.sort(leaders);
		return (ArrayList<Performance>)leaders.subList(0, MAX_ENTRIES);
	}
	
	
	
	public ArrayList<Performance> getRecentLeaderBoard(int quiz_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM scores WHERE quiz_id = ? AND time_created > ?");
			ps.setInt(1, quiz_id);
			ps.setLong(2, recent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int p_id = rs.getInt(1);
				int user_id = rs.getInt(2);
				int score = rs.getInt(4);
				long start = rs.getLong(5);
				long time = rs.getLong(6);
				Performance p = new Performance(p_id, user_id, quiz_id, score, start, time);
				performances.add(p);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(performances);
		return (ArrayList<Performance>)performances.subList(0, MAX_ENTRIES);
	}
	

}
