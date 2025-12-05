package com.group27.ui;

import com.group27.dao.ContactReaderDAO;
import com.group27.model.User;
import com.group27.util.InputHelper;

public class TesterMenu extends BaseMenu {

    protected ContactReaderDAO contactDAO;
    private final InputHelper inputHelper;

    public TesterMenu(User user) {
        super(user);
        this.contactDAO = new ContactReaderDAO();
        this.inputHelper = new InputHelper(scanner);
    }

    @Override
    public void show() {
        boolean running = true;
        while (running) {
            printHeader("TESTER / VIEWER");
            System.out.println("1. List All Contacts");
            System.out.println("2. Single Field Search");
            System.out.println("3. Multi-Field Search (Scenario Based)");
            System.out.println("4. Custom Search (Age & Domain)");
            System.out.println("5. Sort Contacts");
            System.out.println("6. Change Password");
            System.out.println("0. Logout"); // Footer yerine manuel yazdık, input prompt çakışmasın diye

            int choice = input.readInt("Select Option", 0, 6);

            if (choice == 0) {
                running = false;
            } else {
                handleTesterOperations(String.valueOf(choice));
            }
        }
    }

    protected void handleTesterOperations(String choice) {
        switch (choice) {
            case "1": printContactList(contactDAO.getAllContacts()); break;
            case "2": performSearch(); break;
            case "3": performMultiSearchMenu(); break;
            case "4": performCustomSearch(); break;
            case "5": performSort(); break;
            case "6": changePassword(); break;
            default: System.out.println("Invalid selection!");
        }
    }

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
                keyword = input.readName("Enter Name Keyword", true);
                break;
            case 2:
                field = "last_name";
                keyword = input.readName("Enter Surname Keyword", true);
                break;
            case 3:
                field = "phone_primary";
                keyword = input.readPhone("Enter Phone Number", true);
                break;
        }

        System.out.println(">> Searching for '" + keyword + "' in " + field + "...");
        printContactList(contactDAO.searchContacts(field, keyword));
    }

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
                name = input.readName("Name", true);
                int mInput = input.readInt("Month (1-12)", 1, 12);
                month = String.valueOf(mInput);
                break;

            case 2:
                surname = input.readName("Surname", true);
                int yInput = input.readInt("Year (YYYY)", 1900, 2100);
                year = String.valueOf(yInput);
                break;

            case 3:
                name = input.readName("Name", true);
                nickname = input.readRequiredString("Nickname");
                break;

            case 4:
                name = input.readName("Name", true);
                linkedin = input.readRequiredString("LinkedIn Keyword");
                break;

        }

        printContactList(contactDAO.searchComplex(name, surname, null, null, month, year, nickname, linkedin));
    }

    protected void performCustomSearch() {
        System.out.println("\n--- CUSTOM SEARCH ---");
        System.out.println("1. Search by Email Domain (e.g. gmail, khas)");
        System.out.println("2. Search by Age Range");

        int choice = input.readInt("Select Option", 1, 2);

        if (choice == 1) {

            String domain = input.readRequiredString("Enter Domain Keyword");
            printContactList(contactDAO.searchByEmailDomain(domain));

        } else if (choice == 2) {

            int min = input.readValidInt("Min Age: ");
            int max = input.readValidInt("Max Age: ");

            if (min < 0 || max < 0) {
                System.out.println("Error: Age cannot be negative.");
            } else if (min > max) {
                System.out.println("Error: Min age cannot be greater than Max age.");
            } else {
                printContactList(contactDAO.searchByAgeRange(min, max));
            }
        }
    }

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