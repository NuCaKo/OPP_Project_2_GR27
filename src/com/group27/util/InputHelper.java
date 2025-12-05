package com.group27.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class InputHelper {

    private final Scanner scanner;

    private static final String REGEX_NAME = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s\\-]{2,50}$";
    private static final String REGEX_NAME2 = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s\\-]+$";
    private static final String REGEX_PHONE = "^\\d{3}-\\d{3}-\\d{4}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9]+([.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";
    private static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }


    private String getValidatedInput(String prompt, String regex, String errorMsg, boolean allowEmpty) {
        int attemptCount = 0;
        final int MAX_ATTEMPTS = 100;

        while (attemptCount < MAX_ATTEMPTS) {
            attemptCount++;
            System.out.print(prompt);

            if (!scanner.hasNextLine()) return allowEmpty ? "" : null;

            String input = scanner.nextLine();
            if (input == null) input = "";
            input = input.trim();

            // 1. Boşluk Kontrolü
            if (input.isEmpty()) {
                if (allowEmpty) return "";
                System.out.println(" ERROR: This field is required! Cannot be empty.");
                continue;
            }

            // 2. Boşluk Karakteri Kontrolü
            if (input.replace(" ", "").isEmpty()) {
                System.out.println(" ERROR: Input cannot contain only spaces!");
                continue;
            }

            // 3. Regex Kontrolü
            if (regex != null && !input.matches(regex)) {
                System.out.println(" " + errorMsg);
                continue;
            }

            return input;
        }
        return allowEmpty ? "" : null;
    }


    public String readName(String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_NAME,
                "ERROR: Name must contain only letters (2-50 chars)!",
                !isRequired
        );
    }

    public String readName2 (String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_NAME2,
                "ERROR: Name must contain only letters!",
                !isRequired
        );
    }

    public String readPhone(String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_PHONE,
                "ERROR: Invalid Phone Format! (e.g. 555-123-4567)",

                !isRequired
        );
    }

    public String readEmail(String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_EMAIL,
                "ERROR: Invalid Email Address!",
                !isRequired
        );
    }

    public String readRequiredString(String label) {
        return getValidatedInput(label + ": ", null, "", false);
    }

    public int readValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(" ERROR: Input cannot be empty.");
                continue;
            }

            if (!input.matches("^-?\\d+$")) {
                System.out.println(" ERROR: Invalid input! Only numbers are allowed.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" ERROR: Number too large.");
            }
        }
    }

    public java.sql.Date readDate(String label, boolean isRequired) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-M-d").withResolverStyle(ResolverStyle.STRICT);

        while (true) {
            System.out.print(label + " (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                if (!isRequired) return null;
                System.out.println(" ERROR: Date is required!");
                continue;
            }

            if (!input.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                System.out.println(" ERROR: Invalid format! Use YYYY-MM-DD.");
                continue;
            }

            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                if (localDate.getYear() < 1900 || localDate.getYear() > 2100) {
                    System.out.println(" ERROR: Year must be between 1900 and 2100.");
                    continue;
                }
                return java.sql.Date.valueOf(localDate);
            } catch (DateTimeParseException e) {
                System.out.println(" ERROR: Invalid Date! (" + e.getMessage() + ")");
            }
        }
    }

    public String readPassword(String label) {
        return getValidatedInput(
                label + ": ",
                REGEX_PASSWORD,
                "ERROR: Password is too weak!\n" +
                        "   - At least 8 characters\n" +
                        "   - At least 1 Uppercase (A-Z)\n" +
                        "   - At least 1 Lowercase (a-z)\n" +
                        "   - At least 1 Digit (0-9)\n" +
                        "   - At least 1 Special Char (@#$%^&+=!)\n" +
                        "   - No spaces allowed.",
                false // Zorunlu (Boş geçilemez)
        );
    }

    public String readNickname(String label, boolean isRequired) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();

            // Boş bırakılabilir
            if (input.isEmpty()) {
                if (!isRequired) return "";
                System.out.println(" ERROR: Nickname is required!");
                continue;
            }

            // Boşluk içeremez
            if (input.contains(" ")) {
                System.out.println(" ERROR: Nickname cannot contain spaces!");
                continue;
            }

            return input;
        }
    }

    public String readNumeric(String label, boolean isRequired) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();

            // 1. Boşluk Kontrolü
            if (input.isEmpty()) {
                if (!isRequired) return "";
                System.out.println(" ❌ ERROR: This field is required!");
                continue;
            }

            // 2. Uzunluk Kontrolü (Maksimum 10 karakter)
            if (input.length() > 10) {
                System.out.println(" ❌ ERROR: Input cannot exceed 10 characters!");
                continue;
            }

            // 3. Karakter Kontrolü (Sadece Rakam ve Tire)
            // Regex Açıklaması: ^[0-9-]+$
            // ^     : Başlangıç
            // [0-9] : Rakamlar
            // -     : Tire işareti
            // +     : En az bir karakter olmalı
            // $     : Bitiş
            if (!input.matches("^[0-9-]+$")) {
                System.out.println(" ❌ ERROR: Only numbers (0-9) and hyphens (-) are allowed!");
                continue;
            }

            return input;
        }
    }


}