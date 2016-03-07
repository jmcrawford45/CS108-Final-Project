package loginServlets;

import java.io.IOException;
import table.TableAbstraction;
import java.sql.*;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import listeners.Security;
import main.User;

/**
 * Servlet implementation class AccountServlet
 * handles account creation requests
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		Connection con = (Connection)request.getSession().getServletContext().getAttribute("connection");
		Random rand = (Random)request.getSession().getServletContext().getAttribute("rand");
		User user = TableAbstraction.getUser(name,con);
		if(user != null){
            request.getRequestDispatcher("InUse.jsp").forward(request, response);
        } else{
        	String salt = Security.getSalt(rand);
        	String hashPassword = Security.getHashed(password, salt);
        	TableAbstraction.updateUser(name, new User(name, hashPassword, salt), con);
        	request.getRequestDispatcher("Welcome.jsp").forward(request, response);
        }
	}

}
