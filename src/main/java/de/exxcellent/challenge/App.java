package de.exxcellent.challenge;

import java.io.Console;
import java.util.Arrays;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * 
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        try {
            var dataHandler = new DataHandler("src/main/resources/de/exxcellent/challenge/weather.csv");
            String dayWithSmallestTempSpread = String.valueOf(dataHandler.getSmallestSpread("Day", "MxT", "MnT"));
            System.out.printf("Day with smallest temperature spread : %s%n",
                    dayWithSmallestTempSpread);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            var dataHandler = new DataHandler("src/main/resources/de/exxcellent/challenge/football.csv");
            String teamWithSmallestGoalSpread = dataHandler.getSmallestSpread("Team", "Goals", "Goals Allowed");
            System.out.printf("Team with smallest goal spread : %s%n",
                    teamWithSmallestGoalSpread);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
