package com.group27;

import com.group27.model.User;
import com.group27.service.AuthService;
import com.group27.ui.Menu;
import com.group27.ui.MenuSelector;
import com.group27.ui.Animation;
import com.group27.util.PasswordUtil;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Animation.showStartupAnimation();

        Scanner scanner = new Scanner(System.in);
        AuthService authService = new AuthService();

        System.out.println("******************************************");
        System.out.println("* CONTACT MANAGEMENT SYSTEM (GRUP 27)    *");
        System.out.println("******************************************");


        String hashedValue = PasswordUtil.hashPassword("tt");
        System.out.println("Hashed value of 'tt': " + hashedValue);

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

            System.out.print("Programdan çıkılsın mı? (y/n): ");
            String exit = scanner.nextLine();
            if (exit.equalsIgnoreCase("y")) {
                Animation.showShutdownAnimation();
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}