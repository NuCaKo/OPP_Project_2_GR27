package com.group27.ui;

import java.util.Random;

public class Animation {

    // ---------- RENKLER ----------
    static class Color {
        static final String RESET  = "\u001B[0m";
        static final String GREEN  = "\u001B[32m";
        static final String WHITE  = "\u001B[37m";
        static final String CYAN   = "\u001B[36m";
    }

    private static final String MATRIX_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    // '%' = tamamen boş arka plan
    // '#' = artık SADECE tasarım için kullandığın marker;
    //       ekranda ASLA gösterilmeyecek, harfe de çevrilmeyecek.
    // diğer tüm karakterler = tavşan dokusu
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

    // İkinci frame – koşma efekti için
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

    private static final int ART_HEIGHT = RABBIT_FRAME1.length;
    private static final int ART_WIDTH  = RABBIT_FRAME1[0].length();

    // Tavşanın kayacağı alan
    private static final int CANVAS_WIDTH  = 110;
    private static final int CANVAS_HEIGHT = ART_HEIGHT;

    // Yağmur durumu
    private static final boolean[] activeColumn = new boolean[CANVAS_WIDTH];
    private static final boolean[][] rainState  = new boolean[CANVAS_HEIGHT][CANVAS_WIDTH];
    private static boolean rainInitialized = false;

    // ---------- AÇILIŞ ANİMASYONU ----------
    public static void showStartupAnimation() {
        int totalFrames  = 50;
        int startOffset  = -ART_WIDTH;
        int stepPerFrame = 2;

        for (int frame = 0; frame < totalFrames; frame++) {
            int offset = startOffset + frame * stepPerFrame;

            clearScreen();
            printHeader();

            // 2 frame arasında gidip gelerek koşma efekti
            String[] currentArt = ((frame / 2) % 2 == 0) ? RABBIT_FRAME1 : RABBIT_FRAME2;

            drawFrameWithRainAndRabbit(currentArt, offset);
            sleep(90);
        }
    }

    // ---------- KAPANIŞ ANİMASYONU ----------
    public static void showShutdownAnimation() {
        String base = "Shutting down";
        for (int i = 1; i <= 3; i++) {
            System.out.print("\r" + base + ".".repeat(i));
            try { Thread.sleep(500); } catch (Exception ignored) {}
        }
        System.out.println();
    }


    // ---------- HEADER ----------
    private static void printHeader() {
        System.out.println(Color.CYAN + "<======================================>" + Color.RESET);
        System.out.println(Color.CYAN + "   ROLE-BASED CONTACT MANAGEMENT SYSTEM " + Color.RESET);
        System.out.println(Color.CYAN + "<======================================>" + Color.RESET);
        System.out.println("           CMPE 343 - FALL 2025");
        System.out.println("           Kadir Has University");
        System.out.println();
        System.out.println();
    }

    // ---------- YAĞMURU BAŞLAT ----------
    private static void initRain() {
        // Bazı sütunlar sonsuza kadar boş kalsın: %30 aktif, %70 tamamen siyah
        for (int c = 0; c < CANVAS_WIDTH; c++) {
            activeColumn[c] = RANDOM.nextDouble() < 0.30;
        }

        // Başlangıç pattern'i
        for (int r = 0; r < CANVAS_HEIGHT; r++) {
            for (int c = 0; c < CANVAS_WIDTH; c++) {
                if (activeColumn[c] && RANDOM.nextDouble() < 0.25) {
                    rainState[r][c] = true;
                } else {
                    rainState[r][c] = false;
                }
            }
        }

        rainInitialized = true;
    }

    // ---------- HER FRAME'DE YAĞMURU GÜNCELLE ----------
    private static void updateRain() {
        if (!rainInitialized) {
            initRain();
        }

        double dieProb   = 0.10; // var olan harfin yok olma ihtimali
        double birthProb = 0.12; // boş yere yeni harf doğma ihtimali

        for (int c = 0; c < CANVAS_WIDTH; c++) {
            if (!activeColumn[c]) continue; // bu sütun sonsuza kadar boş

            boolean[] newCol = new boolean[CANVAS_HEIGHT];

            // 1 satır aşağı kaydır
            for (int r = 0; r < CANVAS_HEIGHT; r++) {
                int from = (r - 1 + CANVAS_HEIGHT) % CANVAS_HEIGHT;
                newCol[r] = rainState[from][c];
            }

            // Doğma / ölme
            for (int r = 0; r < CANVAS_HEIGHT; r++) {
                if (newCol[r]) {
                    if (RANDOM.nextDouble() < dieProb) {
                        newCol[r] = false;
                    }
                } else {
                    if (RANDOM.nextDouble() < birthProb) {
                        newCol[r] = true;
                    }
                }
            }

            // Geri yaz
            for (int r = 0; r < CANVAS_HEIGHT; r++) {
                rainState[r][c] = newCol[r];
            }
        }
    }

    // ---------- YAĞMUR + KOŞAN TAVŞAN ÇİZ ----------
    private static void drawFrameWithRainAndRabbit(String[] rabbitFrame, int rabbitLeft) {
        updateRain();

        int rabbitTop = 0;
        int rabbitH   = rabbitFrame.length;
        int rabbitW   = rabbitFrame[0].length();

        for (int r = 0; r < CANVAS_HEIGHT; r++) {
            StringBuilder sb = new StringBuilder();

            for (int c = 0; c < CANVAS_WIDTH; c++) {
                char outChar = ' ';
                String color = Color.RESET;

                // 1) Tavşan var mı?
                int rr = r - rabbitTop;
                int rc = c - rabbitLeft;
                boolean insideRabbit = false;
                char rabbitCh = ' ';

                if (rr >= 0 && rr < rabbitH && rc >= 0 && rc < rabbitW) {
                    rabbitCh = rabbitFrame[rr].charAt(rc);
                    // Artık sadece '%' VEYA '#' VEYA ' ' olmayan karakterler tavşan derisi
                    if (rabbitCh != '%' && rabbitCh != '#' && rabbitCh != ' ') {
                        insideRabbit = true;
                    }
                }

                if (insideRabbit) {
                    outChar = rabbitCh;
                    color = Color.WHITE;
                } else {
                    // 2) Tavşan yoksa yağmura bak
                    if (c >= 0 && c < CANVAS_WIDTH &&
                            r >= 0 && r < CANVAS_HEIGHT &&
                            activeColumn[c] &&
                            rainState[r][c]) {

                        outChar = MATRIX_CHARS.charAt(RANDOM.nextInt(MATRIX_CHARS.length()));
                        color = Color.GREEN;
                    } else {
                        outChar = ' ';
                        color = Color.RESET;
                    }
                }

                if (color == Color.RESET) {
                    sb.append(outChar);
                } else {
                    sb.append(color).append(outChar).append(Color.RESET);
                }
            }

            System.out.println(sb);
        }
    }

    // ---------- YARDIMCI METODLAR ----------
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) { }
    }
}



