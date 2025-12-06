package com.group27;

import com.group27.model.User;
import com.group27.service.AuthService;
import com.group27.ui.*;
import com.group27.util.PasswordUtil;


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

        System.out.println("******************************************");
        System.out.println("* CONTACT MANAGEMENT SYSTEM (GROUP 27)    *");
        System.out.println("******************************************");



        while (true) {
            System.out.print("\nUSER NAME: ");
            String username = scanner.nextLine();
            System.out.print("PASS: ");
            String password = scanner.nextLine();

            User currentUser = authService.login(username, password);

            if (currentUser != null) {

                Menu menu = MenuSelector.getMenu(currentUser);
                menu.show();
            }

            System.out.print("Press [y] to end the program, press [any key] to continue): ");
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("y")) {
                TerminationScreen.show();
                break;
            }
        }
    }
}