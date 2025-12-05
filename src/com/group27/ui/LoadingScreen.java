package com.group27.ui;

import java.util.Random;

public class LoadingScreen {

    // ANSI Colors and Styles
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String GREEN = "\033[0;32m";
    private static final String YELLOW = "\033[0;33m";
    private static final String BLUE = "\033[0;34m";
    private static final String MAGENTA = "\033[0;35m";
    private static final String CYAN = "\033[0;36m";
    private static final String WHITE_BOLD = "\033[1;37m";
    private static final String BOLD = "\033[1m";

    public static void show() {
        try {
            clearScreen();

            // Phase 1: Boot Sequence
            simulateBootSequence();

            // Phase 2: Data Matrix / Security Check
            simulateSecurityScan();

            clearScreen();

            // Phase 3: ASCII Logo Animation
            showLogo();

            // Phase 4: Final Progress Bar
            showProgressBar();

            System.out.println(GREEN + "\n\n\t\t>> SYSTEM INITIALIZED AND READY <<" + RESET);
            Thread.sleep(1500);

            clearScreen();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void clearScreen() {
        // ANSI escape code to clear screen and move cursor to top-left
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void simulateBootSequence() throws InterruptedException {
        String[] modules = {
            "CORE_KERNEL", "MEMORY_MGR", "FS_DRIVER", "NET_STACK",
            "CRYPT_ENGINE", "UI_RENDERER", "CONTACT_DB_LINK", "AUTH_SERVICE"
        };

        System.out.println(WHITE_BOLD + "INITIATING BOOT SEQUENCE..." + RESET + "\n");
        Random random = new Random();

        for (String module : modules) {
            System.out.print(CYAN + "[INIT] " + RESET + "Loading " + module + "...");
            System.out.flush();
            int delay = random.nextInt(250) + 50;
            Thread.sleep(delay);

            // Randomly simulate a slight "hiccup" or check
            if (random.nextInt(10) > 8) {
                System.out.print(YELLOW + " [VERIFYING]" + RESET);
                System.out.flush();
                Thread.sleep(200);
            }

            System.out.println(GREEN + " [OK]" + RESET);
        }
        Thread.sleep(500);
    }

    private static void simulateSecurityScan() throws InterruptedException {
        System.out.println("\n" + YELLOW + "PERFORMING INTEGRITY CHECK & SECURITY SCAN..." + RESET);
        Random random = new Random();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()";

        // Matrix-like fast scrolling lines
        for (int i = 0; i < 20; i++) {
            StringBuilder line = new StringBuilder();
            line.append(MAGENTA + "[SCAN] " + RESET);
            for (int j = 0; j < 60; j++) {
                line.append(chars.charAt(random.nextInt(chars.length())));
                if (random.nextBoolean()) line.append(" ");
            }
            System.out.println(line.toString());
            Thread.sleep(30);
        }

        System.out.println(GREEN + "[SECURE] Integrity verified. No threats detected." + RESET);
        Thread.sleep(600);
    }

    private static void showLogo() throws InterruptedException {
        // Logo: "CMS" + "GROUP 27" stylized
        // Centered roughly for 80 columns
        String[] logoLines = {
            "   ______  __  __  _____      ______   ____     ___    __  __  ____   ___   __  ",
            "  / ____/ /  |/  / / ___/     / ____/  / __ \\   /   |  / / / / / __ \\ |__ \\ /_  |",
            " / /     / /|_/ /  \\__ \\     / / __   / /_/ /  / /| | / / / / / /_/ / __/ /  / / ",
            "/ /___  / /  / /  ___/ /    / /_/ /  / _, _/  / ___ |/ /_/ / / ____/ / __/  / /  ",
            "\\____/ /_/  /_/  /____/     \\____/  /_/ |_|  /_/  |_|\\____/ /_/     /____/ /_/   ",
            "                                                                                 "
        };
        // The above says "CMS GROUP 27" in Big font roughly.
        // Actually that's "CMS GROU P 27" let's just stick to a clean "CONTACT MANAGEMENT"
        // Let's use the one generated below for "CMS GROUP 27"

        String[] logo = {
            BLUE + "   ______   __  __   _____        " + MAGENTA + "  ______   ____     ___    __  __   ____      ___     _____ " + RESET,
            BLUE + "  / ____/  /  |/  / / ___/        " + MAGENTA + " / ____/  / __ \\   /   |  / / / /  / __ \\    |__ \\   /__  / " + RESET,
            BLUE + " / /      / /|_/ /  \\__ \\   ____  " + MAGENTA + "/ / __   / /_/ /  / /| | / / / /  / /_/ /    __/ /     / /  " + RESET,
            BLUE + "/ /___   / /  / /  ___/ /  /___/  " + MAGENTA + "/ /_/ /  / _, _/  / ___ |/ /_/ /  / ____/    / __/     / /   " + RESET,
            BLUE + "\\____/  /_/  /_/  /____/          " + MAGENTA + "\\____/  /_/ |_|  /_/  |_|\\____/  /_/        /____/    /_/    " + RESET
        };

        System.out.println("\n\n");
        for (String line : logo) {
            System.out.println("  " + line);
            Thread.sleep(100); // Draw line by line
        }

        System.out.println("\n" + WHITE_BOLD + "\t\t    CONTACT MANAGEMENT SYSTEM" + RESET);
        System.out.println(CYAN + "\t\t\t   Enterprise Edition" + RESET);
        System.out.println("\n");
    }

    private static void showProgressBar() throws InterruptedException {
        int width = 50;
        System.out.print("Loading: [");
        for(int i=0; i<width; i++) System.out.print(" ");
        System.out.print("]");
        System.out.flush();

        System.out.print("\rLoading: [");
        System.out.flush();

        Random random = new Random();

        for (int i = 0; i < width; i++) {
            System.out.print(GREEN + "=" + RESET);
            System.out.flush();

            // Variable speed
            int sleepTime = 20 + random.nextInt(50);
            if (i > 30) sleepTime += 30; // Slow down near the end
            if (i > 45) sleepTime += 100; // Suspense

            Thread.sleep(sleepTime);
        }
        System.out.println("] 100%");
    }
}
