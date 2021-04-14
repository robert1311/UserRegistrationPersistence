package com.techbee.urpersistence;

public class UserProfile {
	private int userProfileId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	/**
	 * @return the userProfileId
	 */
	public int getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(int userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public boolean setFirstName(String firstName) {
		if (!firstName.isBlank()) {
			this.firstName = firstName;
			return true;
		} else {
			System.out.println("First Name can't be blank");
			return false;
		}
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public boolean setLastName(String lastName) {
		if (!lastName.isBlank()) {
			this.lastName = lastName;
			return true;
		} else {
			System.out.println("Last Name can't be blank");
			return false;
		}

	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public boolean setEmail(String email) {
		if (!email.isBlank()) {
			this.email = email;
			return true;
		} else {
			System.out.println("Email can't be blank");
			return false;
		}

	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public boolean setPassword(String password) {
		if (!password.isBlank()) {
			this.password = password;
			return true;
		} else {
			System.out.println("Password can't be blank");
			return false;
		}
	}

	

}
