package serializedwithsql;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableTestv2 {
	public class MyDBInfo {
		
		public static final String MYSQL_USERNAME = "ccs108jared13";
		public static final String MYSQL_PASSWORD = "2zVt6gGlm5GtBhMf";
		public static final String MYSQL_DATABASE_SERVER = "mysql-user.stanford.edu";
		public static final String MYSQL_DATABASE_NAME = "c_cs108_jared13";

	}

	public static void main(String[] args) {
		Connection con = null;
		System.out.println("Hello, World!");
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
    	try{
			Statement stmt = con.createStatement();
			stmt.executeQuery("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			stmt.executeUpdate("CREATE TABLE users(user BLOB);");
			User user = new User("jared", "1234");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(baos);
				oos.writeObject(user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    byte[] userAsBytes = baos.toByteArray();
		    PreparedStatement pstmt = con.prepareStatement("INSERT INTO users VALUES(?)");
		    ByteArrayInputStream bais = new ByteArrayInputStream(userAsBytes);
		    pstmt.setBinaryStream(1, bais, userAsBytes.length);
		    pstmt.executeUpdate();
		    pstmt.close();
		    Statement stmt2 = con.createStatement();
		    ResultSet rs = stmt2.executeQuery("SELECT * FROM users");
		    while (rs.next()) {
		      byte[] st = (byte[]) rs.getObject(1);
		      ByteArrayInputStream baip = new ByteArrayInputStream(st);
		      ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(baip);
				User user2 = (User) ois.readObject();
				System.out.println(user2.getDisplayName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		    stmt2.close();
		    rs.close();
			}catch (SQLException a) {
				a.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
