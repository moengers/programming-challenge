package de.exxcellent.challenge;

import java.util.Arrays;
import java.util.List;

public class DataHandler {
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

    public String getSmallestSpread(String index, String firstValue, String secValue) {
        String indexSmallestSpread = null;
        float smallestSpread = Float.MAX_VALUE;
        int indexColumn = columnFromHeader(index);
        int firstValueColumn = columnFromHeader(firstValue);
        int secValueColumn = columnFromHeader(secValue);
        for (String[] day : this.data) {
            var delta = Float.parseFloat(day[firstValueColumn]) - Float.parseFloat(day[secValueColumn]);
            System.out.println(day[indexColumn] + " " + delta);
            if (delta < smallestSpread) {
                indexSmallestSpread = day[indexColumn];
                smallestSpread = delta;
            }
        }
        return indexSmallestSpread;
    }

    private int columnFromHeader(String header) {
        return Arrays.asList(headers).indexOf(header);
    }
}
