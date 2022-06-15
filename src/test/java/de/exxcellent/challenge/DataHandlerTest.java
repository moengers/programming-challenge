package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

class DataHandlerTest {

    @Test
    void dataHandler_loadFile_SholdNotThrow_CreateNotEmptyDataHandler() {
        var dataHandler = new DataHandler("src/main/resources/de/exxcellent/challenge/weather.csv");

        assertTrue(dataHandler != null);
    }

    @Test
    void dataHandler_loadFile_SholdThrow() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            var dataHandler = new DataHandler("not/a/real/path");
        });

        String expectedMessage = "\"stringList\" is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void dataHandler_getSmallestSpread_1_onlyPositivValues() {
        List<String[]> stringList = new ArrayList<String[]>();
        String[] head = { "Day", "MxT", "MnT" };
        String[] v1 = { "1", "50", "49" };
        String[] v2 = { "2", "10", "0" };
        String[] v3 = { "3", "299999999", "2" };
        stringList.add(head);
        stringList.add(v1);
        stringList.add(v2);
        stringList.add(v3);

        var dataHandler = new DataHandler(stringList);
        var day = dataHandler.getSmallestSpread("Day", "MxT", "MnT");

        assertEquals("1", day);
    }

    @Test
    void dataHandler_getSmallestSpread_2_withNegativValues() {
        List<String[]> stringList = new ArrayList<String[]>();
        String[] head = { "Day", "MxT", "MnT" };
        String[] v1 = { "1", "50", "-1" };
        String[] v2 = { "2", "-4", "-5" };
        String[] v3 = { "3", "10", "2" };
        stringList.add(head);
        stringList.add(v1);
        stringList.add(v2);
        stringList.add(v3);

        var dataHandler = new DataHandler(stringList);
        var day = dataHandler.getSmallestSpread("Day", "MxT", "MnT");

        assertEquals("2", day);
    }

    @Test
    void dataHandler_getSmallestSpread_ByFileWether() {
        var dataHandler = new DataHandler("src/main/resources/de/exxcellent/challenge/weather.csv");
        var day = dataHandler.getSmallestSpread("Day", "MxT", "MnT");
        assertEquals("14", day);
    }

    @Test
    void dataHandler_getSmallestSpread_ByFootball() {
        var dataHandler = new DataHandler("src/main/resources/de/exxcellent/challenge/football.csv");
        var team = dataHandler.getSmallestSpread("Team", "Goals", "Goals Allowed");
        assertEquals("Aston_Villa", team);
    }
}