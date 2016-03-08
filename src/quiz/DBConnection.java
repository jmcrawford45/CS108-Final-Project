package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import listeners.MyDBInfo;

public class DBConnection {

	public static Connection connect() {
		Connection con = null;
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + 
            MyDBInfo.MYSQL_DATABASE_SERVER, MyDBInfo.MYSQL_USERNAME , MyDBInfo.MYSQL_PASSWORD);
            Statement stmt = con.createStatement();
            stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
    		} catch (SQLException e) {
            e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
		return con;
	}
	
}
