package com.techbee.urpersistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;



public class UserProfileDao {


	/*final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://userdb.czqmlpibq7qv.us-east-2.rds.amazonaws.com:3306/userdb";
	final String USER = "admin";
	final String PASS = "Mavs11ar";*/
	
	Connection conn = null;
	ArrayList<UserProfile> users = new ArrayList<>();
	int userId = 0;

	public void addUser(UserProfile user) {
		
			/*user.setUserProfileId(userId);
			userId++;
			users.add(user);*/
		
	}

	public UserProfile getUser(int userId) {
		UserProfile selectedUser = null;
		for (UserProfile user : users) {
			if (user.getUserProfileId() == userId) {
				selectedUser = user;
				;
			}
		}
		return selectedUser;
	}

	public List<UserProfile> getAllUsers() {
		return users;
	}

	public void updateUser(UserProfile user) {
		int index = users.indexOf(user);
		users.remove(users.indexOf(user));
		users.add(index, user);
	}

	public boolean deleteUser(int userId) {
		UserProfile user = getUser(userId);
		if (user == null) {
			return false;
		} else {
			users.remove(user);
			return true;
		}
	}
}
