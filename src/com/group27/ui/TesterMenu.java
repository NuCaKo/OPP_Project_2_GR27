package com.group27.ui;

import com.group27.dao.ContactReaderDAO;
import com.group27.model.User;
import com.group27.util.InputHelper;

public class TesterMenu extends BaseMenu {

    protected ContactReaderDAO contactDAO;
    private final InputHelper inputHelper; // InputHelper eklendi

    public TesterMenu(User user) {
        super(user);
        this.contactDAO = new ContactReaderDAO();
        this.inputHelper = new InputHelper(scanner); // Helper başlatıldı
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

            System.out.print("Select: ");
            String choice = scanner.nextLine(); // Menü seçimi basit kalabilir

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

        // Boş geçilemez string okuma
        String ch = inputHelper.readRequiredString("Select Field");

        String field = "";
        boolean isNameField = false;

        if (ch.equals("1")) { field = "first_name"; isNameField = true; }
        else if (ch.equals("2")) { field = "last_name"; isNameField = true; }
        else if (ch.equals("3")) { field = "phone_primary"; }
        else { System.out.println("❌ Invalid field."); return; }

        String key;
        // Eğer isim aranıyorsa özel karakter kontrolü için readName kullan
        if (isNameField) {
            key = inputHelper.readName2("Enter Keyword", true);
        } else {
            // Telefon veya genel arama ise sadece boş olmamasını kontrol et
            key = inputHelper.readRequiredString("Enter Keyword");
        }

        printContactList(contactDAO.searchContacts(field, key));
    }

    protected void performMultiSearchMenu() {
        System.out.println("\n--- MULTI-FIELD SEARCH SCENARIOS ---");
        System.out.println("1. Name AND Birth Month (e.g., 'Ahmet' in '11')");
        System.out.println("2. Surname AND Birth Year (e.g., 'Yilmaz' in '1990')");
        System.out.println("3. Name AND Nickname (e.g., 'Michael' aka 'Boss')");
        System.out.println("4. Name AND LinkedIn Keyword");

        String choice = inputHelper.readRequiredString("Select Scenario");
        String name = null, surname = null, month = null, year = null, nickname = null, linkedin = null;

        switch (choice) {
            case "1":
                // İsimde özel karakter olamaz
                name = inputHelper.readName2("Name", true);

                // Ay kontrolü (Sayı olmalı ve 1-12 arasında olmalı)
                int mInput = inputHelper.readValidInt("Month (1-12): ");
                if (mInput < 1 || mInput > 12) {
                    System.out.println("❌ Invalid month! Must be between 1 and 12.");
                    return;
                }
                month = String.valueOf(mInput);
                break;

            case "2":
                surname = inputHelper.readName2("Surname", true);

                // Yıl kontrolü (Sayı olmalı ve mantıklı bir aralıkta olmalı)
                int yInput = inputHelper.readValidInt("Year (YYYY): ");
                if (yInput < 1900 || yInput > 2100) {
                    System.out.println("❌ Invalid year! Must be between 1900 and 2100.");
                    return;
                }
                year = String.valueOf(yInput);
                break;

            case "3":
                name = inputHelper.readName2("Name", true);
                nickname = inputHelper.readNickname("Nickname", true);
                break;

            case "4":
                name = inputHelper.readName2("Name", true);
                linkedin = inputHelper.readRequiredString("LinkedIn Keyword");
                break;

            default:
                System.out.println("❌ Invalid selection.");
                return;
        }

        printContactList(contactDAO.searchComplex(name, surname, null, null, month, year, nickname, linkedin));
    }

    protected void performCustomSearch() {
        System.out.println("\n--- CUSTOM SEARCH ---");
        System.out.println("1. Search by Email Domain (e.g. gmail, khas)");
        System.out.println("2. Search by Age Range");

        String choice = inputHelper.readRequiredString("Select");

        if (choice.equals("1")) {
            String domain = inputHelper.readRequiredString("Domain");
            printContactList(contactDAO.searchByEmailDomain(domain));

        } else if (choice.equals("2")) {
            // readValidInt zaten sayısal hata kontrolü yapar (try-catch gerekmez)
            int min = inputHelper.readValidInt("Min Age: ");
            int max = inputHelper.readValidInt("Max Age: ");

            // Mantıksal kontroller
            if (min < 0 || max < 0) {
                System.out.println("❌ Age cannot be negative.");
            } else if (min > max) {
                System.out.println("❌ Invalid range! Min age cannot be greater than Max age.");
            } else {
                printContactList(contactDAO.searchByAgeRange(min, max));
            }
        } else {
            System.out.println("❌ Invalid selection.");
        }
    }

    protected void performSort() {
        System.out.println("\n--- SORT CONTACTS ---");
        System.out.println("Field: 1. Name  2. Surname  3. Birth Date");

        String f = inputHelper.readRequiredString("Select Field");
        String dbField = "first_name";

        if (f.equals("2")) dbField = "last_name";
        else if (f.equals("3")) dbField = "birth_date";
        else if (!f.equals("1")) {
            System.out.println("⚠️ Invalid field, defaulting to 'Name'.");
        }

        System.out.println("Order: 1. Ascending (A-Z)  2. Descending (Z-A)");
        String orderChoice = inputHelper.readRequiredString("Select Order");
        boolean asc = orderChoice.equals("1");

        printContactList(contactDAO.getContactsSorted(dbField, asc));
    }
}