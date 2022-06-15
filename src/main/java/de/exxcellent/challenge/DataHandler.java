package de.exxcellent.challenge;

import java.util.List;

public class DataHandler {
    private int DAY_Index = 0;
    private int MAX_Temp_Index = 1;
    private int MIN_Temp_Index = 2;
    private List<Float[]> data;

    public DataHandler(List<Float[]> data) {
        this.data = data;
    }

    public DataHandler(String filename) {
        this.data = CsvReader.readCsvFileToListFloatArray(filename);
    }

    public int getSmallestSpread() {
        int indexSmallestSpread = -1;
        float smallestSpread = Float.MAX_VALUE;
        for (Float[] day : this.data) {
            var delta = day[MAX_Temp_Index] - day[MIN_Temp_Index];
            System.out.println(Math.round(day[DAY_Index]) + " " + delta);
            if (delta < smallestSpread) {
                indexSmallestSpread = Math.round(day[DAY_Index]);
                smallestSpread = delta;
            }
        }
        return indexSmallestSpread;
    }
}
