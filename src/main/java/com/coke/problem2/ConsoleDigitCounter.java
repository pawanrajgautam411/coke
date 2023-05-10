package com.coke.problem2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleDigitCounter implements DigitCounter {

    /**
     * reading input from console
     *
     * @return the list of scanned input using Scanner class
     */
    @Override
    public List<String> readInput() {
        Scanner scanner = new Scanner(System.in);
        String startingDigit = scanner.nextLine();
        if (startingDigit == null || startingDigit.trim().length() == 0) {
            throw new IllegalArgumentException("invalid-starting-digit");
        }

        String endingDigit = scanner.nextLine();
        if (endingDigit == null || endingDigit.trim().length() == 0) {
            throw new IllegalArgumentException("invalid-ending-digit");
        }

        String delimitedDigits = scanner.nextLine();
        if (delimitedDigits == null || delimitedDigits.trim().length() == 0) {
            throw new IllegalArgumentException("invalid-delimited-digit");
        }

        return Arrays.asList(startingDigit, endingDigit, delimitedDigits);
    }

    /**
     * generate the desired output as per the assignment
     *
     * @param startingNumber  starting number of the counter series
     * @param endingNumber    ending number of the counter series
     * @param delimitedDigits comma separated delimited numbers
     * @return the printable list
     */
    @Override
    public List<String> generateOutput(int startingNumber, int endingNumber, int[] delimitedDigits) {

        List<String> finalList = new ArrayList<>();

        for (int delimitedDigit : delimitedDigits) {
            int count = 0;

            int incrementer = 0;
            for (int i = startingNumber; i <= endingNumber; i++) {

                if (delimitedDigit * 11 == i) {
                    System.out.println(i);
                    count += 2;

                } else if (delimitedDigit == i
                        || (delimitedDigit + incrementer) == i) {
                    System.out.println(i);
                    count++;

                } else if (i % 10 == 0) {
                    incrementer += 10;
                }

            }

            finalList.add(delimitedDigit + " occurs " + count + " times");
        }

        return finalList;
    }

}
