package serializedwithsql;

import java.io.*;
import java.sql.*;

public class TableAbstraction {

public static User getUser(String id, Connection con){
		System.out.println("Getting: " + id);
		try{
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT user FROM users WHERE id = '" + id +"';");
	    while (rs.next()) {
	      byte[] st = (byte[]) rs.getObject(1);
	      ByteArrayInputStream baip = new ByteArrayInputStream(st);
	      ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(baip);
			User user = (User) ois.readObject();
			return user;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
				e.printStackTrace();
			}
			byte[] userAsBytes = baos.toByteArray();
			System.out.println("inserting: " + id);

		    PreparedStatement pstmt = con.prepareStatement("INSERT INTO users VALUES(?,?)");
		    ByteArrayInputStream bais = new ByteArrayInputStream(userAsBytes);
		    pstmt.setString(1, id);
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
			System.out.println("deleting: " + id);

			stmt.executeUpdate("DELETE FROM users WHERE id = \"" + id + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
