package com.group27.ui;

import com.group27.dao.UserDAO;
import com.group27.model.Contact;
import com.group27.model.User;
import com.group27.util.PasswordUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Scanner;

public abstract class BaseMenu implements Menu {

    protected User user;
    protected Scanner scanner;
    protected UserDAO userDAO;


    protected static final String REGEX_NAME = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s\\-]{2,50}$";
    protected static final String REGEX_PHONE = "^[0-9\\s\\-\\(\\)]{10,15}$";
    protected static final String REGEX_EMAIL = "^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9]+([.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";

    public BaseMenu(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
        this.userDAO = new UserDAO();
    }


    protected String getValidatedInput(String prompt, String regex, String errorMsg, boolean allowEmpty) {
        int attemptCount = 0;
        final int MAX_ATTEMPTS = 100;

        while (attemptCount < MAX_ATTEMPTS) {
            attemptCount++;
            System.out.print(prompt);

            if (!scanner.hasNextLine()) {
                System.out.println("ERROR: Input stream closed!");
                return allowEmpty ? "" : null;
            }

            String input = scanner.nextLine();

            if (input == null) {
                input = "";
            }

            input = input.trim();

            // boş değer
            if (input.isEmpty()) {
                if (allowEmpty) {
                    return ""; // İzin varsa boş dön (Eski değeri koru)
                } else {
                    System.out.println("❌ ERROR: This field is required! Cannot be empty.");
                    System.out.println("   Please try again.\n");
                    continue; // Döngü başa döner, tekrar sorar
                }
            }

            // boşluk karakter
            if (input.replace(" ", "").isEmpty()) {
                System.out.println("❌ ERROR: Input cannot contain only spaces!");
                System.out.println("   Please enter valid data.\n");
                continue;
            }

            // regex
            if (regex != null && !input.matches(regex)) {
                System.out.println(" " + errorMsg);
                System.out.println("   Please try again with correct format.\n");
                continue;
            }


            return input;
        }

        System.out.println("⚠️ Maximum attempt limit reached. Operation cancelled.");
        return allowEmpty ? "" : null;
    }

    protected java.sql.Date getValidatedDate(String prompt, boolean allowEmpty) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-M-d")
                .withResolverStyle(ResolverStyle.STRICT);

        int attemptCount = 0;
        final int MAX_ATTEMPTS = 100;

        while (attemptCount < MAX_ATTEMPTS) {
            attemptCount++;
            System.out.print(prompt + " (Format: YYYY-MM-DD): ");

            if (!scanner.hasNextLine()) {
                System.out.println("❌ ERROR: Input stream closed!");
                return null;
            }

            String input = scanner.nextLine();
            if (input == null) input = "";
            input = input.trim();

            if (input.isEmpty()) {
                if (allowEmpty) {
                    return null;
                }
                System.out.println("❌ ERROR: Date is required! Cannot be empty.");
                System.out.println("   Example: 2000-12-31\n");
                continue;
            }

            // 2. Format Kontrolü (Regex ile ön kontrol)
            // Yıl: 4 rakam, Ay: 1 veya 2 rakam, Gün: 1 veya 2 rakam (Tire ile ayrılmış)
            if (!input.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                System.out.println("❌ ERROR: Invalid date format!");
                System.out.println("   Correct format: YYYY-MM-DD (Example: 2000-12-31)");
                System.out.println("   Your input: " + input + "\n");
                continue;
            }

            // 3. Mantıksal Kontrol (DateTimeFormatter ile)
            try {
                LocalDate localDate = LocalDate.parse(input, formatter);

                // Mantıklı yıl aralığı (Örn: 1900 - 2100)
                if (localDate.getYear() < 1900 || localDate.getYear() > 2100) {
                    System.out.println("❌ ERROR: Year must be between 1900 and 2100.");
                    System.out.println("   Your input year: " + localDate.getYear() + "\n");
                    continue;
                }

                // Gelecek tarihleri engelle (isteğe bağlı)
                if (localDate.isAfter(LocalDate.now())) {
                    System.out.println("⚠️ WARNING: You entered a future date!");
                    System.out.print("   Do you want to continue? (Y/N): ");
                    String confirm = scanner.nextLine().trim().toUpperCase();
                    if (!confirm.equals("Y") && !confirm.equals("YES")) {
                        continue;
                    }
                }

                return java.sql.Date.valueOf(localDate);

            } catch (DateTimeParseException e) {
                System.out.println("❌ ERROR: Invalid Date!");
                System.out.println("   Reason: " + e.getMessage() + " - This date does not exist!"); // Hata mesajını basitleştirdik
                System.out.println("   (Example: February 30, April 31 are invalid dates)");
                System.out.println("   Please try again.\n");
            }
        }

        System.out.println("⚠️ Maximum attempt limit reached. Operation cancelled.");
        return null;
    }

    protected int readValidInt(String prompt) {
        int attemptCount = 0;
        final int MAX_ATTEMPTS = 100;

        while (attemptCount < MAX_ATTEMPTS) {
            attemptCount++;
            System.out.print(prompt);

            if (!scanner.hasNextLine()) {
                System.out.println("❌ ERROR: Input stream closed!");
                return -1;
            }

            String input = scanner.nextLine();
            if (input == null) input = "";
            input = input.trim();

            if (input.isEmpty()) {
                System.out.println("❌ ERROR: Input cannot be empty. Please enter a number.\n");
                continue;
            }

            // Sadece rakam kontrolü
            if (!input.matches("^-?\\d+$")) {
                System.out.println("❌ ERROR: Invalid input! Only numbers are allowed.");
                System.out.println("   Your input: " + input + "\n");
                continue;
            }

            try {
                int value = Integer.parseInt(input);

                // Negatif sayı kontrolü (ID'ler için)
                if (prompt.toLowerCase().contains("id") && value < 0) {
                    System.out.println("❌ ERROR: ID cannot be negative!");
                    System.out.println("   Your input: " + value + "\n");
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Number is too large or invalid!");
                System.out.println("   Please enter a valid integer.\n");
            }
        }

        System.out.println("⚠️ Maximum attempt limit reached. Operation cancelled.");
        return -1;
    }


    protected String readName(String label, boolean isRequired) {
        String result = getValidatedInput(
                label + ": ",
                REGEX_NAME,
                "ERROR: Name must contain only letters (2-50 characters)!\n   No numbers or special symbols allowed.\n   Allowed: letters, spaces, hyphens (-)",
                !isRequired
        );

        // İsim boşsa ve required ise null döndür
        if (result == null && isRequired) {
            return null;
        }

        return result;
    }

    protected String readPhone(String label, boolean isRequired) {
        String result = getValidatedInput(
                label + ": ",
                REGEX_PHONE,
                "ERROR: Invalid Phone Format!\n   Allowed: digits, spaces, hyphens, parentheses (10-15 characters)\n   Examples: 555-123-4567 or (555) 123-4567 or 5551234567",
                !isRequired
        );

        if (result == null && isRequired) {
            return null;
        }

        return result;
    }

    protected String readEmail(String label, boolean isRequired) {
        String result = getValidatedInput(
                label + ": ",
                REGEX_EMAIL,
                "ERROR: Invalid Email Address!\n   Format: username@domain.com\n   Example: user@example.com",
                !isRequired
        );

        if (result == null && isRequired) {
            return null;
        }

        return result;
    }

    protected java.sql.Date readDate(String label, boolean isRequired) {
        return getValidatedDate(label, !isRequired);
    }

    protected String readRequiredString(String label) {
        return getValidatedInput(label + ": ", null, "", false);
    }
    

    protected void changePassword() {
        System.out.println("\n--- CHANGE PASSWORD ---");
        System.out.print("Current Password: ");

        if (!scanner.hasNextLine()) {
            System.out.println("❌ Error: Input stream error!");
            return;
        }

        String oldPass = scanner.nextLine();
        if (oldPass == null || oldPass.trim().isEmpty()) {
            System.out.println("❌ Error: Password cannot be empty!");
            return;
        }

        String oldHash = PasswordUtil.hashPassword(oldPass);

        if (!user.getPasswordHash().equals(oldHash)) {
            System.out.println("❌ Error: Incorrect current password!");
            return;
        }

        System.out.print("New Password: ");
        if (!scanner.hasNextLine()) {
            System.out.println("❌ Error: Input stream error!");
            return;
        }

        String newPass = scanner.nextLine();

        if (newPass == null || newPass.trim().isEmpty()) {
            System.out.println("❌ Error: New password cannot be empty!");
            return;
        }

        if (newPass.length() < 6) {
            System.out.println("❌ Error: Password must be at least 6 characters long!");
            return;
        }

        String newHash = PasswordUtil.hashPassword(newPass);
        if (userDAO.updatePassword(user.getUserId(), newHash)) {
            System.out.println("✅ Password updated successfully!");
            user.setPasswordHash(newHash);
        } else {
            System.out.println("❌ Database Error!");
        }
    }

    protected void printHeader(String title) {
        System.out.println("\n==========================================================================================");
        System.out.println("★ " + title + " PANEL");
        System.out.println("👤 User: " + user.getFullName() + " (" + user.getRole() + ")");
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
                    c.getContactId(), c.getFirstName(), c.getLastName(), nickname,
                    c.getPhonePrimary(), c.getEmail(), linkedin, birthDate);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------\n");
    }
}