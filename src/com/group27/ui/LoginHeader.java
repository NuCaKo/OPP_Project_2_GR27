package com.group27.ui;

public class LoginHeader {

    // --------- RENK KODLARI ---------
    private static class Color {
        static final String RESET   = "\u001B[0m";
        static final String CYAN    = "\u001B[36m";
        static final String MAGENTA = "\u001B[35m";
        static final String GREEN   = "\u001B[32m";
    }

    // Header’ı ortalamak için genişlik
    private static final int CONSOLE_WIDTH = 70;

    // Dışarıdan genelde bunu çağıracağız
    public static void show() {
        printMenuHeader("LOGIN");
    }

    // İster başka menüler için de kullan
    public static void printMenuHeader(String title) {
        clearScreen();

        String border = "<" + "=".repeat(CONSOLE_WIDTH - 2) + ">";

        // Üst çizgi
        System.out.println(Color.CYAN + border + Color.RESET);

        // Ortalanmış satırlar
        printCentered("ROLE-BASED CONTACT MANAGEMENT SYSTEM", Color.CYAN);
        printCentered("CMPE 343 - FALL 2025", Color.CYAN);
        printCentered("Kadir Has University", Color.CYAN);

        // Alt çizgi
        System.out.println(Color.CYAN + border + Color.RESET);
        System.out.println();

        // Ortasında LOGIN başlığı
        printCentered(title, Color.MAGENTA);
        System.out.println();
    }

    // USER NAME / PASS için kayarak gelen prompt
    public static void printSlidingPrompt(String label) {
        String text = label + " ";

        System.out.println(); // prompt’tan önce bir satır boşluk

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            System.out.print(Color.GREEN + ch + Color.RESET);
            sleep(25); // çok yavaşsa 15–20 yapabilirsin
        }
        // burada println yok, böylece nextLine aynı satırdan okumaya devam eder
    }

    // ---------- YARDIMCI METODLAR ----------
    private static void printCentered(String text, String color) {
        int pad = Math.max(0, (CONSOLE_WIDTH - text.length()) / 2);
        String spaces = " ".repeat(pad);
        System.out.println(spaces + color + text + Color.RESET);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
