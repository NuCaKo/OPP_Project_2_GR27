package com.group27.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadingScreen {

    // ANSI Colors
    private static final String RESET = "\033[0m";
    private static final String CYAN = "\033[0;36m";
    private static final String GREEN = "\033[0;32m";
    private static final String WHITE = "\033[0;37m";
    private static final String YELLOW = "\033[0;33m";
    private static final String BLUE = "\033[0;34m";
    private static final String BOLD = "\033[1m";
    private static final String RED = "\033[0;31m";

    // Configuration
    private static final int WIDTH = 130;
    private static final int HEIGHT = 43; // Adjusted for taller rabbit
    private static final int DURATION_MS = 15000;
    private static final int FPS = 15;
    private static final long FRAME_DELAY = 1000 / FPS;

    // Rabbit Frames (User Provided)
    private static final String[] RABBIT_FRAME1 = {
            "%%%%%%%#%%%%%%%%%#%%#%%%%%%%#%%%%%#%%%%%#%%%%%%#%%%%#%#%%%#%%%%%%%%%%##%%%%#%%%%%#%%%#%%%#%#%%%%%%%%",
            "%%%%%%%#%%%%%%#%%#%%#%%%%%%%#%%%%%%%%%%%#%%%%%%#%#%%%%#%%%%%%%%%%%%%###%%%%#%%%#%%%%%#%%%#%#%%%%%%%%",
            "%%%%%%%#%%%%%%#%%%%%#%%%%%%%#%%%%%%%%%%%#%%%%%%#%%%%%%%%%%%%%%%%%%%%##%%%%%#%%%#%%%%%%%%%#%#%%%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%###%%%%#%%%%%%%%%%%%%%%%%%%%%%%%",
            "%%%%%%%%%%%%#%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%",
            "%%%%%%%%%%%%#%#%%%%%%%%%%%%%#%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%#%#%#%%#%%%%%%%%%%#%%%#%%%#%%%%%%",
            "%%%%%%%%%%%%#%#%%%%%#%%%%%%%#%%#%%%%%%%%%%%%%%%%%%%%%%%%#%%#%%#%%%%%%#%%%%#%#%%%%#%%%#%%%#%%%%#%%%%%",
            "%%%%%%%%%%%%#%#%%%%%#%%%%%%%#%%#%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%#%#%%#%#%%%%#%%%#%%%#%%%#%%%%%%",
            "%%%%%%#%%%%%#%%%%%%%%%%%%%%%#%%#%%%%%%%%%%%%%%%#%%%%%%%#%%%%%%#%%%%%%#%%%%%%##%%%#%%%%%%%%%%%%%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%###%%%%%%%%#%%%%#%%%%%%%%%%%%%%%%%%%%%%%",
            "%%%%#%#%%%%%#%%%%%%%%%%%%%%%#%%#%%%%#%%%%%%%%%%#%%%%%%%%%+++++*%%%%%#%%%%%%%##%%%%%%%%%%%%#%%#%%%%%%",
            "%%#%#%#%%%%%#%%%%%%%#%%%%%%%#%%%#%%%%%#+*%%%%%%#%%%%%#%%#+=++=+++++%##%%%%%%%#%%%%%%%%%%%%#%%##%%%%%",
            "%%#%#%#%%%%%#%%%%%%%%%%%#%%%#%%%#%%#%%%%%**#%%#*%#%%%#%%*++**++++**#%%%%%%%#%%%%%%%%%%%%%%#%%%#%%%%%",
            "%%#%#%#%%%%%#%%%%%%%%%%%#%%%#%%%#%%##%%%%%%*==+++++=+++++=+=+++++++++++%%%%%%%%%%%%%%%%%%%#%%%#%%%%%",
            "%%#%%%%%%%%%#%%%%%%%#%%%#%%%#%%%#%%%%%%%%%#***************************#%%%%%%%%%%%%%%%%%%%%%#%%#%%%%",
            "%%%%%%##%%%%#%%%%%%%%%%%#%%%#%%%#%%%%%%%%%#****************#****%%**%%%%%%%%%%%%%%%%%%%%###%#%%%%%%%",
            "%%%%%%%%%%%%#%%%%%%%#%%%#%%%#%%%#%%#%%%%%#++++++++=++++++=+=+++++%%%%%%%%%%%%%%%%#%%%%#####%%%%#%#%%",
            "%#%%%%%%%%%%#%%%%%%%%%%%#%%%#%%%#%%#%%%%%#++*++++++%%%%%*++++++*%%%%%%%%%%%%%%%%%#%%%%%%%##%%%%#%#%%",
            "%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%#%%#%%%%%++++=+==%%%%#%%%%=+++=%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%#%%",
            "%%%%%%%%%%%%%%%%%%%%#%%%#%%%%%%%%%%%%%%%#**%%**%%%%%%#%%%%++%%*+%%%%#%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%",
            "%%%%%%%%%%%%%%%%%#%%%%%%#%%%%%%%#%%%%%%%#%%%%%%%%%%%%##%#%%%%%%%##%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%#%%",
            "%%%%%%%%%%%%%%%%%#%%#%%%#%%%%%%%#%%%%%%%%%%%%%%%%%%%%##%#%#%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%##%%%%%%%%%",
            "%%%%%%%#%%%%%%%%%#%%%%%%#%%%%%%%#%%%%%%%%%%%%%%%#%%%%##%%%#%%#%%##%%%%%%%%%%%%%%%%%%%%%%%#%%%#%%#%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%#%%%%%%%%%%%%%%%#%%%%##%%%%%%#%%##%%%%%%%%%#%%%%%%%%%%%%%%%%%#%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%#%%%#%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%",
            "%%%%%%%#%%%%%%#%%%%%%%%%#%%%%%%%%%%%%%%%#%%#%%%%%%%%%%#%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%",
            "%%%%%%%#%%%%%%#%%%%%%%%%#%%%%%%%%%%%%%%%#%%#%#%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%#%%%",
            "%%%%%%%#%%%%%%#%%#%%#%%%%%%%%%%%%%%%%%%%#%##%#%%%%%%%%#%#%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%#%%#%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
    };

    private static final String[] RABBIT_FRAME2 = {
            "%%%%%%%#%%%%%%%%%#%%#%%%%%%%#%%%%%#%%%%%#%%%%%%#%%%%#%#%%%#%%%%%%%%%%##%%%%#%%%%%#%%%#%%%#%#%%%%%%%%",
            "%%%%%%%#%%%%%%#%%#%%#%%%%%%%#%%%%%%%%%%%#%%%%%%#%#%%%%#%%%%%%%%%%%%%###%%%%#%%%#%%%%%#%%%#%#%%%%%%%%",
            "%%%%%%%#%%%%%%#%%%%%#%%%%%%%#%%%%%%%%%%%#%%%%%%#%%%%%%%%%%%%%%%%%%%%##%%%%%#%%%#%%%%%%%%%#%#%%%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%###%%%%#%%%%%%%%%%%%%%%%%%%%%%%%",
            "%%%%%%%%%%%%#%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%",
            "%%%%%%%%%%%%#%#%%%%%%%%%%%%%#%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%#%#%#%%#%%%%%%%%%%#%%%#%%%#%%%%%%",
            "%%%%%%%%%%%%#%#%%%%%#%%%%%%%#%%#%%%%%%%%%%%%%%%%%%%%%%%%#%%#%%#%%%%%%#%%%%#%#%%%%#%%%#%%%#%%%%#%%%%%",
            "%%%%%%%%%%%%#%#%%%%%#%%%%%%%#%%#%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%#%#%%#%#%%%%#%%%#%%%#%%%#%%%%%%",
            "%%%%%%#%%%%%#%%%%%%%%%%%%%%%#%%#%%%%%%%%%%%%%%%#%%%%%%%#%%%%%%#%%%%%%#%%%%%%##%%%#%%%%%%%%%%%%%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%###%%%%%%%%#%%%%#%%%%%%%%%%%%%%%%%%%%%%%",
            "%%%%#%#%%%%%#%%%%%%%%%%%%%%%#%%#%%%%#%%%-+-%%%%#%%%%%%%%%+++++*%%%%%#%%%%%%%##%%%%%%%%%%%%#%%#%%%%%%",
            "%%#%#%#%%%%%#%%%%%%%#%%%%%%%#%%%#%%%%%%%%+++%%%#%%%%%#%%#+=++=++--++##%%%%%%%#%%%%%%%%%%%%#%%##%%%%%",
            "%%#%#%#%%%%%#%%%%%%%%%%%#%%%#%%%#%%#%%%%%%%++%#*%#%%%#%%*++**++++****#%%%%%%%#%%%%%%%%%%%%#%%%#%%%%%",
            "%%#%#%#%%%%%#%%%%%%%%%%%#%%%#%%%#%%##%%%%%%*==+++++=+++++=+=++*++++++++%%%%%%%%%%%%%%%%%%%#%%%#%%%%%",
            "%%#%%%%%%%%%#%%%%%%%#%%%#%%%#%%%#%%%%%%%%%#***************************#%%%%%%%%%%%%%%%%%%%%%#%%#%%%%",
            "%%%%%%##%%%%#%%%%%%%%%%%#%%%#%%%#%%%%%%%%%#****************#*****%**%%%%%%%%%%%%%%%%%%%%###%#%%%%%%%",
            "%%%%%%%%%%%%#%%%%%%%#%%%#%%%#%%%#%%#%%+++***--*+++=++++++=+=++++++%%%%%%%%%%%%%%%#%%%%#####%%%%#%#%%",
            "%#%%%%%%%%%%#%%%%%%%%%%%#%%%#%%%#%%++--++%%%%%%%%%%%%%%%%%%%%%++++-++%%%%%%%%%%#%%%%%%%##%%%%#%#%%%%",
            "%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%#+---++%%%%%%%%%%%%%%#%%%%%%%%%%%++--++%%%%%%%%%%%%%%%%%%%#%%%%%%%#%",
            "%%%%%%%%%%%%%%%%%%%%#%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%",
            "%%%%%%%%%%%%%%%%%#%%%%%%#%%%%%%%#%%%%%%%#%%%%%%%%%%%%##%#%%%%%%%##%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%#%%",
            "%%%%%%%%%%%%%%%%%#%%#%%%#%%%%%%%#%%%%%%%%%%%%%%%%%%%%##%#%#%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%##%%%%%%%%%",
            "%%%%%%%#%%%%%%%%%#%%%%%%#%%%%%%%#%%%%%%%%%%%%%%%#%%%%##%%%#%%#%%##%%%%%%%%%%%%%%%%%%%%%%%#%%%#%%#%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%#%%%%%%%%%%%%%%%#%%%%##%%%%%%#%%##%%%%%%%%%#%%%%%%%%%%%%%%%%%#%%%%%%",
            "%%%%%%%%%%%%%%%%%%%%#%%%#%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%",
            "%%%%%%%#%%%%%%#%%%%%%%%%#%%%%%%%%%%%%%%%#%%#%%%%%%%%%%#%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%",
            "%%%%%%%#%%%%%%#%%%%%%%%%#%%%%%%%%%%%%%%%#%%#%#%%%%%%%%#%%%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%#%%%",
            "%%%%%%%#%%%%%%#%%#%%#%%%%%%%%%%%%%%%%%%%#%##%#%%%%%%%%#%#%%%%%%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%#%%#%%%",
            "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
    };

    private static final String MATRIX_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    // Rain Logic State
    private static final int RAIN_AREA_HEIGHT = 32; // Just larger than rabbit
    private static final int RAIN_AREA_WIDTH = WIDTH - 4; // Inside borders

    private static boolean[] activeColumn;
    private static boolean[][] rainState;
    private static boolean rainInitialized = false;

    public static void show() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + DURATION_MS;

        rainInitialized = false; // Reset rain
        List<String> logs = new ArrayList<>();
        logs.add("System Boot Initiated...");
        logs.add("Loading User Interface...");

        int frameCount = 0;
        int rabbitWidth = RABBIT_FRAME1[0].length();

        try {
            // Hide Cursor
            System.out.print("\033[?25l");
            // Clear Screen
            System.out.print("\033[H\033[2J");

            while (System.currentTimeMillis() < endTime) {
                long elapsed = System.currentTimeMillis() - startTime;
                double progress = (double) elapsed / DURATION_MS;
                if (progress > 1.0) progress = 1.0;

                // Move Rabbit
                // Start off-screen left (-100), end off-screen right (WIDTH)
                int startX = -rabbitWidth;
                int endX = WIDTH;
                int rabbitLeft = (int) (startX + (endX - startX) * progress);

                // Update Rain
                updateRain();

                // Build Frame
                StringBuilder sb = new StringBuilder();
                sb.append("\033[H"); // Home cursor

                // --- TOP BORDER ---
                sb.append(CYAN).append("╔");
                for (int i = 0; i < WIDTH - 2; i++) sb.append("═");
                sb.append("╗").append(RESET).append("\n");

                // --- HEADER ---
                String title = " ROLE-BASED CONTACT MANAGEMENT SYSTEM ";
                String subtitle = " CMPE 343 - FALL 2025 ";

                sb.append(CYAN).append("║").append(RESET);
                sb.append(centerText(title, WIDTH - 2));
                sb.append(CYAN).append("║").append(RESET).append("\n");

                sb.append(CYAN).append("║").append(RESET);
                sb.append(centerText(subtitle, WIDTH - 2));
                sb.append(CYAN).append("║").append(RESET).append("\n");

                // Separator
                sb.append(CYAN).append("╠");
                for (int i = 0; i < WIDTH - 2; i++) sb.append("═");
                sb.append("╣").append(RESET).append("\n");

                // --- RABBIT & RAIN AREA ---
                String[] currentRabbit = ((frameCount / 3) % 2 == 0) ? RABBIT_FRAME1 : RABBIT_FRAME2;

                for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
                    sb.append(CYAN).append("║ ").append(RESET); // Left border pad

                    for (int c = 0; c < RAIN_AREA_WIDTH; c++) {
                        char outChar = ' ';
                        String color = RESET;

                        // Check Rabbit
                        // Center rabbit vertically in the rain area
                        int rabbitTop = (RAIN_AREA_HEIGHT - 30) / 2;
                        int rr = r - rabbitTop;
                        int rc = c - rabbitLeft;
                        boolean insideRabbit = false;
                        char rabbitCh = ' ';

                        if (rr >= 0 && rr < 30 && rc >= 0 && rc < rabbitWidth) {
                            rabbitCh = currentRabbit[rr].charAt(rc);
                            if (rabbitCh != '%' && rabbitCh != '#' && rabbitCh != ' ') {
                                insideRabbit = true;
                            }
                        }

                        if (insideRabbit) {
                            outChar = rabbitCh;
                            color = WHITE;
                        } else {
                            // Check Rain
                            if (c < RAIN_AREA_WIDTH && r < RAIN_AREA_HEIGHT &&
                                    activeColumn != null && activeColumn[c] &&
                                    rainState != null && rainState[r][c]) {
                                outChar = MATRIX_CHARS.charAt(RANDOM.nextInt(MATRIX_CHARS.length()));
                                color = GREEN;
                            }
                        }

                        if (color.equals(RESET)) sb.append(outChar);
                        else sb.append(color).append(outChar).append(RESET);
                    }
                    sb.append(CYAN).append(" ║").append(RESET).append("\n");
                }

                // Separator
                sb.append(CYAN).append("╠");
                for (int i = 0; i < WIDTH - 2; i++) sb.append("═");
                sb.append("╣").append(RESET).append("\n");

                // --- LOGS ---
                if (RANDOM.nextInt(100) < 5) {
                    logs.add(generateFakeLog());
                    if (logs.size() > 3) logs.remove(0);
                }
                for(int i=0; i<3; i++) {
                    sb.append(CYAN).append("║ ").append(RESET);
                    String log = (i < logs.size()) ? logs.get(i) : "";
                    sb.append(YELLOW).append(String.format("%-" + (WIDTH - 4) + "s", log)).append(RESET);
                    sb.append(CYAN).append(" ║").append(RESET).append("\n");
                }

                // --- PROGRESS ---
                sb.append(CYAN).append("║ ").append(RESET);
                int barWidth = WIDTH - 4 - 8;
                int filled = (int) (barWidth * progress);
                sb.append(GREEN);
                for(int i=0; i<filled; i++) sb.append("█");
                sb.append(RED);
                for(int i=filled; i<barWidth; i++) sb.append("░");
                sb.append(RESET);
                sb.append(String.format(" %3d%% ", (int)(progress * 100)));
                sb.append(CYAN).append("║").append(RESET).append("\n");

                // --- BOTTOM ---
                sb.append(CYAN).append("╚");
                for (int i = 0; i < WIDTH - 2; i++) sb.append("═");
                sb.append("╝").append(RESET).append("\n");

                System.out.print(sb.toString());
                System.out.flush();

                Thread.sleep(FRAME_DELAY);
                frameCount++;
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print("\033[?25h");
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static void initRain() {
        activeColumn = new boolean[RAIN_AREA_WIDTH];
        rainState = new boolean[RAIN_AREA_HEIGHT][RAIN_AREA_WIDTH];

        for (int c = 0; c < RAIN_AREA_WIDTH; c++) {
            activeColumn[c] = RANDOM.nextDouble() < 0.30;
        }
        for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
            for (int c = 0; c < RAIN_AREA_WIDTH; c++) {
                if (activeColumn[c] && RANDOM.nextDouble() < 0.25) {
                    rainState[r][c] = true;
                } else {
                    rainState[r][c] = false;
                }
            }
        }
        rainInitialized = true;
    }

    private static void updateRain() {
        if (!rainInitialized) initRain();

        double dieProb = 0.10;
        double birthProb = 0.12;

        for (int c = 0; c < RAIN_AREA_WIDTH; c++) {
            if (!activeColumn[c]) continue;

            boolean[] newCol = new boolean[RAIN_AREA_HEIGHT];
            // Shift down
            for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
                int from = (r - 1 + RAIN_AREA_HEIGHT) % RAIN_AREA_HEIGHT;
                newCol[r] = rainState[from][c];
            }
            // Birth/Death
            for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
                if (newCol[r]) {
                    if (RANDOM.nextDouble() < dieProb) newCol[r] = false;
                } else {
                    if (RANDOM.nextDouble() < birthProb) newCol[r] = true;
                }
            }
            // Update
            for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
                rainState[r][c] = newCol[r];
            }
        }
    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<padding; i++) sb.append(" ");
        sb.append(BOLD).append(WHITE).append(text).append(RESET);
        for(int i=0; i<width - padding - text.length(); i++) sb.append(" ");
        return sb.toString();
    }

    private static String generateFakeLog() {
        String[] actions = {"Compiling", "Optimizing", "Injecting", "Parsing", "Linking"};
        String[] targets = {"RabbitAI", "NeuralMesh", "SecureSocket", "DataStream", "LogicCore"};
        return "> " + actions[RANDOM.nextInt(actions.length)] + " " + targets[RANDOM.nextInt(targets.length)] + "...";
    }
}
