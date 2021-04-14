package com.techbee.urpersistence;

import java.util.List;
import java.util.Scanner;



public class UserRegistationFrontEnd {
	static Scanner sc = new Scanner(System.in);
	static UserProfileDao dao = new UserProfileDao();

	public static void main(String[] args) {

		boolean keepGoing = true;
		int userOption;
		do {
			System.out.println("\nHello, please select a User option.\n" + "1) Add User\n" + "2) Display All Users\n"
					+ "3) Display One User by ID\n" + "4) Update User\n" + "5) Delete User\n" + "6) Exit\n");
			userOption = sc.nextInt();
			sc.nextLine();

			switch (userOption) {
			case 1:
				addUserPrompt();
				break;
			case 2:
				displayAllUsers();
				break;
			case 3:
				displayOneUser();
				break;
			case 4:
				displayUpdateUserProfile();
				break;
			case 5:
				displayRemoveUserPrompt();
				break;
			case 6:
				keepGoing = false;
				break;
			}
		} while (keepGoing);
		System.out.println("Good Bye!");
	}

	public static void addUserPrompt() {
		UserProfile user = new UserProfile();
		user = enterUserInfoTemplate(user);
		dao.addUser(user);
		System.out.println("New User Added!");
	}

	public static void displayAllUsers() {
		List<UserProfile> users = dao.getAllUsers();
		for (UserProfile user : users) {
			System.out.println(user.getUserProfileId() + ") " + user.getFirstName() + " " + user.getLastName() + "\n"
					+ " - " + user.getEmail() + "\n -" + user.getPassword());
		}
	}

	public static UserProfile displayOneUser() {
		System.out.println("Enter User ID to display User.");
		int id = sc.nextInt();
		UserProfile user = dao.getUser(id);
		System.out.println(user.getUserProfileId() + ") " + user.getFirstName() + " " + user.getLastName() + "\n"
				+ " - " + user.getEmail() + "\n -" + user.getPassword());
		return user;
	}

	public static void displayUpdateUserProfile() {
		boolean isInvalidId;
		UserProfile user;
		do {
			isInvalidId = false;
			user = displayOneUser();
			if (user == null) {
				isInvalidId = true;
			}
		} while (isInvalidId);
		sc.nextLine();
		System.out.println("Update this User? (y-Yes n-No)");
		String update = sc.nextLine();
		if (update.equalsIgnoreCase("y")) {
			System.out.println("Press enter with blank entry to leave current " + "info");
			user = enterUserInfoTemplate(user);
			dao.updateUser(user);
			System.out.println("User updated!");
		}

	}

	public static void displayRemoveUserPrompt() {

		int id;
		boolean isValid;
		do {
			isValid = false;
			if (dao.getAllUsers().size() <= 0) {
				break;
			}
			System.out.println("Enter an existing User ID to delete User.");
			id = sc.nextInt();
			isValid = dao.deleteUser(id);
			if (isValid) {
				System.out.println("User Removed!");
			}
		} while (!isValid);

	}

	public static UserProfile enterUserInfoTemplate(UserProfile user) {
		boolean hasErrors;
		String currFirstName = user.getFirstName();
		String currLastName = user.getLastName();
		String currEmail = user.getEmail();
		String currPassword = user.getPassword();
		String tempFirstName = "";
		String tempLastName = "";
		String tempEmail = "";
		String tempPassword = "";

		do {
			hasErrors = false;
			if (!tempFirstName.isBlank()) {
				// skip
			} else {
				String currFS = currFirstName != null ? currFirstName : "";
				System.out.println("Enter First Name\n" + "Current first name: " + currFS);
				tempFirstName = sc.nextLine();
				if (tempFirstName.isBlank() & currFirstName != null) {
					tempFirstName = currFirstName;
				}
				hasErrors = !user.setFirstName(tempFirstName);
				if (hasErrors) {
					continue;
				}
			}
			if (!tempLastName.isBlank()) {
				// skip
			} else {
				String currLS = currLastName != null ? currLastName : "";
				System.out.println("Enter Last Name\n" + "Current last name: " + currLS);
				tempLastName = sc.nextLine();
				if (tempLastName.isBlank() & currLastName != null) {
					tempLastName = currLastName;
				}
				hasErrors = !user.setLastName(tempLastName);
				if (hasErrors) {
					continue;
				}
			}
			if (!tempEmail.isBlank()) {
				// skip
			} else {
				String currE = currEmail != null ? currEmail : "";
				System.out.println("Enter Email\n" + "Current email: " + currE);
				tempEmail = sc.nextLine();
				if (tempEmail.isBlank() & currEmail != null) {
					tempEmail = currEmail;
				}
				hasErrors = !user.setEmail(tempEmail);
				if (hasErrors) {
					continue;
				}
			}
			if (!tempPassword.isBlank()) {
				// skip
			} else {
				String currP = currPassword != null ? currPassword : "";
				System.out.println("Enter Password\n" + "Current Password: " + currP);
				tempPassword = sc.nextLine();
				if (tempPassword.isBlank() & currPassword != null) {
					tempPassword = currPassword;
					continue;
				}
				System.out.println("Re-type password to confirm");
				String confirmPW = sc.nextLine();
				if (!tempPassword.equals(confirmPW)) {
					System.out.println("Passwords must match. Please try again");
					hasErrors = true;
					tempPassword = "";
				} else {
					hasErrors = !user.setPassword(tempPassword);
					if (hasErrors) {
						continue;
					}
				}
			}
		} while (hasErrors);
		return user;
	}
}
