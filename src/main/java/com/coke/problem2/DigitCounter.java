package com.coke.problem2;

import java.util.List;

public interface DigitCounter {
    
    List<String> readInput();

    List<String> generateOutput(int startingNumber, int endingNumber, int[] delimitedDigits);

}
