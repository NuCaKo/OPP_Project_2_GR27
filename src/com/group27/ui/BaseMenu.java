package com.group27.ui;

import com.group27.dao.UserDAO;
import com.group27.model.Contact;
import com.group27.model.User;
import com.group27.util.PasswordUtil;

import java.util.List;
import java.util.Scanner;

public abstract class BaseMenu implements Menu {

    protected User user;
    protected Scanner scanner;
    protected UserDAO userDAO;

    public BaseMenu(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
    }


    protected void changePassword() {
        System.out.println("\n--- CHANGE PASSWORD ---");
        System.out.print("Current Password: ");
        String oldPass = scanner.nextLine();
        String oldHash = PasswordUtil.hashPassword(oldPass);

        if (!user.getPasswordHash().equals(oldHash)) {
            System.out.println("Error: Incorrect current password!");
            return;
        }
        System.out.print("New Password: ");
        String newPass = scanner.nextLine();
        if (newPass.length() < 3) { System.out.println("Error: Password is too short!"); return; }

        String newHash = PasswordUtil.hashPassword(newPass);
        if (userDAO.updatePassword(user.getUserId(), newHash)) {
            System.out.println("assword updated successfully!");
            user.setPasswordHash(newHash);
        } else {
            System.out.println("Database Error!");
        }
    }

    protected void printHeader(String title) {
        System.out.println("\n==========================================================================================");
        System.out.println("★ " + title + " PANEL");
        System.out.println("👤 User: " + user.getFirstName() + " " + user.getLastName() + " (" + user.getRole() + ")");
        System.out.println("==========================================================================================");
    }

    protected void printFooter() {
        System.out.println("0. Logout");
        System.out.print("Select: ");
    }


    protected void printContactList(List<Contact> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("📭 No records found.");
            return;
        }
        System.out.println("\n--- RESULTS (" + list.size() + " Records) ---");
        System.out.printf("%-4s %-12s %-12s %-10s %-15s %-25s %-25s %-12s\n",
                "ID", "FIRST NAME", "LAST NAME", "NICKNAME", "PHONE", "EMAIL", "LINKEDIN", "BIRTH DATE");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for (Contact c : list) {
            String nickname = (c.getNickname() == null) ? "-" : c.getNickname();
            String linkedin = (c.getLinkedinUrl() == null) ? "-" : c.getLinkedinUrl();
            String birthDate = (c.getBirthDate() == null) ? "-" : c.getBirthDate().toString();
            if(linkedin.length() > 24) linkedin = linkedin.substring(0, 21) + "...";

            System.out.printf("%-4d %-12s %-12s %-10s %-15s %-25s %-25s %-12s\n",
                    c.getContactId(), c.getFirstName(), c.getLastName(), nickname, c.getPhonePrimary(), c.getEmail(), linkedin, birthDate);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------\n");
    }
}