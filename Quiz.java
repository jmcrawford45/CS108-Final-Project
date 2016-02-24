package quiz;

import java.util.ArrayList;
import java.util.HashMap;

import question.Question;

public class Quiz {


	
	
	public int id;
	public int creator_id;
	public int category_id;
	public String name;
	public String description;
	public boolean random;
	public boolean one_page;
	public boolean immediate;
	public ArrayList<Question> questions;
	public ArrayList<Performance> scoreboard;
	public long time_created;
	public boolean practice;

	//constructor for quizzes already in database
	
	public Quiz(int id, int creator_id, int category_id, String name, String description, boolean random, boolean one_page, boolean immediate, long time_created, ArrayList<Question> questions, ArrayList<Performance> scores, boolean practice) {
	
		this.id = id;
		this.name = name;
		this.creator_id = creator_id;
		this.category_id = category_id;
		this.description = description;
		this.random = random;
		this.one_page = one_page;
		this.immediate = immediate;
		this.time_created = time_created;
		this.questions = questions;
		this.scoreboard = scores;
		this.practice = practice;
		
	}
	
	
	// constructor for new quiz
	
	public Quiz(int id, int creator_id, int category_id, String name, String description, boolean random, boolean one_page, boolean immediate, ArrayList<Question> questions, boolean practice) {
		
		this.id = id;
		this.name = name;
		this.creator_id = creator_id;
		this.category_id = category_id;
		this.description = description;
		this.random = random;
		this.one_page = one_page;
		this.immediate = immediate;
		this.questions = questions;
		this.scoreboard = new ArrayList<Performance>();
		this.practice = practice;
		
	}
	
	public void setTime(long time) {
		this.time_created = time;
	}
	
	public void setQuestions(ArrayList<Question> qs) {
		this.questions = qs;
	}

	
	public void setScores(ArrayList<Performance> ps) {
		this.scoreboard = ps;
	}
	
	
	public void addScore(Performance p) {
		this.scoreboard.add(p);
	}
	

	
	public Performance gradeQuiz(int user_id, long start, ArrayList<Question> questions, HashMap<Question, String> userAnswers) {
		Performance p;
		long end = System.currentTimeMillis();
		int correct = 0;
		int total = questions.size();
		for (Question q: questions) {
			if (q.isCorrectAnswer(userAnswers.get(q))) {
				correct++;
			} 
		}
		long time = end - start;
		int score = (int)(((float)correct/(float)total) * 100);
		p = new Performance(10, user_id, this.id, score, start, time);
		return p;
	}

}
