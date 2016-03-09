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
		String to = request.getParameter("toAdd"); 
		User defUser = (User)request.getSession().getAttribute("user");//correct//
		if(to.equals(defUser.getDisplayName())){
			RequestDispatcher dispatch = request.getRequestDispatcher("DisplaySelf.jsp");  
			dispatch.forward(request, response);  
			return;
		}
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");

		User sendingTo = tableabstraction.TableAbstraction.getUser(to, con);

		FriendRequest friendRequest = new FriendRequest(defUser.getDisplayName(),sendingTo.getDisplayName());
		sendingTo.addMessageToInbox(friendRequest);

		TableAbstraction.updateUser(to,sendingTo,con);
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayInbox.jsp");  
		dispatch.forward(request, response);   

 
	}

}
