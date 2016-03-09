package listeners;

import java.sql.*;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//import initializer.MyDBInfo;



/**
 * Application Lifecycle Listener implementation class ContextListener.
 * Handles the connection opening and closing.
 *
 */
@WebListener
public class Initialize implements ServletContextListener {
	

	public static final String MYSQL_USERNAME = "ccs108jared13";
	public static final String MYSQL_PASSWORD = "2zVt6gGlm5GtBhMf";
	public static final String MYSQL_DATABASE_SERVER = "mysql-user.stanford.edu";
	public static final String MYSQL_DATABASE_NAME = "c_cs108_jared13";

    /**
     * Default constructor. 
     */
    public Initialize() {
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
            MYSQL_DATABASE_SERVER, MYSQL_USERNAME , MYSQL_PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + MYSQL_DATABASE_NAME);
            stmt.executeUpdate("DROP TABLE users;");
            stmt.executeUpdate("CREATE TABLE users(id varchar(255), user BLOB);");
    		} catch (SQLException e) {
            e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        ServletContext context = arg0.getServletContext();
        context.setAttribute("connection", con);
        context.setAttribute("rand", new Random());
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
