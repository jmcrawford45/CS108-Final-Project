package messages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DistinguishMessages
 */
@WebServlet("/DistinguishMessages")
public class DistinguishMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DistinguishMessages() {
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
		//<a href="displayMessage.jsp?body=<%=msg.getBody()%>&from=<%=msg.getFrom()%>"> <%= msg.getFrom()%></a><br></li> --%>

		String type = request.getParameter("type");
		String from = request.getParameter("from");
		String string = request.getParameter("string");
		String link = request.getParameter ("link");
		//System.out.println(type);
		if(type.equals("TextMessage")){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("displayMessage.jsp?body="+string+"&from="+from); 
			dispatch.forward(request, response);
		} else if(type.equals("FriendRequest")){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher("DisplayFriendRequest.jsp?body="+string+"&from="+from);
			dispatch.forward(request, response);
		} else if(type.equals("ChallengeMessage")){
			RequestDispatcher dispatch = 
					request.getRequestDispatcher
					("DisplayChallengeMessage.jsp?body="+string+"&from="+from+"&link="+link);
			dispatch.forward(request, response);
		}
		//RequestDispatcher dispatch = request.getRequestDispatcher("DisplayUser.jsp?user="+defUser.getDisplayName());  
		//dispatch.forward(request, response); 
	}

}
