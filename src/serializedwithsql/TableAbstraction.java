package tableabstraction;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import user.User;
public class TableAbstraction {
	
public static Stats getStats(Connection con){
	try{
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM stats;");
    while (rs.next()) {
      byte[] st = (byte[]) rs.getObject(1);
      ByteArrayInputStream baip = new ByteArrayInputStream(st);
      ObjectInputStream ois;
	try {
		ois = new ObjectInputStream(baip);
		return (Stats) ois.readObject();
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
	return new Stats(0,0, new ArrayList<String>());
}
public static void updateStats(Stats stats, Connection con){
	deleteStats(con);
	try{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(stats);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] statsAsBytes = baos.toByteArray();
	    PreparedStatement pstmt = con.prepareStatement("INSERT INTO stats VALUES(?)");
	    ByteArrayInputStream bais = new ByteArrayInputStream(statsAsBytes);
	    pstmt.setBinaryStream(1, bais, statsAsBytes.length);
	    pstmt.executeUpdate();
	    pstmt.close();
	}catch (SQLException a) {
		a.printStackTrace();
	}
	
}
public static void deleteStats(Connection con){
	Statement stmt;
	try {
		stmt = con.createStatement();    
		stmt.executeUpdate("DELETE FROM stats");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

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
		Stats stats = getStats(con);
		stats.setUsers(stats.getUsers() + 1);
		updateStats(stats, con);
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
		Stats stats = getStats(con);
		try {
			stmt = con.createStatement();    
			System.out.println("deleting: " + id);
			if(getUser(id, con) != null) {
				stats.setUsers(stats.getUsers() - 1);
				updateStats(stats, con);
			}
			stmt.executeUpdate("DELETE FROM users WHERE id = \"" + id + "\"");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}