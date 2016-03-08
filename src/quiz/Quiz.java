package quiz;

import java.util.ArrayList;

import question.Answer;
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

	
	public Quiz(int id, int creator_id, int category_id, String description, boolean random, boolean one_page, boolean immediate, long time_created, String name, boolean practice) {
		
		this.id = id;
		this.name = name;
		this.creator_id = creator_id;
		this.category_id = category_id;
		this.description = description;
		this.random = random;
		this.one_page = one_page;
		this.immediate = immediate;
		this.time_created = time_created;
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
	

	
	public Performance gradeQuiz(int performance_id, int user_id, long start, ArrayList<Question> questions, ArrayList<Answer> userAnswers) {
		Performance p;
		long end = System.currentTimeMillis();
		int correct = 0;
		int total = questions.size();
		for (int i = 0; i < questions.size(); i++) {
			if(questions.get(i).isCorrectAnswer(userAnswers.get(i))) {
				correct++;
			}
		}
		long time = end - start;
		int score = (int)(((float)correct/(float)total) * 100);
		p = new Performance(performance_id, user_id, this.id, score, start, time);
		return p;
	}

}
