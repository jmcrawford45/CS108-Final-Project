package quiz;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.Answer;
import question.Question;

/**
 * Servlet implementation class MultiPageServlet
 */
@WebServlet("/MultiPageServlet")
public class MultiPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiPageServlet() {
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
		Quiz q = (Quiz)request.getSession().getAttribute("quiztaken");	
		int i = Integer.parseInt(request.getParameter("index"));
		String input = request.getParameter("input"+i);
		Answer a = new Answer(input);
		ArrayList<Answer> ans = (ArrayList<Answer>)request.getSession().getAttribute("answers");
		ans.add(new Answer(input));
		request.getSession().setAttribute("answers", ans);
		Question qs = q.getQuestionbyIndex(i);
		if (i >= (q.getNumQuestions() - 1)) {
			
			RequestDispatcher rd = request.getRequestDispatcher("GradeQuiz");
			rd.forward(request, response);
		} else if (q.immediate) {
			String c = "";
			if (qs.isCorrectAnswer(a)) {
				c = "CORRECT!";
			}	else c = "Incorrect";
			
			String s = "The Question was " + qs.getQuestion() + "\n";
			String sa = "You answered " + input + "\n";
			String sc = "Correct answer was " + qs.answersToString() + "\n";
			response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    out.println("<HTML>");
		    out.println("<HEAD><TITLE> Question "+i+" Feedback</TITLE></HEAD>");
		    out.println("<BODY>");
		    out.println("<BIG>Your answer is "+ c +"</BIG><br>");
		    out.println("<BIG>" + s  + "<br>" +sa + "<br>"+ sc + "</BIG><br>");
		    
		    String next = "TakeQuizMultiPage.jsp?index=" + (i+1);
		    out.println("<A HREf="+next+">Continue Quiz </A>");
		    out.println("</BODY></HTML>");
		  
			
		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("TakeQuizMultiPage.jsp?index=" + (i+1));
			rd.forward(request, response);
		}
		
		
		
	}

}
