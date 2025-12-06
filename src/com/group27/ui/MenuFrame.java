package com.group27.ui;


/**
 * Provides utility methods for rendering the menu frame and animations in the console UI.
 */
public class MenuFrame {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";
    public static final String RED_BOLD = "\u001B[1;31m";
    public static final String YELLOW_BOLD = "\u001B[1;33m";
    private static final String WHITE_BOLD = "\033[1;37m";

    private static final String TOP_LEFT = "╔";
    private static final String TOP_RIGHT = "╗";
    private static final String BOTTOM_LEFT = "╚";
    private static final String BOTTOM_RIGHT = "╝";
    private static final String HORIZONTAL = "═";
    private static final String VERTICAL = "║";
    private static final String T_DOWN = "╦";
    private static final String T_UP = "╩";

    private static final int WIDTH = 60;

    /**
     * Clears the console screen.
     */
    public static void clearScreen() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Prints the header of the menu with the given title and optional subtitle.
     *
     * @param title    the main title of the menu
     * @param subtitle the subtitle of the menu
     */
    public static void printHeader(String title, String subtitle) {
        clearScreen();
        printSeparator(TOP_LEFT, TOP_RIGHT);

        String centeredTitle = centerText(title, WIDTH - 2);
        System.out.println(RED_BOLD + VERTICAL + RESET + centeredTitle + RED_BOLD + VERTICAL + RESET);

        if (subtitle != null && !subtitle.isEmpty()) {
            printSeparator("╠", "╣");
            String centeredSub = centerText(subtitle, WIDTH - 2);
            System.out.println(RED + VERTICAL + RESET + centeredSub + RED + VERTICAL + RESET);
        }

        printSeparator("╠", "╣");
    }

    /**
     * Prints a menu item with the given index and label.
     *
     * @param index the index of the menu item
     * @param label the label of the menu item
     */
    public static void printMenuItem(int index, String label) {
        String itemText = String.format(" %d. %-50s", index, label);
        if (itemText.length() > WIDTH - 2) {
            itemText = itemText.substring(0, WIDTH - 2);
        } else {
            itemText = String.format("%-" + (WIDTH - 2) + "s", itemText);
        }
        System.out.println(RED + VERTICAL + RESET + WHITE + itemText + RED + VERTICAL + RESET);
    }

    /**
     * Prints a line of text within the menu frame.
     *
     * @param text the text to print
     */
    public static void printMenuLine(String text) {
        String line = String.format(" %-54s", text);
        if (line.length() > WIDTH - 2) {
            line = line.substring(0, WIDTH - 2);
        } else {
            line = String.format("%-" + (WIDTH - 2) + "s", line);
        }
        System.out.println(RED + VERTICAL + RESET + YELLOW + line + RED + VERTICAL + RESET);
    }

    /**
     * Prints the footer of the menu.
     */
    public static void printFooter() {
        printMenuItem(0, "Logout / Back");
        printSeparator(BOTTOM_LEFT, BOTTOM_RIGHT);
        System.out.print(YELLOW_BOLD + ">> Select Option: " + RESET);
    }

    /**
     * Displays a startup animation.
     */
    public static void animateMenu() {
        clearScreen();

        for (int i = 0; i < 5; i++) {  System.out.println(RED_BOLD + "INITIALIZING SYSTEM KERNEL...");}


        try {
            for (int i = 0; i < 15; i++) {
                for(int j=0; j<5; j++) {
                    System.out.print((Math.random() > 0.5 ? "1" : "0") + "x" + (int)(Math.random()*9) + "F ");
                }
                System.out.println(" [OK]");
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {}

        clearScreen();

        System.out.print(RED_BOLD + "\n   CONNECTING TO SECURE DATABASE: [");
        for (int i = 0; i < 30; i++) {
            System.out.print("=");
            try { Thread.sleep(30 + (int)(Math.random() * 50)); } catch (Exception e) {}
        }
        System.out.println("] 100%\n" + RESET);

        try { Thread.sleep(500); } catch (Exception e) {}
        clearScreen();

        drawBoxAnimation();
    }

    /**
     * Uses the designated left and right characters to print a horizontal separator line.
     *
     * @param left  the character for the left end of the separator
     * @param right the character for the right end of the separator
     */
    private static void printSeparator(String left, String right) {
        System.out.print(RED_BOLD + left);
        for (int i = 0; i < WIDTH - 2; i++) {
            System.out.print(HORIZONTAL);
        }
        System.out.println(right + RESET);
    }

    /**
     * Centers the given text within a specified width.
     *
     * @param text  the text to center
     * @param width the width to center within
     * @return the centered text string
     */
    private static String centerText(String text, int width) {

        String cleanText = text.replaceAll("\\u001B\\[[;\\d]*m", "");

        if (cleanText.length() >= width) {

            return text.substring(0, width);
        }

        int padding = (width - cleanText.length()) / 2;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(YELLOW_BOLD).append(text).append(RESET);

        int remaining = width - padding - cleanText.length();
        for (int i = 0; i < remaining; i++) sb.append(" ");

        return sb.toString();
    }

    /**
     * Encircles the title with a box animation.
     */
    private static void drawBoxAnimation() {
        try {

            System.out.print(RED_BOLD + TOP_LEFT);
            for (int i = 0; i < WIDTH - 2; i++) {
                System.out.print(HORIZONTAL);
                Thread.sleep(5);
            }
            System.out.println(TOP_RIGHT);

            System.out.print(VERTICAL);

            String title = " CONTACT MANAGEMENT SYSTEM v2.0 ";
            int padding = (WIDTH - 2 - title.length()) / 2;

            for(int i=0; i<padding; i++) System.out.print(" ");

            System.out.print(WHITE_BOLD);
            for (char c : title.toCharArray()) {
                System.out.print(c);
                Thread.sleep(30);
            }
            System.out.print(RED_BOLD);

            int remaining = WIDTH - 2 - padding - title.length();
            for(int i=0; i<remaining; i++) System.out.print(" ");

            System.out.println(VERTICAL);

            System.out.print(BOTTOM_LEFT);
            for (int i = 0; i < WIDTH - 2; i++) {
                System.out.print(HORIZONTAL);
                Thread.sleep(5);
            }
            System.out.println(BOTTOM_RIGHT + RESET);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
