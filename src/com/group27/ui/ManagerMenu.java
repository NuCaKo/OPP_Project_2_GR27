package com.group27.ui;

import com.group27.dao.ManagerDAO;
import com.group27.model.Role;
import com.group27.model.User;
import com.group27.util.PasswordUtil;

import java.util.List;
import java.util.Map;

/**
 * Represents the User Interface for the 'MANAGER' role.
 * <p>
 * This class extends {@link SeniorMenu} and adds administrative functionalities
 * such as creating, reading, updating, and deleting (CRUD) system users.
 * It also provides statistical insights and includes an "Undo" mechanism for critical operations.
 * </p>
 *
 * @author Group27
 * @version 1.0
 */
public class ManagerMenu extends SeniorMenu {

    private final ManagerDAO managerDAO = new ManagerDAO();

    /**
     * Constructs a new ManagerMenu.
     * Initializes the menu with the currently logged-in user and the DAO.
     *
     * @param user The currently logged-in user (must have MANAGER role).
     */
    public ManagerMenu(User user) {
        super(user);

    }

    /**
     * Displays the main menu loop for the Manager.
     * <p>
     * Offers options to manage users, view statistics, or navigate to the Senior menu.
     * Uses {@code InputHelper} to safely read user choices.
     * </p>
     */
    @Override
    public void show() {
        while (true) {
            printHeader("MANAGER");
            MenuFrame.printMenuItem(1, "Add User");
            MenuFrame.printMenuItem(2, "Delete User");
            MenuFrame.printMenuItem(3, "Update User");
            MenuFrame.printMenuItem(4, "List Users");
            MenuFrame.printMenuItem(5, "Contact Statistics");
            MenuFrame.printMenuItem(6, "Change My Password");



            printFooter();

            String sec = input.readRequiredString("Choice");

            switch (sec) {
                case "1" -> { addUserFlow(); pause(); }
                case "2" -> { deleteUserFlow(); pause(); }
                case "3" -> { updateUserFlow(); pause(); }
                case "4" -> { listUsersFlow(); pause(); }
                case "5" -> { showStatisticsFlow(); pause(); }
                case "6" -> { changePassword(); pause(); }
                case "0" -> { return; }
                default -> System.out.println(MenuFrame.RED + "Wrong Choice! Please try again." + MenuFrame.RESET);
            }
        }
    }

