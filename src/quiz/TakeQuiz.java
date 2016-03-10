package quiz;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TakeQuiz
 */
@WebServlet("/TakeQuiz")
public class TakeQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TakeQuiz() {
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
		//QuizManager qm = (QuizManager)request.getServletContext().getAttribute("quizmanager");
		
		QuizManager qm = new QuizManager(DBConnection.connect());
		int quiz_id = Integer.parseInt(request.getParameter("quizid"));
		Quiz q = qm.getQuizByID(quiz_id);
		request.getSession().setAttribute(Integer.toString(quiz_id), q);
		RequestDispatcher dispatch = null;
		if (q.one_page) {
			dispatch = request.getRequestDispatcher("TakeQuizOnePage.jsp?quiz="+q.name);  
			 
		} else {
			
		}
		long start = System.currentTimeMillis();
		request.setAttribute("start", start);
		request.setAttribute("userid", request.getParameter("userid"));
		request.setAttribute("quiz", q);
		dispatch.forward(request, response);
		
	}

}
