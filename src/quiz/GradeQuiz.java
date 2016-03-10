package quiz;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.Answer;

/**
 * Servlet implementation class GradeQuiz
 */
@WebServlet("/GradeQuiz")
public class GradeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeQuiz() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizManager qm = new QuizManager(DBConnection.connect());
		String qid = request.getParameter("quizid");
		Quiz q = (Quiz)request.getSession().getAttribute(qid);
		//Quiz q = qm.getQuizByID(quiz_id);
		ArrayList<Answer> input = new ArrayList<Answer>();
		for (int i = 0; i < Test.questions.size(); i++) {
			Answer a = new Answer(request.getParameter("answer"+i));
			input.add(a);
		}
		long start = Long.parseLong(request.getParameter("start"));
		
		Performance p = q.gradeQuiz(2323, 1212, start, Test.questions, input);
		request.setAttribute("performance", p);
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizResults.jsp?quiz="+q.name); 
		dispatch.forward(request, response);
	}

}
