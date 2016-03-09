package user;

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

/**
 * Servlet implementation class AddFriend
 */
@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriend() {
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
		int index = Integer.parseInt(request.getParameter("index"));		
		String toAdd = request.getParameter("toAdd"); 
		
		User defUser = (User)request.getSession().getAttribute("user");//correct//
		defUser.deleteMessage(index);
		

		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User toAddUser = tableabstraction.TableAbstraction.getUser(toAdd, con);
		defUser.addFriend(toAdd);
		toAddUser.addFriend(defUser.getDisplayName());
		TableAbstraction.updateUser(defUser.getDisplayName(),defUser,con);
		TableAbstraction.updateUser(toAdd,toAddUser,con);

		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayUser.jsp?user="+toAddUser.getDisplayName());  
		dispatch.forward(request, response);  

	}

}
