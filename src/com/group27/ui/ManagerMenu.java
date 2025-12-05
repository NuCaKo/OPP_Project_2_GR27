package com.group27.ui;

import com.group27.dao.ManagerDAO;
import com.group27.model.Role;
import com.group27.model.User;
import com.group27.util.PasswordUtil; // Make sure you have this utility

import java.util.List;
import java.util.Map;

public class ManagerMenu extends SeniorMenu {

    private final ManagerDAO managerDAO = new ManagerDAO();

    public ManagerMenu(User user) {
        super(user);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("1) Add User");
            System.out.println("2) Delete User");
            System.out.println("3) Update User");
            System.out.println("4) List All Users");
            System.out.println("5) Contacts Statistical Info");
            System.out.println("6) Go to the Senior (contact) menu");
            System.out.println("0) Exit");

            String sec = input.readRequiredString("Choice");

            switch (sec) {
                case "1" -> addUserFlow();
                case "2" -> deleteUserFlow();
                case "3" -> updateUserFlow();
                case "4" -> listUsersFlow();
                case "5" -> showStatisticsFlow();
                case "6" -> super.show();
                case "0" -> { return; }
                default -> System.out.println("❌ Wrong Choice! Please try again.");
            }
        }
    }

    private void showStatisticsFlow() {
        System.out.println("\n--- CONTACTS STATISTICAL INFO ---");

        Map<String, String> stats = managerDAO.getContactStatistics();

        if (stats.containsKey("Error")) {
            System.out.println("Error fetching statistics: " + stats.get("Error"));
            return;
        }

        for (Map.Entry<String, String> entry : stats.entrySet()) {
            System.out.printf("%-25s : %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println("---------------------------------");
    }

    private void addUserFlow() {
        System.out.println("\n--- ADD NEW USER ---");

        String username = input.readNickname("Username", true);

        if (managerDAO.getUserByUsername(username) != null) {
            System.out.println("Error: This username is already taken!");
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
            System.out.println("Error: Failed to add user.");
            return;
        }

        if (askYesNo("\nDo you want to undo this operation? (The user will be deleted)")) {
            User addedUser = managerDAO.getUserByUsername(username);
            if (addedUser != null && managerDAO.deleteUser(addedUser.getUserId())) {
                System.out.println("Operation undone: User deleted.");
            } else {
                System.out.println("Error: Could not undo.");
            }
        }
    }

    private void deleteUserFlow() {
        System.out.println("\n--- DELETE USER ---");

        int id = input.readValidInt("User ID to be deleted: ");

        User oldUser = managerDAO.getUserById(id);
        if (oldUser == null) {
            System.out.println("No user was found with this ID.");
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
            System.out.println("User can't be deleted.");
            return;
        }

        if (askYesNo("Do you want to undo the operation? (Restore user)")) {
            if (managerDAO.addUser(oldUser)) {
                System.out.println("Operation undone: User restored.");
            } else {
                System.out.println("Undo operation failed.");
            }
        }
    }

    private void updateUserFlow() {
        System.out.println("\n--- UPDATE USER ---");

        int id = input.readValidInt("User ID to be updated: ");

        User oldUser = managerDAO.getUserById(id);
        if (oldUser == null) {
            System.out.println("There is no user with this ID.");
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
            System.out.println("User can't be updated.");
            return;
        }

        if (askYesNo("Do you want to undo the operation? (Revert changes)")) {
            if (managerDAO.updateUser(oldUser)) {
                System.out.println("Operation undone: Previous information restored.");
            } else {
                System.out.println("Undo operation failed.");
            }
        }
    }

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
                default -> System.out.println("Invalid selection! Please enter 1-4.");
            }
        }
    }

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
}