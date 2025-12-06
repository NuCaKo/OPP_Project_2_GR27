package com.group27;

import com.group27.model.User;
import com.group27.service.AuthService;
import com.group27.ui.*;
import java.util.Scanner;

/**
 * The main entry point for the Contact Management System application.
 */
public class Main {
    /**
     * The main method that starts the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        LoadingScreen.show();

        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();

        while (true) {
            drawLoginHeader();

            System.out.print(MenuFrame.YELLOW_BOLD + "   USERNAME : " + MenuFrame.RESET);
            String username = scanner.nextLine();

            System.out.print(MenuFrame.YELLOW_BOLD + "   PASSWORD : " + MenuFrame.RESET);
            String password = scanner.nextLine();

            System.out.println(MenuFrame.RED_BOLD + "\n   Authenticating..." + MenuFrame.RESET);
            try { Thread.sleep(500); } catch (Exception e) {}

            User currentUser = authService.login(username, password);

            if (currentUser != null) {
                Menu menu = MenuSelector.getMenu(currentUser);
                menu.show();
            }

            System.out.println("\n" + MenuFrame.RED_BOLD + "╔════════════════════════════════════════════════════════════╗" + MenuFrame.RESET);
            System.out.println(MenuFrame.RED_BOLD + "║" + MenuFrame.WHITE + "  [Y] SHUTDOWN SYSTEM      [ENTER] NEW LOGIN SESSION        " + MenuFrame.RED_BOLD + "║" + MenuFrame.RESET);
            System.out.println(MenuFrame.RED_BOLD + "╚════════════════════════════════════════════════════════════╝" + MenuFrame.RESET);
            System.out.print(MenuFrame.YELLOW_BOLD + ">> Command: " + MenuFrame.RESET);

            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("y")) {
                TerminationScreen.show();
                break;
            }
        }
    }

    /**
     * Draws the stylish login header using ASCII box art and ANSI colors.
     */
    private static void drawLoginHeader() {
        MenuFrame.clearScreen();
        System.out.println(MenuFrame.RED_BOLD + "╔════════════════════════════════════════════════════════════╗");
        System.out.println("║" + MenuFrame.YELLOW_BOLD + "        CONTACT MANAGEMENT SYSTEM - SECURE LOGIN            " + MenuFrame.RED_BOLD + "║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║" + MenuFrame.WHITE + "                                                            " + MenuFrame.RED_BOLD + "║");
        System.out.println("║" + MenuFrame.WHITE + "   PLEASE ENTER YOUR CREDENTIALS TO ACCESS THE MAINFRAME.   " + MenuFrame.RED_BOLD + "║");
        System.out.println("║" + MenuFrame.WHITE + "                                                            " + MenuFrame.RED_BOLD + "║");
        System.out.println("╚════════════════════════════════════════════════════════════╝" + MenuFrame.RESET);
        System.out.println();
    }
}
