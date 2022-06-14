package de.exxcellent.challenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

class DataHandlerTest {

    @Test
    void dataHandlerLoadFile_SholdNotThrow_CreateNotEmptyDataHandler() {
        var dataHandler = new DataHandler("src/main/resources/de/exxcellent/challenge/weather.csv");

        assertTrue(dataHandler != null);
    }

    @Test
    void dataHandlerLoadFile_SholdThrow() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            var dataHandler = new DataHandler("not/a/real/path");
        });

        String expectedMessage = "\"stringList\" is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void dataHandler_getSmallestSpread_1_onlyPositivValues() {
        List<Float[]> floatList = new ArrayList<Float[]>();
        Float[] v1 = { 1f, 50f, 49f };
        Float[] v2 = { 2f, 10f, 0f };
        Float[] v3 = { 3f, 299999999f, 2f };
        floatList.add(v1);
        floatList.add(v2);
        floatList.add(v3);

        var dataHandler = new DataHandler(floatList);
        var day = dataHandler.getSmallestSpread();

        assertEquals(1, day);
    }

    @Test
    void dataHandler_getSmallestSpread_2_withNegativValues() {
        List<Float[]> floatList = new ArrayList<Float[]>();
        Float[] v1 = { 1f, 50f, -1f };
        Float[] v2 = { 2f, -4f, -5f };
        Float[] v3 = { 3f, 10f, 2f };
        floatList.add(v1);
        floatList.add(v2);
        floatList.add(v3);

        var dataHandler = new DataHandler(floatList);
        var day = dataHandler.getSmallestSpread();

        assertEquals(2, day);
    }
}