    /**
     * Retrieves and displays statistical information about the contacts.
     * Fetches data from {@link ManagerDAO#getContactStatistics()}.
     */
    private void showStatisticsFlow() {
        System.out.println("\n--- CONTACTS STATISTICAL INFO ---");

        Map<String, String> stats = managerDAO.getContactStatistics();

        if (stats.containsKey("Error")) {
            System.out.println(MenuFrame.RED + "Error fetching statistics: " + stats.get("Error") + MenuFrame.RESET);
            return;
        }

        for (Map.Entry<String, String> entry : stats.entrySet()) {
            System.out.printf("%-25s : %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println("---------------------------------");
    }

    /**
     * Orchestrates the flow for adding a new system user.
     * <p>
     * Steps:
     * 1. Validates inputs (Unique Username, Strong Password, Name formats).
     * 2. Hashes the password using {@link PasswordUtil}.
     * 3. Selects a Role.
     * 4. Saves to Database.
     * 5. Offers an option to UNDO (Delete the newly created user).
     * </p>
     */
    private void addUserFlow() {
        System.out.println("\n--- ADD NEW USER ---");

        String username = input.readNickname("Username", true);

        if (managerDAO.getUserByUsername(username) != null) {
            System.out.println(MenuFrame.RED + "Error: This username is already taken!" + MenuFrame.RESET);
            return;
        }

        String rawPassword = input.readPassword("Password");
        String passwordHash = PasswordUtil.hashPassword(rawPassword);

        String first = input.readName("First Name", true);
        String last = input.readName("Last Name", true);

        Role role = getRoleSelection();

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordHash);
        u.setFirstName(first);
        u.setLastName(last);
        u.setRole(role);

        if (managerDAO.addUser(u)) {
            System.out.println("User added successfully.");
        } else {
            System.out.println(MenuFrame.RED + "Error: Failed to add user." + MenuFrame.RESET);
            return;
        }

        if (askYesNo("\nDo you want to undo this operation? (The user will be deleted)")) {
            User addedUser = managerDAO.getUserByUsername(username);
            if (addedUser != null && managerDAO.deleteUser(addedUser.getUserId())) {
                System.out.println("Operation undone: User deleted.");
            } else {
                System.out.println(MenuFrame.RED + "Error: Could not undo." + MenuFrame.RESET);
            }
        }
    }

    /**
     * Orchestrates the flow for deleting an existing user.
     * <p>
     * Steps:
     * 1. Requests User ID.
     * 2. Verifies user existence.
     * 3. Asks for confirmation before deletion.
     * 4. Offers an option to UNDO (Restore the deleted user).
     * </p>
     */
    private void deleteUserFlow() {
        System.out.println("\n--- DELETE USER ---");

        int id = input.readValidInt("User ID to be deleted: ");

        User oldUser = managerDAO.getUserById(id);
        if (oldUser == null) {
            System.out.println(MenuFrame.RED + "No user was found with this ID." + MenuFrame.RESET);
            return;
        }

        System.out.println("Deleting: " + oldUser.getUsername() + " (" + oldUser.getFullName() + ")");
        if (!askYesNo("Are you sure you want to delete this user?")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (managerDAO.deleteUser(id)) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println(MenuFrame.RED + "User can't be deleted." + MenuFrame.RESET);
            return;
        }

        if (askYesNo("Do you want to undo the operation? (Restore user)")) {
            if (managerDAO.addUser(oldUser)) {
                System.out.println("✅ Operation undone: User restored.");
            } else {
                System.out.println(MenuFrame.RED + "Undo operation failed." + MenuFrame.RESET);
            }
        }
    }

    /**
     * Orchestrates the flow for updating an existing user's details.
     * <p>
     * Allows updating Username, Name, and Role. Keeps the existing password hash.
     * Offers an option to UNDO (Revert to previous user state).
     * </p>
     */
    private void updateUserFlow() {
        System.out.println("\n--- UPDATE USER ---");

        int id = input.readValidInt("User ID to be updated: ");

        User oldUser = managerDAO.getUserById(id);
        if (oldUser == null) {
            System.out.println(MenuFrame.RED + "There is no user with this ID." + MenuFrame.RESET);
            return;
        }

        System.out.println("Updating user: " + oldUser.getUsername());

        String username = input.readNickname("New Username", true);
        String first = input.readName("New First Name", true);
        String last = input.readName("New Last Name", true);

        System.out.println("--- Select New Role ---");
        Role role = getRoleSelection();

        User newUser = new User();
        newUser.setUserId(id);
        newUser.setUsername(username);
        newUser.setFirstName(first);
        newUser.setLastName(last);
        newUser.setRole(role);
        newUser.setPasswordHash(oldUser.getPasswordHash());

        if (managerDAO.updateUser(newUser)) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println(MenuFrame.RED + "User can't be updated." + MenuFrame.RESET);
            return;
        }

        if (askYesNo("Do you want to undo the operation? (Revert changes)")) {
            if (managerDAO.updateUser(oldUser)) {
                System.out.println("Operation undone: Previous information restored.");
            } else {
                System.out.println(MenuFrame.RED + "Undo operation failed." + MenuFrame.RESET);
            }
        }
    }

    /**
     * Displays a formatted list of all users in the system.
     * Shows ID, Username, Full Name, and Role.
     */
    private void listUsersFlow() {
        System.out.println("\n--- USER LIST ---");
        List<User> users = managerDAO.getAllUsers();

        System.out.printf("%-4s | %-15s | %-20s | %-10s%n", "ID", "USERNAME", "FULL NAME", "ROLE");
        System.out.println("-------------------------------------------------------------");

        for (User u : users) {
            System.out.printf("%-4d | %-15s | %-20s | %-10s%n",
                    u.getUserId(),
                    u.getUsername(),
                    u.getFirstName() + " " + u.getLastName(),
                    u.getRole()
            );
        }
        System.out.println("-------------------------------------------------------------\n");
    }


    /**
     * Prompts the user to select a role from a predefined list.
     * Uses a loop to ensure a valid selection is made.
     *
     * @return The selected {@link Role} enum.
     */
    private Role getRoleSelection() {
        while (true) {
            System.out.println("1) TESTER");
            System.out.println("2) JUNIOR_DEV");
            System.out.println("3) SENIOR_DEV");
            System.out.println("4) MANAGER");

            String choice = input.readRequiredString("Select Role (1-4)");

            switch (choice) {
                case "1" -> { return Role.TESTER; }
                case "2" -> { return Role.JUNIOR_DEV; }
                case "3" -> { return Role.SENIOR_DEV; }
                case "4" -> { return Role.MANAGER; }
                default -> System.out.println(MenuFrame.RED + "⚠️ Invalid selection! Please enter 1-4." + MenuFrame.RESET);
            }
        }
    }

    /**
     * A utility method to ask the user a Yes/No question.
     *
     * @param message The question to display to the user.
     * @return {@code true} if the user enters 'Y' or 'YES', {@code false} otherwise.
     */
    private boolean askYesNo(String message) {
        String choice = input.readRequiredString(message + " (Y/N)").toUpperCase();
        switch (choice) {
            case "Y", "YES" -> { return true; }
            case "N", "NO" -> { return false; }
            default -> {
                System.out.println("Invalid input. Assuming 'NO'.");
                return false;
            }
        }
    }

    private void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}