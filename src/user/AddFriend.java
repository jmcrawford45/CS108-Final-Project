package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import setUp.AccountManager;

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
		//doGet(request, response);
		
		String toAdd = request.getParameter("toAdd"); 
		//System.out.println(toAdd + "Being added as friend");
		AccountManager accounts = (AccountManager)request.getServletContext().getAttribute("manager");
		User defUser = (User)request.getServletContext().getAttribute("defaultUser");
//		System.out.print(defUser.getDisplayName());
//		System.out.print("Adding" + toAdd);
		User toAddUser = accounts.getAccount(toAdd);
		defUser.addFriend(toAdd);
		toAddUser.addFriend(defUser.getDisplayName());
		RequestDispatcher dispatch = request.getRequestDispatcher("DisplayUser.jsp?user="+defUser.getDisplayName());  
		dispatch.forward(request, response);  

	}

}
