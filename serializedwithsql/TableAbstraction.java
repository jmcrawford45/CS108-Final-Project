package serializedwithsql;

import java.io.*;
import java.sql.*;

public class TableAbstraction {

	public static User getUser(String id, Connection con){
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM users");
	    while (rs.next()) {
	      byte[] st = (byte[]) rs.getObject(1);
	      ByteArrayInputStream baip = new ByteArrayInputStream(st);
	      ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(baip);
			User user = (User) ois.readObject();
			return user;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    rs.close();
		}catch (SQLException a) {
			a.printStackTrace();
		}
		return null;
	}
	public static void updateUser(String id, User user, Connection con){
		deleteUser(id, con);
		try{
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
		    PreparedStatement pstmt = con.prepareStatement("INSERT INTO users VALUES(?,?)");
		    ByteArrayInputStream bais = new ByteArrayInputStream(userAsBytes);
		    pstmt.setString(0, id);
		    pstmt.setBinaryStream(2, bais, userAsBytes.length);
		    pstmt.executeUpdate();
		    pstmt.close();
		}catch (SQLException a) {
			a.printStackTrace();
		}
	}
	public static void deleteUser(String id, Connection con){
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE * from users where id = '" + id + "';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
