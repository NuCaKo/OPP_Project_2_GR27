package com.group27.ui;

import com.group27.dao.ContactReaderDAO;
import com.group27.model.User;
import com.group27.util.InputHelper;

/**
 * Menu for the Tester role, providing basic read-only operations and search functionality.
 */
public class TesterMenu extends BaseMenu {

    protected ContactReaderDAO contactDAO;
    private final InputHelper inputHelper;

    /**
     * Constructs a TesterMenu for the given user.
     *
     * @param user the currently logged-in user
     */
    public TesterMenu(User user) {
        super(user);
        this.contactDAO = new ContactReaderDAO();
        this.inputHelper = new InputHelper(scanner);
    }

    /**
     * Displays the menu and handles user input.
     */
    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printHeader("TESTER / VIEWER");
            MenuFrame.printMenuItem(1, "List All Contacts");
            MenuFrame.printMenuItem(2, "Single Field Search");
            MenuFrame.printMenuItem(3, "Multi-Field Search (Scenario Based)");
            MenuFrame.printMenuItem(4, "Custom Search (Age & Domain)");
            MenuFrame.printMenuItem(5, "Sort Contacts");
            MenuFrame.printMenuItem(6, "Change Password");

            printFooter();

            String choiceStr = scanner.nextLine();
            int choice = -1;
            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                // Invalid input
            }

            if (choice == 0) {
                running = false;
            } else if (choice >= 1 && choice <= 6) {
                handleTesterOperations(String.valueOf(choice));
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println(MenuFrame.RED + "Invalid selection!" + MenuFrame.RESET);
            }
        }
    }

    /**
     * Handles the common operations available to Testers and higher roles.
     *
     * @param choice the user's menu choice
     */
    protected void handleTesterOperations(String choice) {
        switch (choice) {
            case "1": printContactList(contactDAO.getAllContacts()); break;
            case "2": performSearch(); break;
            case "3": performMultiSearchMenu(); break;
            case "4": performCustomSearch(); break;
            case "5": performSort(); break;
            case "6": changePassword(); break;
            default: System.out.println(MenuFrame.RED + "Invalid selection!" + MenuFrame.RESET);
        }
    }

    /**
     * Performs a single-field search.
     */
    protected void performSearch() {
        System.out.println("\n--- SINGLE FIELD SEARCH ---");

        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Phone");

        int choice = input.readInt("Select Field", 1, 3);

        String field = "";
        String keyword = "";

        switch (choice) {
            case 1:
                field = "first_name";
                keyword = input.readName2("Enter Name Keyword", true);
                break;
            case 2:
                field = "last_name";
                keyword = input.readName2("Enter Surname Keyword", true);
                break;
            case 3:
                field = "phone_primary";
                keyword = input.readNumeric("Enter Phone Number", true);
                break;
        }

        System.out.println(">> Searching for '" + keyword + "' in " + field + "...");
        printContactList(contactDAO.searchContacts(field, keyword));
    }

    /**
     * Performs a multi-field search based on scenarios.
     */
    protected void performMultiSearchMenu() {
        System.out.println("\n--- MULTI-FIELD SEARCH SCENARIOS ---");
        System.out.println("1. Name AND Birth Month (e.g., 'Ahmet' in '11')");
        System.out.println("2. Surname AND Birth Year (e.g., 'Yilmaz' in '1990')");
        System.out.println("3. Name AND Nickname (e.g., 'Michael' aka 'Boss')");
        System.out.println("4. Name AND LinkedIn Keyword");

        int choice = input.readInt("Select Scenario", 1, 4);

        String name = null, surname = null, month = null, year = null, nickname = null, linkedin = null;

        switch (choice) {
            case 1:
                name = input.readName2("Name", true);
                int mInput = input.readInt("Month (1-12)", 1, 12);
                month = String.valueOf(mInput);
                break;

            case 2:
                surname = input.readName2("Surname", true);
                int yInput = input.readInt("Year (YYYY)", 1900, 2100);
                year = String.valueOf(yInput);
                break;

            case 3:
                name = input.readName2("Name", true);
                nickname = input.readRequiredString("Nickname");
                break;

            case 4:
                name = input.readName2("Name", true);
                linkedin = input.readRequiredString("LinkedIn Keyword");
                break;

        }

        printContactList(contactDAO.searchComplex(name, surname, null, null, month, year, nickname, linkedin));
    }

    /**
     * Performs a custom search (e.g., by email domain or age range).
     */
    protected void performCustomSearch() {
        System.out.println("\n--- CUSTOM SEARCH ---");
        System.out.println("1. Search by Email Domain (e.g. gmail, khas)");
        System.out.println("2. Search by Age Range");

        int choice = input.readInt("Select Option", 1, 2);

        if (choice == 1) {
            String domain = input.readRequiredString("Enter Domain Keyword");
            printContactList(contactDAO.searchByEmailDomain(domain));

        } else if (choice == 2) {
            int min = input.readInt("Min Age", 18, 100);
            int max = input.readInt("Max Age", 18, 100);

            if (min > max) {
                System.out.println(MenuFrame.RED + "Error: Min age cannot be greater than Max age." + MenuFrame.RESET);
            } else {
                printContactList(contactDAO.searchByAgeRange(min, max));
            }
        }
    }

    /**
     * Performs a sort operation on contacts.
     */
    protected void performSort() {
        System.out.println("\n--- SORT CONTACTS ---");

        System.out.println("Field: 1. Name  2. Surname  3. Birth Date");

        int fieldChoice = input.readInt("Select Field", 1, 3);

        String dbField = "";
        switch (fieldChoice) {
            case 1: dbField = "first_name"; break;
            case 2: dbField = "last_name"; break;
            case 3: dbField = "birth_date"; break;
        }

        if (dbField.equals("birth_date")) {
            System.out.println("Order: 1. Chronological (Oldest -> Newest)  2. Reverse (Newest -> Oldest)");
        } else {
            System.out.println("Order: 1. Ascending (A -> Z)  2. Descending (Z -> A)");
        }

        int orderChoice = input.readInt("Select Order", 1, 2);

        boolean asc = (orderChoice == 1);

        printContactList(contactDAO.getContactsSorted(dbField, asc));
    }
}