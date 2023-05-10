package com.coke.problem1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface InvertText {

    List<String> EXCLUDED_WORDS = Arrays.asList("of", "the", "to", "and", "for");

    Collection<String> convertToCsvRows(FileContent fileContent);

    void printCsvFormat(Collection<String> collection);

    FileContent readFile(String fileName);

}


/**
 * class to hold the file content and other related attributes
 */
@Getter
@Setter
@AllArgsConstructor
class FileContent {
    private String text;
}



