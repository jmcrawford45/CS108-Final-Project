package messages;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setUp.AccountManager;
import tableabstraction.TableAbstraction;
import user.User;

/**
 * Servlet implementation class SendChallengeMessage
 */
@WebServlet("/SendChallengeMessage")
public class SendChallengeMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChallengeMessage() {
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
		String toUser = request.getParameter("toUser");
		//String fromUser = request.getParameter("fromUser");
		User defUser = (User)request.getSession().getAttribute("user");//correct//

		String link = request.getParameter("link");
		ChallengeMessage msg = new ChallengeMessage(defUser.getDisplayName(),toUser,link,50);
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User sendingTo = tableabstraction.TableAbstraction.getUser(toUser, con);

		
		sendingTo.addMessageToInbox(msg);
		TableAbstraction.updateUser(defUser.getDisplayName(),defUser,con);
		TableAbstraction.updateUser(toUser,sendingTo,con);
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayInbox.jsp");  
		dispatch.forward(request, response); 
		//System.out.println("" + toUser + fromUser + link);
		
	}

}
