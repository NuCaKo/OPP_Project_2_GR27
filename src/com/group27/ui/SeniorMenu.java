package com.group27.ui;

import com.group27.dao.SeniorDAO;
import com.group27.model.Contact;
import com.group27.model.User;
import java.sql.Date;

/**
 * Menu for Senior Developer role, providing functionality to add, delete, and restore contacts.
 */
public class SeniorMenu extends JuniorMenu {

    private SeniorDAO seniorDAO;

    /**
     * Constructs a SeniorMenu for the given user.
     *
     * @param user the currently logged-in user
     */
    public SeniorMenu(User user) {
        super(user);
        this.seniorDAO = new SeniorDAO();
    }

    /**
     * Displays the menu and handles user input.
     */
    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printHeader("SENIOR DEVELOPER");
            MenuFrame.printMenuItem(1, "List All Contacts");
            MenuFrame.printMenuItem(2, "Single Field Search");
            MenuFrame.printMenuItem(3, "Multi-Field Search");
            MenuFrame.printMenuItem(4, "Custom Search");
            MenuFrame.printMenuItem(5, "Sort Contacts");
            MenuFrame.printMenuItem(6, "Change Password");
            MenuFrame.printMenuItem(7, "UPDATE Contact");
            MenuFrame.printMenuItem(8, "ADD New Contact");
            MenuFrame.printMenuItem(9, "DELETE Contact");

            printFooter();

            String choice = scanner.nextLine();

            if (choice.matches("[1-6]")) {
                super.handleTesterOperations(choice);
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }

            else if (choice.equals("7")) {
                super.performUpdate();
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }

            else if (choice.equals("8")) {
                performAdd();
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
            else if (choice.equals("9")) {
                performDelete();
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
            else if (choice.equals("0")) {
                running = false;
            } else {
                System.out.println(MenuFrame.RED + "Invalid selection! Please enter 0-9." + MenuFrame.RESET);
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        }
    }


    /**
     * Carries out the add contact function.
     */
    protected void performAdd() {
        System.out.println("\n--- ADD NEW CONTACT ---");

        Contact c = new Contact();

        c.setFirstName(input.readName("First Name", true));
        c.setLastName(input.readName("Last Name", true));
        c.setPhonePrimary(input.readPhone("Phone", true));
        String email = input.readEmail("Email (Optional)", false);

        if (!email.isEmpty()) c.setEmail(email);
        Date birthDate = input.readDate("Birth Date", false, true);
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
            System.out.println(MenuFrame.RED + "Failure: Could not add contact." + MenuFrame.RESET);
        }
    }


    /**
     * Performs the delete contact operation, with an option to undo.
     */
    protected void performDelete() {
        System.out.println("\n--- DELETE CONTACT ---");


        int id = input.readValidInt("Enter Contact ID to delete: ");


        Contact c = seniorDAO.getContactById(id);
        if (c == null) {
            System.out.println(MenuFrame.RED + "Error: Contact not found." + MenuFrame.RESET);
            return;
        }

        System.out.println("Warning: You are about to delete: " + c.getFullName());

        String confirm = "";
        while (true) {
            System.out.print("Are you sure? (y/n): ");
            confirm = scanner.nextLine().trim().toLowerCase();
            if (confirm.equals("y") || confirm.equals("n")) {
                break;
            }
            System.out.println(MenuFrame.RED + "Error: Please enter 'y' or 'n'." + MenuFrame.RESET);
        }

        if (confirm.equals("y")) {

            if (seniorDAO.deleteContact(id)) {
                System.out.println("Success: Contact deleted.");

                String undo = "";
                while (true) {
                    System.out.print("Mistake? Undo immediately? (y/n): ");
                    undo = scanner.nextLine().trim().toLowerCase();
                    if (undo.equals("y") || undo.equals("n")) {
                        break;
                    }
                    System.out.println(MenuFrame.RED + "Error: Please enter 'y' or 'n'." + MenuFrame.RESET);
                }

                if (undo.equals("y")) {
                    if (seniorDAO.restoreContact(id)) {
                        System.out.println("Success: Contact restored!");
                    } else {
                        System.out.println(MenuFrame.RED + "Error: Restore failed." + MenuFrame.RESET);
                    }
                } else {
                    System.out.println("Deletion finalized.");
                }

            } else {
                System.out.println(MenuFrame.RED + "Failure: Could not delete contact." + MenuFrame.RESET);
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }
}