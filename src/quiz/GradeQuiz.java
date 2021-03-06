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
import tableabstraction.TableAbstraction;

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
		QuizManager qm = (QuizManager)request.getServletContext().getAttribute("quizmanager");
		Quiz q = (Quiz)request.getSession().getAttribute("quiztaken");

		
		ArrayList<Answer> input; 
		if (q.one_page) {
			input = new ArrayList<Answer>();
			for (int i = 0; i < q.getNumQuestions(); i++) {
				Answer a = new Answer(request.getParameter("input"+i));
				input.add(a);
			}
		} else {
			input = (ArrayList<Answer>)request.getSession().getAttribute("answers");
		}
		long start = Long.parseLong(request.getSession().getAttribute("start").toString());
		int pid = TableAbstraction.getID(qm.con);
		int userid = Integer.parseInt(request.getServletContext().getInitParameter("userid"));
		Performance p = q.gradeQuiz(pid, userid, start, q.questions, input);
		request.setAttribute("performance", p);
		qm.addPerformance(p);
		request.getSession().removeAttribute("quiztaken");
		request.getSession().removeAttribute("start");
		request.getSession().removeAttribute("answers");
		RequestDispatcher dispatch = request.getRequestDispatcher("QuizResults.jsp?userid="+userid); 
		dispatch.forward(request, response);
	}

}
