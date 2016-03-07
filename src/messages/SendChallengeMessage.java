package messages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setUp.AccountManager;
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
		String fromUser = request.getParameter("fromUser");
		String link = request.getParameter("link");
		ChallengeMessage msg = new ChallengeMessage(fromUser,toUser,link,50);
		
		//User defUser = (User)request.getServletContext().getAttribute("defaultUser");
		AccountManager manager = (AccountManager)request.getServletContext().getAttribute("manager");
		User sendingTo = manager.getAccount(toUser);
		sendingTo.addMessageToInbox(msg);
		
		System.out.println("" + toUser + fromUser + link);
		
	}

}
