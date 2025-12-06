package com.group27.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/**
 * Utility class for handling and validating user input from the console.
 */
public class InputHelper {

    private final Scanner scanner;

    private static final String REGEX_NAME = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s\\-]{2,50}$";
    private static final String REGEX_NAME2 = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s\\-]+$";
    private static final String REGEX_PHONE = "^\\d{3}-\\d{3}-\\d{4}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9][a-zA-Z0-9._-]*@[a-zA-Z0-9]+([.-][a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";
    private static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    /**
     * Constructs an InputHelper with the specified Scanner.
     *
     * @param scanner the Scanner object to read input from
     */
    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }


    /**
     * Reads and validates input based on a regex pattern.
     *
     * @param prompt     the message to display to the user
     * @param regex      the regular expression to validate against (can be null)
     * @param errorMsg   the error message to display if validation fails
     * @param allowEmpty whether empty input is allowed
     * @return the validated input string
     */
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

            if (input.isEmpty()) {
                if (allowEmpty) return "";
                System.out.println(RED + " ERROR: This field is required! Cannot be empty." + RESET);
                continue;
            }

            if (input.replace(" ", "").isEmpty()) {
                System.out.println(RED + " ERROR: Input cannot contain only spaces!" + RESET);
                continue;
            }

            if (regex != null && !input.matches(regex)) {
                System.out.println(RED + " " + errorMsg + RESET);
                continue;
            }

            return input;
        }
        return allowEmpty ? "" : null;
    }


    /**
     * Reads a name from the user.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid name string
     */
    public String readName(String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_NAME,
                "ERROR: Name must contain only letters (2-50 chars)!",
                !isRequired
        );
    }

    /**
     * Reads a name using the secondary regex pattern.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid name string
     */
    public String readName2 (String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_NAME2,
                "ERROR: Name must contain only letters!",
                !isRequired
        );
    }

    /**
     * Reads a phone number from the user.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid phone number string
     */
    public String readPhone(String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_PHONE,
                "ERROR: Invalid Phone Format! (e.g. 555-123-4567)",

                !isRequired
        );
    }

    /**
     * Reads an email address from the user.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid email address string
     */
    public String readEmail(String label, boolean isRequired) {
        return getValidatedInput(
                label + ": ",
                REGEX_EMAIL,
                "ERROR: Invalid Email Address!",
                !isRequired
        );
    }

    /**
     * Reads a required string input.
     *
     * @param label the label for the input
     * @return the valid string input
     */
    public String readRequiredString(String label) {
        return getValidatedInput(label + ": ", null, "", false);
    }

    /**
     * Reads an integer input from the user.
     *
     * @param label the label for the input
     * @return the parsed integer value
     */
    public int readInt(String label) {
        while (true) {
            String input = readRequiredString(label);

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "ERROR: Invalid input! Please enter a valid number." + RESET);
            }
        }
    }

    /**
     * Reads an integer input within a specified range.
     *
     * @param label the label for the input
     * @param min   the minimum allowed value
     * @param max   the maximum allowed value
     * @return the valid integer value
     */
    public int readInt(String label, int min, int max) {
        while (true) {
            int value = readInt(label);

            if (value >= min && value <= max) {
                return value;
            }

            System.out.println(RED + "ERROR: Please enter a number between " + min + " and " + max + "." + RESET);
        }
    }

    /**
     * Reads a valid integer input from the user directly from the prompt.
     *
     * @param prompt the prompt message to display
     * @return the valid integer value
     */
    public int readValidInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(RED + " ERROR: Input cannot be empty." + RESET);
                continue;
            }

            if (!input.matches("^-?\\d+$")) {
                System.out.println(RED + " ERROR: Invalid input! Only numbers are allowed." + RESET);
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + " ERROR: Number too large." + RESET);
            }
        }
    }

    /**
     * Reads a date input from the user.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid java.sql.Date object
     */
    public java.sql.Date readDate(String label, boolean isRequired) {
        return readDate(label, isRequired, false);
    }

    /**
     * Reads a date input from the user with optional birth date validation.
     *
     * @param label          the label for the input
     * @param isRequired     whether the input is required
     * @param checkBirthDate whether to validate if the user is at least 18 years old
     * @return the valid java.sql.Date object
     */
    public java.sql.Date readDate(String label, boolean isRequired, boolean checkBirthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("u-M-d").withResolverStyle(ResolverStyle.STRICT);

        while (true) {
            System.out.print(label + " (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                if (!isRequired) return null;
                System.out.println(RED + " ERROR: Date is required!" + RESET);
                continue;
            }

            if (!input.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                System.out.println(RED + " ERROR: Invalid format! Use YYYY-MM-DD." + RESET);
                continue;
            }

            try {
                LocalDate localDate = LocalDate.parse(input, formatter);
                LocalDate today = LocalDate.now();

                if (checkBirthDate) {
                    if (localDate.isAfter(today)) {
                        System.out.println(RED + " ERROR: Date cannot be in the future!" + RESET);
                        continue;
                    }

                    if (localDate.plusYears(18).isAfter(today)) {
                        System.out.println(RED + " ERROR: User must be at least 18 years old." + RESET);
                        continue;
                    }
                }

                if (localDate.getYear() < 1900 || localDate.getYear() > 2100) {
                    System.out.println(RED + " ERROR: Year must be between 1900 and 2100." + RESET);
                    continue;
                }
                return java.sql.Date.valueOf(localDate);
            } catch (DateTimeParseException e) {
                System.out.println(RED + " ERROR: Invalid Date! (" + e.getMessage() + ")" + RESET);
            }
        }
    }

    /**
     * Reads a password from the user.
     *
     * @param label the label for the input
     * @return the valid password string
     */
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
                false
        );
    }

    /**
     * Reads a nickname from the user.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid nickname string
     */
    public String readNickname(String label, boolean isRequired) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                if (!isRequired) return "";
                System.out.println(RED + " ERROR: Nickname is required!" + RESET);
                continue;
            }

            if (input.contains(" ")) {
                System.out.println(RED + " ERROR: Nickname cannot contain spaces!" + RESET);
                continue;
            }

            return input;
        }
    }

    /**
     * Reads a numeric string from the user.
     *
     * @param label      the label for the input
     * @param isRequired whether the input is required
     * @return the valid numeric string
     */
    public String readNumeric(String label, boolean isRequired) {
        while (true) {
            System.out.print(label + ": ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                if (!isRequired) return "";
                System.out.println(RED + "ERROR: This field is required!" + RESET);
                continue;
            }

            if (input.length() > 10) {
                System.out.println(RED + "ERROR: Input cannot exceed 10 characters!" + RESET);
                continue;
            }


            if (!input.matches("^[0-9-]+$")) {
                System.out.println(RED + "ERROR: Only numbers (0-9) and hyphens (-) are allowed!" + RESET);
                continue;
            }

            return input;
        }
    }


}