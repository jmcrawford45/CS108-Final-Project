package user;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tableabstraction.TableAbstraction;

/**
 * Servlet implementation class RemoveFriend
 */
@WebServlet("/RemoveFriend")
public class RemoveFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFriend() {
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
		String toRemove = request.getParameter("toRemove"); 
		
		User defUser = TableAbstraction.getUser(request);
		if(defUser == null){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("Register.html");
			dispatch.forward(request, response);
			return;
		}

		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		User toRemoveUser = tableabstraction.TableAbstraction.getUser(toRemove, con);
		defUser.removeFriend(toRemove);
		
		
		toRemoveUser.removeFriend(defUser.getDisplayName());
		TableAbstraction.updateUser(defUser.getDisplayName(),defUser,con);
		TableAbstraction.updateUser(toRemove,toRemoveUser,con);

		RequestDispatcher dispatch = request.getRequestDispatcher("HomePage.jsp");  
		dispatch.forward(request, response);  

	}

}
