package messages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import setUp.AccountManager;
import user.User;

/**
 * Servlet implementation class SendFriendRequest
 */
@WebServlet("/SendFriendRequest")
public class SendFriendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendFriendRequest() {
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
		System.out.print("Request Received");
		String toAdd = request.getParameter("toAdd"); 
		User defUser = (User)request.getServletContext().getAttribute("defaultUser");
		AccountManager manager = (AccountManager)request.getServletContext().getAttribute("manager");
		User sendingTo = manager.getAccount(toAdd);
		FriendRequest friendRequest = new FriendRequest(defUser.getDisplayName(),sendingTo.getDisplayName());
		sendingTo.addMessageToInbox(friendRequest);
		System.out.print(defUser.getDisplayName());
		System.out.print("Adding" + toAdd);
		

//		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayUser.jsp?user="+defUser.getDisplayName());  
//		dispatch.forward(request, response);  
	}

}
