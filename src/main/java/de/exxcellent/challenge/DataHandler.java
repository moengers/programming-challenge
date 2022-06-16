package de.exxcellent.challenge;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Can handle a table as List<String[]>.
 * Can calculate the smallest spread between values in two columns in this
 * table.
 */
public class DataHandler {
    private List<String[]> data;
    private String[] headers;

    /**
     * Constructor
     * Initialize DataHandler by a data in a List of String-Arrays.
     * 
     * @param data
     */
    public DataHandler(List<String[]> data) {
        this.headers = data.get(0);
        data.remove(0);
        this.data = data;
    }

    /**
     * Constructor
     * Initialize DataHandler by a file and it's path.
     * 
     * @param filename relativ path to file
     * @throws Exception FileNotFoundException if the path is not valid
     */
    public DataHandler(String filename) throws Exception {
        var data = CsvReader.readCsvFileToListStringArrays(filename);
        if (data == null)
            throw new FileNotFoundException("CsvReader.readCsvFileToListStringArrays: data is empty");
        this.headers = data.get(0);
        data.remove(0);
        this.data = data;
    }

    /**
     * Calculate the smallest spread of the data between firstValue column and
     * secValue column and returns the value of the index column
     * 
     * @param index      header name of index column
     * @param firstValue header name of the first value column
     * @param secValue   header name of the second value column
     * @return value of the index column with the smallest spread
     */
    public String getSmallestSpread(String index, String firstValue, String secValue) {
        String indexSmallestSpread = null;
        float smallestSpread = Float.MAX_VALUE;
        int indexColumn = columnFromHeader(index);
        int firstValueColumn = columnFromHeader(firstValue);
        int secValueColumn = columnFromHeader(secValue);
        for (String[] day : this.data) {
            var delta = Float.parseFloat(day[firstValueColumn]) - Float.parseFloat(day[secValueColumn]);
            delta = Math.abs(delta);
            // System.out.println(day[indexColumn] + " " + delta);
            if (delta < smallestSpread) {
                indexSmallestSpread = day[indexColumn];
                smallestSpread = delta;
            }
        }
        return indexSmallestSpread;
    }

    /**
     * Get the index of the header
     * 
     * @param header name of the header
     * @return index of the column with this header name
     */
    private int columnFromHeader(String header) {
        return Arrays.asList(headers).indexOf(header);
    }
}
