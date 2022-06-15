package de.exxcellent.challenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Example JUnit 5 test case.
 * 
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private String successLabel = "not successful";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void runWeather() {
        App.main("--weather", "src/main/resources/de/exxcellent/challenge/weather.csv");
        String expect = "Day with smallest temperature spread : 14";
        assertEquals(expect, outputStreamCaptor.toString().trim());
    }

    @Test
    void runFootball() {
        App.main("--football", "src/main/resources/de/exxcellent/challenge/football.csv");
        String expect = "Team with smallest goal spread : Aston_Villa";
        assertEquals(expect, outputStreamCaptor.toString().trim());
    }

}