package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static Connection connect() {
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + 
            listeners.ContextListener.MYSQL_DATABASE_SERVER, 
            listeners.ContextListener.MYSQL_USERNAME , 
            listeners.ContextListener.MYSQL_PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + listeners.ContextListener.MYSQL_DATABASE_NAME);
    		} catch (SQLException e) {
            e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
		return con;
	}
	
}
