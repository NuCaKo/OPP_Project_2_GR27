package com.group27.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Handles the display of the termination screen animation.
 */
public class TerminationScreen {

    private static final String RESET = "\033[0m";
    private static final String RED = "\033[0;31m";
    private static final String RED_BOLD = "\033[1;31m";
    private static final String YELLOW = "\033[0;33m";
    private static final String YELLOW_BOLD = "\033[1;33m";
    private static final String WHITE = "\033[0;37m";
    private static final String WHITE_BOLD = "\033[1;37m";
    private static final String BOLD = "\033[1m";

    private static final int WIDTH = 130;
    private static final int HEIGHT = 43;
    private static final int FPS = 15;
    private static final long FRAME_DELAY = 1000 / FPS;

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

    private static final String[] GOODBYE_TEXT = {
            "   ______                   __   __                   ",
            "  / ____/  ____   ____     / /  / /_   __  __   ___   ",
            " / / __   / __ \\ / __ \\   / /  / __ \\ / / / /  / _ \\  ",
            "/ /_/ /  / /_/ // /_/ /  / /  / /_/ // /_/ /  /  __/  ",
            "\\____/   \\____/ \\____/  /_/  /_.___/ \\__, /   \\___/   ",
            "                                    /____/            "
    };

    private static final String[] ATATURK_PORTRAIT = {
            "⠂⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣄⣠⣄⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣶⣶⣿⣿⣾⣿⣿⣿⣿⣾⣷⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⡿⡿⠻⠛⠛⠛⠛⠛⠛⠻⠿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣯⣤⣴⣷⣿⣄⠀⠀⣴⣶⣶⣶⢤⣍⠻⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⢻⣿⡿⠙⣿⣶⣿⡿⣿⡿⠀⠀⠿⡿⠟⢿⠷⠦⠀⢹⣿⣗⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⣾⣿⣄⠀⠀⠀⠀⣄⣄⣀⠀⢀⣤⠀⠀⠀⠀⠀⢀⣭⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⢾⣿⣿⠀⠀⠈⠙⣿⣿⣿⣛⠋⠁⠀⠀⠀⠀⢈⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⣿⣿⣷⢀⣶⣷⣿⣧⣽⣾⣶⡆⠀⠀⠀⢀⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣌⠉⠁⣾⣿⣷⠀⠈⠁⢠⣦⣼⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣄⠀⠀⠀⠀⠀⠀⢀⣾⣼⠋⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡘⠻⣿⣿⣿⣿⣿⣷⣶⡶⠞⠋⠁⠀⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠀⠀⡟⣿⣿⡟⠉⠁⠀⠀⠀⢀⣤⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣧⣀⠀⠈⠿⠀⠀⠀⣀⣴⣾⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣾⣿⣿⣿⡇⣿⣿⣾⣯⣭⣶⣶⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠉⢿⣿⣿⠿⠋⠀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⠋⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢹⣿⣿⣿⣿⢯⣿⣿⣻⣿⣿⣿⣧⢠⣾⣿⣿⡆⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣽⣿⠿⠟⢛⣿⣻⠟⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⣾⣿⣿⣿⣮⣻⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣆⣴⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⢻⣿⢏⣵⣽⣶⡴⢻⠿⢯⣄⡀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣷⣽⢿⣯⣿⢿⣿⣿⣯⡾⠋⢁⣴⣿⣿⣿⡟⢠⣞⣿⡾⢹⡿⠿⠛⠋⠉⠀⠈⠀⠀⠀⠙⢷⣄",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢉⣿⣾⣿⣿⣿⢿⣟⣿⣿⣽⠿⠋⠙⠳⣿⣫⣾⣟⣿⠋⠀⣠⣾⣿⠞⢡⣿⠞⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣽",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠓⠋⣥⣼⠟⠉⠀⠀⢀⠀⠀⠀⣸⡏⢹⡟⢿⠴⠞⠃⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⡤⠶⠞⠋⠁",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠟⠃⠀⠀⠀⠀⣾⣃⣠⡶⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠠⣾⣖⣞⣧⣤⠤⠶⠶⠶⠚⠛⠛⠉⠉⠉⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "                                     MUSTAFA KEMAL ATATÜRK                                          "
    };

