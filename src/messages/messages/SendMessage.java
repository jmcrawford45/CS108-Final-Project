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
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
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
		String to = request.getParameter("toUser");
		String from = request.getParameter("fromUser");
		String message = request.getParameter("message");
		User defUser = (User)request.getSession().getAttribute("user");//correct//
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User toUser = tableabstraction.TableAbstraction.getUser(to, con);
		System.out.println(toUser.getDisplayName());

		TextMessage msg = new TextMessage(from,to,message);
		toUser.addMessageToInbox(msg);
		TableAbstraction.updateUser(to,toUser,con);  
		
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayInbox.jsp");  
		dispatch.forward(request, response); 
 
		
	}

}
