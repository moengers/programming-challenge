package de.exxcellent.challenge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple CSV Reader.
 */
public class CsvReader {

    /**
     * Reading the the CSV file and converting it to a List of String Arrays
     * 
     * @param filename location of the file
     * @return List of String Arrays with the seperated CSV values
     */
    static List<String[]> readCsvFileToListStringArrays(String filename) {

        System.out.println("Reading the file " + filename + " ...");
        try {
            // Open BufferedReader and setup variables
            Reader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            List<String[]> lines = new ArrayList<String[]>();

            String thisLine;

            // Iterate over all lines and split the line by the ","
            while ((thisLine = br.readLine()) != null) {
                lines.add(thisLine.split(","));
            }

            // Close BufferedReader and return value
            br.close();
            System.out.println("Finished reading the file " + filename);
            return lines;

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: Trying to read the file: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException: Trying to parse the file: " + filename);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reading the the CSV file and converting it to a List of Float Arrays
     * The headings are lost in this process
     * 
     * @param filename   location of the file
     * @param withHeader Does the CVS file have a headers?
     * 
     * @return List of Float Arrays with the seperated CSV values
     */
    static List<Float[]> readCsvFileToListFloatArray(String filename, boolean withHeader) {
        List<String[]> stringList = readCsvFileToListStringArrays(filename);
        if (withHeader) {
            stringList.remove(0);
        }
        List<Float[]> floatList = new ArrayList<Float[]>();
        for (String[] strings : stringList) {
            floatList.add(StringArrayToFloatArray(strings));
        }
        return floatList;
    }

    /**
     * Reading the the CSV file and converting it to a List of Float Arrays
     * The headings are lost in this process
     * Assume that there are headers
     * 
     * @param filename location of the file
     * @return List of Float Arrays with the seperated CSV values
     */
    static List<Float[]> readCsvFileToListFloatArray(String filename) {
        return readCsvFileToListFloatArray(filename, true);
    }

    /**
     * Convert a list of Strings to a list of floats
     * 
     * @param stringArray an array of strings
     * @return an array of floats with the values of the strings
     */
    private static Float[] StringArrayToFloatArray(String[] stringArray) {
        Float[] floatArray = new Float[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            floatArray[i] = Float.parseFloat(stringArray[i]);
        }
        return floatArray;
    }
}