    private static final String ATATURK_QUOTE = "Benim naçiz vücudum elbet bir gün toprak olacaktır, ancak Türkiye Cumhuriyeti ilelebet payidar kalacaktır.";

    private static final String MATRIX_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    private static final int RAIN_AREA_HEIGHT = 32;
    private static final int RAIN_AREA_WIDTH = WIDTH - 4;

    private static boolean[] activeColumn;
    private static boolean[][] rainState;
    private static boolean rainInitialized = false;
    private static boolean stopRain = false;

    /**
     * Displays the termination animation and tribute.
     */
    public static void show() {
        int durationFrames = 90;
        int frameCount = 0;
        int rabbitWidth = RABBIT_FRAME1[0].length();

        rainInitialized = false;
        stopRain = false;

        int startX = (WIDTH - rabbitWidth) / 2;
        int endX = WIDTH + 10;

        try {
            System.out.print("\033[?25l");
            System.out.print("\033[H\033[2J");

            while (frameCount < durationFrames) {
                double progress = (double) frameCount / durationFrames;

                int rabbitLeft = (int) (startX + (endX - startX) * progress);

                if (progress > 0.6) stopRain = true;

                updateRain();

                StringBuilder sb = new StringBuilder();
                sb.append("\033[H");

                renderFrame(sb, frameCount, rabbitLeft, progress);

                System.out.print(sb.toString());
                System.out.flush();

                Thread.sleep(FRAME_DELAY);
                frameCount++;
            }


            for(int i=0; i<15; i++) {
                updateRain();
                StringBuilder sb = new StringBuilder();
                sb.append("\033[H");
                renderFrame(sb, frameCount + i, WIDTH + 20, 1.0);
                System.out.print(sb.toString());
                System.out.flush();
                Thread.sleep(FRAME_DELAY);
            }


            int totalLines = 45;
            for (int mask = 0; mask < totalLines / 2 + 2; mask++) {
                StringBuilder sb = new StringBuilder();
                sb.append("\033[H");
                renderFrameContent(sb, 0, WIDTH + 20, 1.0, mask, true);
                System.out.print(sb.toString());
                System.out.flush();
                Thread.sleep(30);
            }

            System.out.print("\033[H\033[2J");
            System.out.flush();

            showAtaturkTribute();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.print("\033[?25h");
        }
    }

    /**
     * Shows a tribute animation.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    private static void showAtaturkTribute() throws InterruptedException {
        System.out.print("\033[H\033[2J");

        int startRow = Math.max(1, (HEIGHT - ATATURK_PORTRAIT.length) / 2 - 3);

        System.out.print(WHITE_BOLD);

        for (int i = 0; i < ATATURK_PORTRAIT.length; i++) {
            String line = ATATURK_PORTRAIT[i];
            int paddingLeft = Math.max(1, (WIDTH - line.length()) / 2);

            System.out.printf("\033[%d;%dH", startRow + i, paddingLeft);
            System.out.print(line);
            System.out.flush();

            Thread.sleep(50);
        }

        String quote = ATATURK_QUOTE;
        int quoteRow = startRow + ATATURK_PORTRAIT.length + 2;
        int quotePadding = Math.max(1, (WIDTH - quote.length()) / 2);

        System.out.printf("\033[%d;%dH", quoteRow, quotePadding);
        System.out.print(WHITE_BOLD + BOLD);

        for (char c : quote.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(40);
        }

        System.out.print(RESET);

        Thread.sleep(5000);
        System.out.println();
    }

    /**
     * Renders a single animation frame.
     *
     * @param sb          the StringBuilder to render to
     * @param frameCount  the current frame count
     * @param rabbitLeft  the rabbit's left position
     * @param progress    the current progress
     */
    private static void renderFrame(StringBuilder sb, int frameCount, int rabbitLeft, double progress) {
        renderFrameContent(sb, frameCount, rabbitLeft, progress, -1, false);
    }

