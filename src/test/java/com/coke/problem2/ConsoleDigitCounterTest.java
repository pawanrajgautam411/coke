package com.coke.problem2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsoleDigitCounterTest {

    @Test
    void givenNumberFrom1_to_35() {
        ConsoleDigitCounter consoleDigitCounter = new ConsoleDigitCounter();
        List<String> strings = consoleDigitCounter.generateOutput(1, 35, new int[]{3, 5});

        assertNotNull(strings);
        assertEquals("3 occurs 5 times", strings.get(0));
        assertEquals("5 occurs 4 times", strings.get(1));

    }

    @Test
    void givenNumberFrom7_to_77() {
        ConsoleDigitCounter consoleDigitCounter = new ConsoleDigitCounter();
        List<String> strings = consoleDigitCounter.generateOutput(7, 77, new int[]{7});

        assertNotNull(strings);
        assertEquals("7 occurs 9 times", strings.get(0));
    }


    /**
     * executing test case using scanner class, hence running in main method
     *
     * @param args the program args if any
     */
    public static void main(String[] args) {
        ConsoleDigitCounter consoleDigitCounter = new ConsoleDigitCounter();
        List<String> listOfInputs = consoleDigitCounter.readInput();

        int startingDigit = Integer.parseInt(listOfInputs.get(0));
        int endingDigit = Integer.parseInt(listOfInputs.get(1));

        int[] array = Arrays.stream(listOfInputs.get(2).split(","))
                .mapToInt(Integer::valueOf)
                .toArray();

        List<String> strings = consoleDigitCounter.generateOutput(startingDigit, endingDigit, array);

        assertNotNull(strings);
        assertEquals("3 occurs 5 times", strings.get(0));
        assertEquals("5 occurs 4 times", strings.get(1));
    }
}