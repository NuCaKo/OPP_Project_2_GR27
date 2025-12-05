package com.group27.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadingScreen {

    // ANSI Colors
    private static final String RESET = "\033[0m";
    private static final String CYAN = "\033[0;36m";
    private static final String CYAN_BOLD = "\033[1;36m";
    private static final String YELLOW_BOLD = "\033[1;33m";
    private static final String GREEN = "\033[0;32m";
    private static final String GREEN_BOLD = "\033[1;32m";
    private static final String WHITE = "\033[0;37m";
    private static final String WHITE_BOLD = "\033[1;37m";
    private static final String YELLOW = "\033[0;33m";
    private static final String BLUE = "\033[0;34m";
    private static final String BOLD = "\033[1m";
    private static final String RED = "\033[0;31m";
    private static final String RED_BOLD = "\033[1;31m";
    private static final String MAGENTA = "\033[0;35m";

    // Configuration
    private static final int WIDTH = 130;
    private static final int HEIGHT = 43;
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
    private static final int RAIN_AREA_HEIGHT = 32;
    private static final int RAIN_AREA_WIDTH = WIDTH - 4;

    private static boolean[] activeColumn;
    private static boolean[][] rainState;
    private static boolean rainInitialized = false;

    public static void show() {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + DURATION_MS;

        rainInitialized = false; // Reset rain
        List<String> logs = new ArrayList<>();
        logs.add(getTimeStamp(startTime) + " System Boot Initiated...");
        logs.add(getTimeStamp(startTime) + " Loading User Interface...");

        int frameCount = 0;
        int rabbitWidth = RABBIT_FRAME1[0].length();

        try {
            // Hide Cursor
            System.out.print("\033[?25l");
            // Clear Screen
            System.out.print("\033[H\033[2J");

            // --- MAIN ANIMATION LOOP ---
            while (System.currentTimeMillis() < endTime) {
                long elapsed = System.currentTimeMillis() - startTime;
                double progress = (double) elapsed / DURATION_MS;
                if (progress > 1.0) progress = 1.0;

                // Move Rabbit
                int startX = -rabbitWidth;
                int endX = WIDTH;
                int rabbitLeft = (int) (startX + (endX - startX) * progress);

                // Update Rain
                updateRain();

                // Update Logs
                if (RANDOM.nextInt(100) < 5) {
                    logs.add(getTimeStamp(startTime) + " " + generateFakeLog());
                    if (logs.size() > 3) logs.remove(0);
                }

                // Render Frame
                StringBuilder sb = new StringBuilder();
                sb.append("\033[H");

                renderFrameContent(sb, frameCount, rabbitLeft, logs, progress, -1);

                System.out.print(sb.toString());
                System.out.flush();

                Thread.sleep(FRAME_DELAY);
                frameCount++;
            }

            // --- EXIT ANIMATION (Vertical Collapse / CRT Off) ---
            int totalLines = 45;
            for (int mask = 0; mask < totalLines / 2 + 2; mask++) {
                StringBuilder sb = new StringBuilder();
                sb.append("\033[H");

                renderFrameContent(sb, frameCount, WIDTH, logs, 1.0, mask);

                System.out.print(sb.toString());
                System.out.flush();

                Thread.sleep(40);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print("\033[?25h");
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    private static void renderFrameContent(StringBuilder sb, int frameCount, int rabbitLeft, List<String> logs, double progress, int maskHeight) {
        int currentLine = 0;
        int rabbitWidth = RABBIT_FRAME1[0].length();

        // Pulse Effect for Borders (Changed to RED/YELLOW for harmony)
        String borderColor = WHITE;

        // --- TOP BORDER ---
        appendLine(sb, borderColor + "╔" + "═".repeat(WIDTH - 2) + "╗" + RESET, currentLine++, maskHeight);

        // --- HEADER ---
        String title = " ROLE-BASED CONTACT MANAGEMENT SYSTEM ";
        String subtitle = " CMPE 343 - FALL 2025 ";

        // Using YELLOW for text to match warmth
        String titleStr = BOLD + YELLOW + title + RESET;
        String subStr = BOLD + WHITE + subtitle + RESET;

        // centerText helper adds color, but let's just use it and rely on borders
        // Actually centerText hardcodes WHITE. Let's fix that or accept it.
        // User wants harmony. White is neutral. Let's leave text White/Yellow.

        appendLine(sb, borderColor + "║" + RESET + centerText(title, WIDTH - 2).replace(WHITE, YELLOW) + borderColor + "║" + RESET, currentLine++, maskHeight);
        appendLine(sb, borderColor + "║" + RESET + centerText(subtitle, WIDTH - 2) + borderColor + "║" + RESET, currentLine++, maskHeight);

        // Separator
        appendLine(sb, borderColor + "╠" + "═".repeat(WIDTH - 2) + "╣" + RESET, currentLine++, maskHeight);

        // --- RABBIT & RAIN AREA ---
        String[] currentRabbit = ((frameCount / 3) % 2 == 0) ? RABBIT_FRAME1 : RABBIT_FRAME2;

        for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
            StringBuilder lineSb = new StringBuilder();
            lineSb.append(borderColor).append("║ ").append(RESET);

            for (int c = 0; c < RAIN_AREA_WIDTH; c++) {
                char outChar = ' ';
                String color = RESET;

                // Check Rabbit
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
                    // Rabbit is Red/Yellow striped
                    if ((rc + frameCount) % 6 < 3) {
                        color = RED;
                    } else {
                        color = YELLOW;
                    }
                } else {
                    // Check Rain
                    if (c < RAIN_AREA_WIDTH && r < RAIN_AREA_HEIGHT &&
                            activeColumn != null && activeColumn[c] &&
                            rainState != null && rainState[r][c]) {

                        outChar = MATRIX_CHARS.charAt(RANDOM.nextInt(MATRIX_CHARS.length()));

                        boolean isHead = false;
                        if (r < RAIN_AREA_HEIGHT - 1) {
                            if (!rainState[r + 1][c]) isHead = true;
                        } else {
                            isHead = true;
                        }

                        // Fire Rain: White/Yellow head, Red body
                        if (isHead) color = WHITE_BOLD; // Hot core
                        else if (RANDOM.nextInt(10) > 8) color = YELLOW_BOLD; // Spark
                        else color = RED; // Cooling magma
                    }
                }

                if (color.equals(RESET)) lineSb.append(outChar);
                else lineSb.append(color).append(outChar).append(RESET);
            }
            lineSb.append(borderColor).append(" ║").append(RESET);
            appendLine(sb, lineSb.toString(), currentLine++, maskHeight);
        }

        // Separator
        appendLine(sb, borderColor + "╠" + "═".repeat(WIDTH - 2) + "╣" + RESET, currentLine++, maskHeight);

        // --- LOGS ---
        for(int i=0; i<3; i++) {
            StringBuilder lineSb = new StringBuilder();
            lineSb.append(borderColor).append("║ ").append(RESET);
            String log = (i < logs.size()) ? logs.get(i) : "";
            // Logs in Yellow/White
            lineSb.append(YELLOW).append(String.format("%-" + (WIDTH - 4) + "s", log)).append(RESET);
            lineSb.append(borderColor).append(" ║").append(RESET);
            appendLine(sb, lineSb.toString(), currentLine++, maskHeight);
        }

        // --- PROGRESS ---
        StringBuilder progSb = new StringBuilder();
        progSb.append(borderColor).append("║ ").append(RESET);
        int barWidth = WIDTH - 4 - 8;
        int filled = (int) (barWidth * progress);

        // Gradient: Red -> RedBold -> Yellow (Fire gradient)
        String barColor;
        if (progress < 0.3) barColor = RED;
        else if (progress < 0.7) barColor = RED_BOLD;
        else if (progress < 0.8) barColor = YELLOW_BOLD;
        else barColor = GREEN_BOLD;

        progSb.append(barColor);
        for(int i=0; i<filled; i++) progSb.append("▓");
        progSb.append(RESET);

        for(int i=filled; i<barWidth; i++) progSb.append("░");

        progSb.append(RESET);
        progSb.append(String.format(" %3d%% ", (int)(progress * 100)));
        progSb.append(borderColor).append("║").append(RESET);
        appendLine(sb, progSb.toString(), currentLine++, maskHeight);

        // --- BOTTOM ---
        appendLine(sb, borderColor + "╚" + "═".repeat(WIDTH - 2) + "╝" + RESET, currentLine++, maskHeight);
    }

    private static void appendLine(StringBuilder sb, String content, int lineIndex, int maskHeight) {
        int totalHeight = 42;
        boolean isMasked = false;
        if (maskHeight >= 0) {
            if (lineIndex < maskHeight || lineIndex >= totalHeight - maskHeight) {
                isMasked = true;
            }
        }
        if (isMasked) {
            for(int i=0; i<WIDTH; i++) sb.append(" ");
            sb.append("\n");
        } else {
            sb.append(content).append("\n");
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
            for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
                int from = (r - 1 + RAIN_AREA_HEIGHT) % RAIN_AREA_HEIGHT;
                newCol[r] = rainState[from][c];
            }
            for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
                if (newCol[r]) {
                    if (RANDOM.nextDouble() < dieProb) newCol[r] = false;
                } else {
                    if (RANDOM.nextDouble() < birthProb) newCol[r] = true;
                }
            }
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
        String[] actions = {"Compiling", "Optimizing", "Injecting", "Parsing", "Linking", "Hashing"};
        String[] targets = {"RabbitAI", "NeuralMesh", "SecureSocket", "DataStream", "LogicCore", "CryptoWallet"};
        return actions[RANDOM.nextInt(actions.length)] + " " + targets[RANDOM.nextInt(targets.length)] + "...";
    }

    private static String getTimeStamp(long start) {
        long diff = System.currentTimeMillis() - start;
        long sec = diff / 1000;
        long ms = (diff % 1000) / 10;
        return String.format("[%02d:%02d]", sec, ms);
    }
}