    /**
     * Renders the content of a frame.
     *
     * @param sb          the StringBuilder to render to
     * @param frameCount  the current frame count
     * @param rabbitLeft  the rabbit's left position
     * @param progress    the current progress
     * @param maskHeight  the mask height for closing animation
     * @param isFinal     whether this is the final frame
     */
    private static void renderFrameContent(StringBuilder sb, int frameCount, int rabbitLeft, double progress, int maskHeight, boolean isFinal) {
        int currentLine = 0;
        int rabbitWidth = RABBIT_FRAME1[0].length();

        String borderColor = (frameCount / 5) % 2 == 0 ? RED : YELLOW;

        appendLine(sb, borderColor + "╔" + "═".repeat(WIDTH - 2) + "╗" + RESET, currentLine++, maskHeight);

        String status = stopRain ? "SYSTEM HALTED" : "INITIATING SHUTDOWN SEQUENCE...";
        String colorStatus = stopRain ? RED_BOLD : YELLOW;
        appendLine(sb, borderColor + "║" + RESET + centerText(colorStatus + status + RESET, WIDTH - 2) + borderColor + "║" + RESET, currentLine++, maskHeight);

        appendLine(sb, borderColor + "╠" + "═".repeat(WIDTH - 2) + "╣" + RESET, currentLine++, maskHeight);

        String[] currentRabbit = ((frameCount / 3) % 2 == 0) ? RABBIT_FRAME1 : RABBIT_FRAME2;

        int goodbyeTop = (RAIN_AREA_HEIGHT - GOODBYE_TEXT.length) / 2;

        for (int r = 0; r < RAIN_AREA_HEIGHT; r++) {
            StringBuilder lineSb = new StringBuilder();
            lineSb.append(borderColor).append("║ ").append(RESET);

            for (int c = 0; c < RAIN_AREA_WIDTH; c++) {
                char outChar = ' ';
                String color = RESET;

                boolean inGoodbye = false;
                if (stopRain && r >= goodbyeTop && r < goodbyeTop + GOODBYE_TEXT.length) {
                    int textRow = r - goodbyeTop;
                    String line = GOODBYE_TEXT[textRow];
                    int textPad = (RAIN_AREA_WIDTH - line.length()) / 2;
                    int textCol = c - textPad;

                    if (textCol >= 0 && textCol < line.length()) {
                        char ch = line.charAt(textCol);
                        if (ch != ' ') {
                            outChar = ch;
                            color = YELLOW_BOLD;
                            inGoodbye = true;
                        }
                    }
                }

                if (!inGoodbye) {
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
                        if ((rc + frameCount) % 6 < 3) color = RED;
                        else color = YELLOW;
                    } else {
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
                            if (isHead) color = WHITE_BOLD;
                            else if (RANDOM.nextInt(10) > 8) color = YELLOW_BOLD;
                            else color = RED;
                        }
                    }
                }

                if (color.equals(RESET)) lineSb.append(outChar);
                else lineSb.append(color).append(outChar).append(RESET);
            }
            lineSb.append(borderColor).append(" ║").append(RESET);
            appendLine(sb, lineSb.toString(), currentLine++, maskHeight);
        }

        appendLine(sb, borderColor + "╚" + "═".repeat(WIDTH - 2) + "╝" + RESET, currentLine++, maskHeight);
    }

    /**
     * Appends a line to the StringBuilder, considering the mask height.
     *
     * @param sb          the StringBuilder
     * @param content     the content of the line
     * @param lineIndex   the index of the line
     * @param maskHeight  the height of the mask
     */
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

    /**
     * Centers text within the specified width.
     *
     * @param text  the text to center
     * @param width the width to center in
     * @return the centered text string
     */
    private static String centerText(String text, int width) {
        String stripped = text.replaceAll("\033\\[[;\\d]*m", "");
        int padding = (width - stripped.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<padding; i++) sb.append(" ");
        sb.append(text);
        for(int i=0; i<width - padding - stripped.length(); i++) sb.append(" ");
        return sb.toString();
    }

    /**
     * Initializes the rain animation.
     */
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

    /**
     * Updates the rain animation.
     */
    private static void updateRain() {
        if (!rainInitialized) initRain();
        double dieProb = stopRain ? 0.30 : 0.10;
        double birthProb = stopRain ? 0.00 : 0.12;

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
}
