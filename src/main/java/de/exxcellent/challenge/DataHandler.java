package de.exxcellent.challenge;

import java.util.List;

public class DataHandler {
    private int DAY_Index = 0;
    private int MAX_Temp_Index = 1;
    private int MIN_Temp_Index = 2;
    private List<String[]> data;
    private String[] headers;

    public DataHandler(List<String[]> data) {
        this.headers = data.get(0);
        data.remove(0);
        this.data = data;
    }

    public DataHandler(String filename) {
        var data = CsvReader.readCsvFileToListStringArrays(filename);
        this.headers = data.get(0);
        data.remove(0);
        this.data = data;
    }

    public String getSmallestSpread() {
        String indexSmallestSpread = null;
        float smallestSpread = Float.MAX_VALUE;
        for (String[] day : this.data) {
            var delta = Float.parseFloat(day[MAX_Temp_Index]) - Float.parseFloat(day[MIN_Temp_Index]);
            System.out.println(day[DAY_Index] + " " + delta);
            if (delta < smallestSpread) {
                indexSmallestSpread = day[DAY_Index];
                smallestSpread = delta;
            }
        }
        return indexSmallestSpread;
    }
}
