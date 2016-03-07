package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
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
		String type = request.getParameter("type");
		User defUser = (User)request.getServletContext().getAttribute("defaultUser");
		if(type.equals("image")){
			String newImage = request.getParameter("newImage");
			//System.out.println(newImage);
			defUser.setImage(newImage);
		} else if(type.equals("status")){
			String newStatus = request.getParameter("newStatus");
			//System.out.println(newStatus);
			defUser.setStatus(newStatus);
		} else if(type.equals("bio")){
			String newBio = request.getParameter("newBio");
			//System.out.println(newBio);
			defUser.setBio(newBio);
		}
		//dispatch request back to home//
	}

}
