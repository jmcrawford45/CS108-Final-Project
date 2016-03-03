package listeners;

import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import listeners.MyDBInfo;


/**
 * Application Lifecycle Listener implementation class ContextListener.
 * Handles the connection opening and closing.
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     * Opens connection to database
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	Connection con = null;
    	try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + 
            MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME , MyDBInfo.MYSQL_PASSWORD);
            } catch (SQLException e) {
            e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        ServletContext context = arg0.getServletContext();
        context.setAttribute("connection", con);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     * closes database connection
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        ServletContext context = arg0.getServletContext();
        Connection con = (Connection)context.getAttribute("connection");
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
	
}
