package com.group27.ui;

import com.group27.dao.ContactReaderDAO;
import com.group27.model.User;


public class TesterMenu extends BaseMenu {


    protected ContactReaderDAO contactDAO;

    public TesterMenu(User user) {
        super(user);

        this.contactDAO = new ContactReaderDAO();
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
            printFooter();

            String choice = scanner.nextLine();
            if (choice.equals("0")) running = false;
            else handleTesterOperations(choice);
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
            default: System.out.println("❌ Invalid selection!");
        }
    }

    protected void performSearch() {
        System.out.println("\n--- SINGLE FIELD SEARCH ---");
        System.out.println("1. First Name  2. Last Name  3. Phone");
        System.out.print("Select Field: ");
        String ch = scanner.nextLine();
        String field = "";
        if (ch.equals("1")) field = "first_name";
        else if (ch.equals("2")) field = "last_name";
        else if (ch.equals("3")) field = "phone_primary";
        else { System.out.println("Invalid field."); return; }

        System.out.print("Enter Keyword: ");
        String key = scanner.nextLine();
        printContactList(contactDAO.searchContacts(field, key));
    }

    protected void performMultiSearchMenu() {
        System.out.println("\n--- MULTI-FIELD SEARCH SCENARIOS ---");
        System.out.println("1. Name AND Birth Month (e.g., 'Ahmet' in '11')");
        System.out.println("2. Surname AND Birth Year (e.g., 'Yilmaz' in '1990')");
        System.out.println("3. Name AND Nickname (e.g., 'Michael' aka 'Boss')");
        System.out.println("4. Name AND LinkedIn Keyword");
        System.out.print("Select: ");

        String choice = scanner.nextLine();
        String name = null, surname = null, month = null, year = null, nickname = null, linkedin = null;

        switch (choice) {
            case "1":
                System.out.print("Name: "); name = scanner.nextLine().trim();
                System.out.print("Month (1-12): "); month = scanner.nextLine().trim(); break;
            case "2":
                System.out.print("Surname: "); surname = scanner.nextLine().trim();
                System.out.print("Year: "); year = scanner.nextLine().trim(); break;
            case "3":
                System.out.print("Name: "); name = scanner.nextLine().trim();
                System.out.print("Nickname: "); nickname = scanner.nextLine().trim(); break;
            case "4":
                System.out.print("Name: "); name = scanner.nextLine().trim();
                System.out.print("LinkedIn Keyword: "); linkedin = scanner.nextLine().trim(); break;
            default: System.out.println("❌ Invalid."); return;
        }
        printContactList(contactDAO.searchComplex(name, surname, null, null, month, year, nickname, linkedin));
    }

    protected void performCustomSearch() {
        System.out.println("\n--- CUSTOM SEARCH ---");
        System.out.println("1. Search by Email Domain (e.g. gmail, khas)");
        System.out.println("2. Search by Age Range");
        System.out.print("Select: ");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.print("Domain: ");
            printContactList(contactDAO.searchByEmailDomain(scanner.nextLine().trim()));
        } else if (choice.equals("2")) {
            try {
                System.out.print("Min Age: "); int min = Integer.parseInt(scanner.nextLine());
                System.out.print("Max Age: "); int max = Integer.parseInt(scanner.nextLine());
                printContactList(contactDAO.searchByAgeRange(min, max));
            } catch (Exception e) { System.out.println("❌ Invalid input."); }
        }
    }

    protected void performSort() {
        System.out.println("\n--- SORT CONTACTS ---");
        System.out.println("Field: 1. Name  2. Surname  3. Birth Date");
        String f = scanner.nextLine();
        String dbField = "first_name";
        if (f.equals("2")) dbField = "last_name";
        else if (f.equals("3")) dbField = "birth_date";

        System.out.println("Order: 1. Ascending (A-Z)  2. Descending (Z-A)");
        boolean asc = scanner.nextLine().equals("1");

        printContactList(contactDAO.getContactsSorted(dbField, asc));
    }
}