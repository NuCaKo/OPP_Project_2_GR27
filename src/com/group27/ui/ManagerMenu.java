package com.group27.ui;

import com.group27.dao.ManagerDAO;
import com.group27.model.Role;
import com.group27.model.User;
import com.group27.util.PasswordUtil;

import java.util.List;

public class ManagerMenu extends BaseMenu {

    private final ManagerDAO managerDAO;

    public ManagerMenu(User user) {
        super(user);
        this.managerDAO = new ManagerDAO();
    }

    @Override
    public void show() {
        while (true) {
            printHeader("MANAGER");

            System.out.println("1) Add New User");
            System.out.println("2) Delete User");
            System.out.println("3) Update User");
            System.out.println("4) List All Users");
            System.out.println("5) System Statistics");
            System.out.println("6) Change My Password");

            printFooter();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addUserFlow();
                case "2" -> deleteUserFlow();
                case "3" -> updateUserFlow();
                case "4" -> listUsersFlow();
                case "5" -> contactStatistics();
                case "6" -> changePassword();
                case "0" -> { return; }
                default -> System.out.println("❌ Invalid choice!");
            }
        }
    }

    // -------------------- STATISTICS ----------------------

    private void contactStatistics() {
        System.out.println("\n--- 📊 SYSTEM STATISTICS ---");
        int total = managerDAO.getTotalContacts();
        int birthdays = managerDAO.getUpcomingBirthdaysCount();
        int noEmail = managerDAO.getNoEmailCount();

        System.out.println("Total Contacts        : " + total);
        System.out.println("Upcoming Birthdays (7d): " + birthdays);
        System.out.println("Contacts w/o Email    : " + noEmail);
    }

    // -------------------- USER CRUD OPERATIONS ---------------------

    private void addUserFlow() {
        System.out.println("\n--- ➕ ADD NEW USER ---");

        // 1. Verileri Topla
        String username = input.readNickname("Username", true);

        // Kullanıcı adı zaten var mı diye kontrol etmek iyi bir UX pratiğidir
        if (managerDAO.getUserByUsername(username) != null) {
            System.out.println("❌ Error: This username is already taken!");
            return;
        }

        String rawPassword = input.readPassword("Password");
        String passwordHash = PasswordUtil.hashPassword(rawPassword);

        String first = input.readName("First Name", true);
        String last = input.readName("Last Name", true);
        Role role = readRole();

        // 2. Özet Göster ve Onay İste (UNDO MEKANİZMASI)
        System.out.println("\n--- CONFIRMATION ---");
        System.out.println("Username : " + username);
        System.out.println("Name     : " + first + " " + last);
        System.out.println("Role     : " + role);
        System.out.println("--------------------");

        if (!getConfirmation("Do you want to save this user?")) {
            System.out.println("↩️ Operation cancelled (Undone).");
            return;
        }

        // 3. İşlemi Gerçekleştir
        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordHash);
        u.setFirstName(first);
        u.setLastName(last);
        u.setRole(role);

        if (managerDAO.addUser(u))
            System.out.println("✅ User added successfully.");
        else
            System.out.println("❌ Failed to add user.");
    }

    private void deleteUserFlow() {
        System.out.println("\n--- 🗑 DELETE USER ---");

        int id = input.readValidInt("User ID to delete: ");

        if (id == user.getUserId()) {
            System.out.println("❌ You cannot delete your own account!");
            return;
        }

        // Silinecek kullanıcıyı bulup gösterelim (Kimi sildiğini bilsin)
        User targetUser = managerDAO.getUserById(id);
        if (targetUser == null) {
            System.out.println("❌ User not found.");
            return;
        }

        // UNDO MEKANİZMASI
        System.out.println("\n--- ⚠️ WARNING ---");
        System.out.println("You are about to delete: " + targetUser.getUsername() + " (" + targetUser.getFullName() + ")");

        if (!getConfirmation("Are you sure you want to delete this user?")) {
            System.out.println("↩️ Delete operation cancelled.");
            return;
        }

        if (managerDAO.deleteUser(id))
            System.out.println("✅ User deleted.");
        else
            System.out.println("❌ Delete failed.");
    }

    private void updateUserFlow() {
        System.out.println("\n--- ✏️ UPDATE USER ---");

        int id = input.readValidInt("User ID to update: ");

        User old = managerDAO.getUserById(id);
        if (old == null) {
            System.out.println("❌ User not found.");
            return;
        }

        System.out.println("Updating user: " + old.getUsername());

        // Yeni verileri al
        String username = input.readNickname("New Username", true);
        String first = input.readName("New First Name", true);
        String last = input.readName("New Last Name", true);
        Role role = readRole();

        // Onay İste
        System.out.println("\n--- REVIEW CHANGES ---");
        System.out.println("Old -> New Username: " + old.getUsername() + " -> " + username);
        System.out.println("Old -> New Role    : " + old.getRole() + " -> " + role);

        if (!getConfirmation("Do you want to apply these changes?")) {
            System.out.println("↩️ Update cancelled.");
            return;
        }

        // Nesneyi güncelle
        old.setUsername(username);
        old.setFirstName(first);
        old.setLastName(last);
        old.setRole(role);

        if (managerDAO.updateUser(old))
            System.out.println("✅ User updated.");
        else
            System.out.println("❌ Update failed!");
    }

    private void listUsersFlow() {
        System.out.println("\n--- 👥 USER LIST ---");
        List<User> users = managerDAO.getAllUsers();

        System.out.printf("%-4s %-15s %-20s %-10s\n", "ID", "USERNAME", "FULL NAME", "ROLE");
        System.out.println("---------------------------------------------------------");
        for (User u : users) {
            System.out.printf("%-4d %-15s %-20s %-10s\n",
                    u.getUserId(),
                    u.getUsername(),
                    u.getFullName(),
                    u.getRole());
        }
        System.out.println("---------------------------------------------------------\n");
    }

    // -------------------- HELPERS ------------------------------

    private Role readRole() {
        while (true) {
            String r = input.readRequiredString("Role (TESTER/JUNIOR_DEV/SENIOR_DEV/MANAGER)").toUpperCase();
            try {
                return Role.valueOf(r);
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Invalid role! Options: TESTER, JUNIOR_DEV, SENIOR_DEV, MANAGER");
            }
        }
    }

    // Kullanıcıya Evet/Hayır sorusu soran yardımcı metot
    private boolean getConfirmation(String message) {
        while (true) {
            // InputHelper'ı kullanarak soruyoruz
            String response = input.readRequiredString(message + " (y/n)").trim().toLowerCase();

            if (response.equals("y") || response.equals("yes")) {
                return true;
            } else if (response.equals("n") || response.equals("no")) {
                return false;
            } else {
                System.out.println("⚠️ Please answer with 'y' or 'n'.");
            }
        }
    }
}