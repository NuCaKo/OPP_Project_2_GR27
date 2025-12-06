package com.group27.ui;

import com.group27.dao.UserDAO;
import com.group27.model.Contact;
import com.group27.model.User;
import com.group27.util.InputHelper;
import com.group27.util.PasswordUtil;

import java.util.List;
import java.util.Scanner;

/**
 * Abstract base class for all menus, providing common functionality.
 */
public abstract class BaseMenu implements Menu {

    protected User user;
    protected Scanner scanner;
    protected UserDAO userDAO;
    protected InputHelper input;

    /**
     * Creates a BaseMenu for the specified user.
     *
     * @param user the currently logged-in user
     */
    public BaseMenu(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
        this.input = new InputHelper(this.scanner);
        MenuFrame.animateMenu();
    }


    /**
     * Handles the change password workflow for the user.
     */
    protected void changePassword() {
        System.out.println("\n--- CHANGE PASSWORD ---");
        String oldPass = input.readRequiredString("Current Password");
        String oldHash = PasswordUtil.hashPassword(oldPass);

        if (!user.getPasswordHash().equals(oldHash)) {
            System.out.println(MenuFrame.RED + "Error: Incorrect current password!" + MenuFrame.RESET);
            return;
        }

        String newPass = input.readPassword("New Password");
        String newHash = PasswordUtil.hashPassword(newPass);

        if (userDAO.updatePassword(user.getUserId(), newHash)) {
            System.out.println("Password updated successfully!");
            user.setPasswordHash(newHash);
        } else {
            System.out.println(MenuFrame.RED + "Database Error!" + MenuFrame.RESET);
        }
    }

    /**
     * Prints the menu header with the given title.
     *
     * @param title the title of the menu
     */
    protected void printHeader(String title) {
        MenuFrame.printHeader(title + " PANEL",
                "User: " + user.getFullName() + " [" + user.getRole() + "]");
    }

    /**
     * Prints the standard menu footer.
     */
    protected void printFooter() {
        MenuFrame.printFooter();
    }

    /**
     * Clears the console screen.
     */
    protected void clearScreen() {
        MenuFrame.clearScreen();
    }

    /**
     * Prints a formatted list of contacts to the console.
     *
     * @param list the list of contacts to display
     */
    protected void printContactList(List<Contact> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("📭 No records found.");
            return;
        }
        System.out.println("\n--- RESULTS (" + list.size() + " Records) ---");
        System.out.printf("%-4s %-12s %-12s %-10s %-15s %-25s %-25s %-12s\n",
                "ID", "FIRST NAME", "LAST NAME", "NICKNAME", "PHONE", "EMAIL", "LINKEDIN", "BIRTH DATE");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Contact c : list) {
            String bd = (c.getBirthDate() == null) ? "-" : c.getBirthDate().toString();
            System.out.printf("%-4d %-12s %-12s %-10s %-15s %-25s %-25s %-12s\n",
                    c.getContactId(), c.getFirstName(), c.getLastName(), c.getSafeNickname(),
                    c.getPhonePrimary(), c.getEmail(), c.getSafeLinkedin(), bd);
        }
        System.out.println("---------------------------------------------------------------------------------------------\n");
    }
}