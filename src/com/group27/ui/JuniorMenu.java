package com.group27.ui;

import com.group27.dao.JuniorDAO;
import com.group27.model.Contact;
import com.group27.model.User;
import java.sql.Date;

public class JuniorMenu extends TesterMenu {

    private JuniorDAO juniorDAO;

    public JuniorMenu(User user) {
        super(user);
        this.juniorDAO = new JuniorDAO();
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printHeader("JUNIOR DEVELOPER");
            System.out.println("1. List All Contacts");
            System.out.println("2. Single Field Search");
            System.out.println("3. Multi-Field Search");
            System.out.println("4. Custom Search");
            System.out.println("5. Sort Contacts");
            System.out.println("6. Change Password");
            System.out.println("7. UPDATE Contact"); //
            printFooter();

            String choice = scanner.nextLine();

            if (choice.matches("[1-6]")) {
                super.handleTesterOperations(choice);
            }
            else if (choice.equals("7")) {
                performUpdate();
            }
            else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println("Invalid selection! Please enter 0-7.");
            }
        }
    }


    protected void performUpdate() {
        System.out.println("\n--- UPDATE CONTACT ---");

        int id = input.readValidInt("Enter Contact ID to update: ");

        Contact c = juniorDAO.getContactById(id);
        if (c == null) {
            System.out.println("Error: Contact with ID " + id + " not found.");
            return;
        }

        System.out.println("\n--- EDITING: " + c.getFullName() + " ---");
        System.out.println("ℹ️ Tip: Press ENTER to keep the current value.");


        String newName = input.readName("First Name (" + c.getFirstName() + ")", false);
        if (!newName.isEmpty()) c.setFirstName(newName);

        String newLast = input.readName("Last Name (" + c.getLastName() + ")", false);
        if (!newLast.isEmpty()) c.setLastName(newLast);

        String newPhone = input.readPhone("Phone (" + c.getPhonePrimary() + ")", false);
        if (!newPhone.isEmpty()) c.setPhonePrimary(newPhone);

        String currentEmail = (c.getEmail() == null) ? "-" : c.getEmail();
        String newEmail = input.readEmail("Email (" + currentEmail + ")", false);
        if (!newEmail.isEmpty()) c.setEmail(newEmail);

        String currentDate = (c.getBirthDate() == null) ? "-" : c.getBirthDate().toString();
        Date newDate = input.readDate("Birth Date (" + currentDate + ")", false);
        if (newDate != null) c.setBirthDate(newDate);

        System.out.print("Nickname (" + c.getSafeNickname() + "): ");
        String newNick = scanner.nextLine().trim();
        if (!newNick.isEmpty()) c.setNickname(newNick);

        System.out.print("LinkedIn (" + c.getSafeLinkedin() + "): ");
        String newLink = scanner.nextLine().trim();
        if (!newLink.isEmpty()) c.setLinkedinUrl(newLink);

        System.out.print("\n❓ Are you sure you want to save these changes? (y/or any key): ");
        String confirm = scanner.nextLine().trim();

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("🚫 Operation cancelled. Changes were NOT saved.");
            return;
        }

        System.out.println(">> Updating database...");
        if (juniorDAO.updateContact(c)) {
            System.out.println("✅ Success: Contact updated successfully!");
        } else {
            System.out.println("❌ Failure: Update failed.");
        }
    }
}