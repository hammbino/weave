package com.weave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        String fileName;
        Scanner fileReader = null;
        int totalCases = 0;
        int numFlips;
        List<String> pancakes = new ArrayList<>();

        //check to see if the program was run with a command line argument
        if (args.length < 1) {
            System.out.println("Error: No file was provided.");
            System.exit(9);     // TERMINATE THE PROGRAM
        }

        //check that the file is a .txt file
        if (!(args[0].matches(".+\\.(txt)"))) {
            System.out.println("Error: Incorrect file format provided.\\n File type should be .txt");
            System.exit(8);     // TERMINATE THE PROGRAM
        }

        //store the file name
        fileName = args[0];

        //open the file and store the data
        try {
            fileReader = new Scanner(new FileInputStream(fileName)).useDelimiter("[\\n\\r]");

            //read in the first line and check that it is a positive integer
            if (fileReader.hasNextInt()) {
                totalCases = (fileReader.nextInt());
            } else {
                System.out.println("Error: Number of cases was not provided");
                System.exit(8);     // TERMINATE THE PROGRAM
            }

            //read in each line
            while (fileReader.hasNext()) {
                //Reads in the line of text and removes white space
                String lineText = fileReader.nextLine().replaceAll("//[\\s\\S]*", "").trim();

                //Checks that the line is not blank
                if (!(lineText.matches("\\s")))
                    pancakes.add(lineText);
            }
        } catch (FileNotFoundException x) {
            System.out.println("ERROR: Unable to open file " + fileName);
            x.printStackTrace();
            System.exit(7);   // TERMINATE THE PROGRAM
        } finally {
            if(fileReader != null)
                fileReader.close();
        }

        //verify the number of cases given in the file matches the size of the list
        if (totalCases != pancakes.size() -1) {
            System.out.println("Array size: " + pancakes.size() + " Total cases: " + totalCases);
            System.out.println("Error: Number of cases does not match expected");
            System.exit(8);     // TERMINATE THE PROGRAM
        }

        //calculate the number of flips and output the answer
        for (int i = 1; i < pancakes.size(); i++) {
            numFlips = countFlips(pancakes.get(i));
            printMessage(i, numFlips);
        }
    }

    //This method compares side by side character moving from left to right
    //If the characters do not match then count is incremented
    //if the last character in the string is "-" then we add 1 to the count
    private static int countFlips(String s) {
        char[] panCakes = s.toCharArray();
        int count = 0;
        for (int i = 0; i < panCakes.length - 1; i++) {
            if (panCakes[i] != panCakes[i + 1])
                count++;
        }
        if (panCakes[panCakes.length - 1] != '+') {
            count++;
        }
        return count;
    }

    //This method outputs a message when a case contains bad values
    private static void printBadDataMessage(int caseNum){
        System.out.println("Case #" + caseNum  + ": null");
    }

    //This method outputs the case number and number of flips when a case contains bad values
    private static void printMessage(int caseNum, int numFlips){
        System.out.println("Case #" + caseNum + ": " + numFlips);
    }
}
