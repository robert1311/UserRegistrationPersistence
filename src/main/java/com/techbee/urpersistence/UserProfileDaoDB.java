package com.techbee.urpersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserProfileDaoDB {

	Connection conn;
	
	public void makeConnection() {
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://userdb.czqmlpibq7qv.us-east-2.rds.amazonaws.com:3306/userdb";
	    final String USER = "admin";
	    final String PASS = "Mavs11ar";
	    
	    try {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	System.out.println("Connecting to database...");
	     
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
		      System.out.println("Creating table in given database...");
		      Statement stmt = conn.createStatement();
//		      String sql = "INSERT INTO `OurTable` (`AccountID`,`firstName`,`lastName`,`age`) VALUES ( 3,'Rayan','B',24);";

		      String sql = "CREATE TABLE IF NOT EXISTS User " + 
		                   "(userId INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
		                   " firstName VARCHAR(255), " + 
		                   " lastName VARCHAR(255), " + 
		                   " email VARCHAR(50),"
		                   + "password VARCHAR(20));"; 		     		      

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(UserProfile user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();
		try {
			Statement stmt = conn.createStatement();
			
			String sql = "INSERT INTO User (`firstName`, `lastName`, `email`, "
					+ "`password`) VALUES ('" + firstName + "', '" + lastName + "', '"
					+ email + "', '" + password + "');";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public UserProfile getUser(int userId) {
		
		UserProfile user = new UserProfile();
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM User WHERE userId = " + userId + ";";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			user.setUserProfileId(rs.getInt("userId"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setEmail(rs.getString("email"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public List<UserProfile> getAllUsers(){
		List<UserProfile> users = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM User";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserProfile user = new UserProfile();
				user.setUserProfileId(rs.getInt("userId"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public void updateUser(UserProfile user) {
		try {
			Statement stmt = conn.createStatement();
			int userId = user.getUserProfileId();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String email = user.getEmail();
			String password = user.getPassword();
			
			String sql = "UPDATE `User` SET firstName = '" + firstName +
					"', lastName = '" + lastName + "', email = '" + email + 
					"', password = '" + password + "' WHERE userId = " + userId + ";";
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteUser(int userId) {
		try {
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM `User` WHERE userId = " + userId + ";";
			stmt.execute(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
