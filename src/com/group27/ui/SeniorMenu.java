package com.group27.ui;

import com.group27.dao.SeniorDAO;
import com.group27.model.Contact;
import com.group27.model.User;
import java.sql.Date;

public class SeniorMenu extends JuniorMenu {

    private SeniorDAO seniorDAO;

    public SeniorMenu(User user) {
        super(user);
        this.seniorDAO = new SeniorDAO();
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printHeader("SENIOR DEVELOPER");

            System.out.println("1. List All Contacts");
            System.out.println("2. Single Field Search");
            System.out.println("3. Multi-Field Search");
            System.out.println("4. Custom Search");
            System.out.println("5. Sort Contacts");
            System.out.println("6. Change Password");
            System.out.println("7. UPDATE Contact");
            System.out.println("8. ADD New Contact");   // Senior Feature
            System.out.println("9. DELETE Contact");    // Senior Feature

            printFooter();

            String choice = scanner.nextLine();

            if (choice.matches("[1-6]")) {
                super.handleTesterOperations(choice);
            }

            else if (choice.equals("7")) {
                super.performUpdate();
            }

            else if (choice.equals("8")) {
                performAdd();
            }
            else if (choice.equals("9")) {
                performDelete();
            }
            else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println("Invalid selection! Please enter 0-9.");
            }
        }
    }

    private void performAdd() {
        System.out.println("\n--- ADD NEW CONTACT ---");

        Contact c = new Contact();

        c.setFirstName(input.readName("First Name", true));
        c.setLastName(input.readName("Last Name", true));
        c.setPhonePrimary(input.readPhone("Phone", true));
        String email = input.readEmail("Email (Optional)", false);

        if (!email.isEmpty()) c.setEmail(email);
        Date birthDate = input.readDate("Birth Date", false);
        if (birthDate != null) c.setBirthDate(birthDate);
        System.out.print("Nickname (Optional): ");
        String nickname = scanner.nextLine().trim();
        if (!nickname.isEmpty()) c.setNickname(nickname);
        System.out.print("LinkedIn (Optional): ");
        String linkedin = scanner.nextLine().trim();
        if (!linkedin.isEmpty()) c.setLinkedinUrl(linkedin);


        System.out.println(">> Saving to database...");
        if (seniorDAO.addContact(c)) {
            System.out.println("Success: Contact added!");
        } else {
            System.out.println("Failure: Could not add contact.");
        }
    }

    private void performDelete() {
        System.out.println("\n--- DELETE CONTACT ---");

        int id = input.readValidInt("Enter Contact ID to delete: ");

        Contact c = seniorDAO.getContactById(id);
        if (c == null) {
            System.out.println("Error: Contact not found.");
            return;
        }

        System.out.println("Warning: You are about to delete: " + c.getFullName());
        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("y")) {
            if (seniorDAO.deleteContact(id)) {
                System.out.println("Success: Contact deleted.");
            } else {
                System.out.println("Failure: Could not delete contact.");
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }
}