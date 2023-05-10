package com.coke.problem1;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DeclarationInvertText implements InvertText {

    /**
     * convert the file content to collection of csv rows
     *
     * @param fileContent the content of the file
     * @return the collection of word count in csv format
     */
    @Override
    public Collection<String> convertToCsvRows(FileContent fileContent) {
        String text = fileContent.getText();

        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException("invalid-file-content");
        }

        // escaping all the special characters
        String escapedString = text.replaceAll("[^a-zA-Z0-9]", " ");

        StringTokenizer stringTokenizer = new StringTokenizer(escapedString);

        Map<String, Integer> map = new HashMap<>();

        while (stringTokenizer.hasMoreTokens()) {
            String strToken = stringTokenizer.nextToken().trim().toLowerCase();

            if (InvertText.EXCLUDED_WORDS.contains(strToken)) {
                continue;
            }

            Integer currentCount = map.getOrDefault(strToken, 0);
            map.put(strToken, currentCount + 1);
        }

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> entry.getValue() + " , " + entry.getKey())
                .collect(Collectors.toList());
    }

    /**
     * only prints the collection in next lines
     *
     * @param collection collection of rows in csv format
     */
    @Override
    public void printCsvFormat(Collection<String> collection) {
        collection.forEach(System.out::println);
    }


    /**
     * convert file from resources to String
     *
     * @param fileName name of the file
     * @return the file content wrapping the text in the file
     */
    @Override
    public FileContent readFile(String fileName) {
        if (fileName == null || fileName.trim().length() == 0) {
            throw new IllegalArgumentException("invalid-file-name");
        }

        StringBuilder stringBuilder = null;
        BufferedReader bufferedReader = null;

        String finalFileContent = "";
        try {
            ClassPathResource classPathResource = new ClassPathResource(fileName);
            String filePath = classPathResource.getFile().toPath().toString();

            bufferedReader = new BufferedReader(new java.io.FileReader(filePath));
            stringBuilder = new StringBuilder();

            String str;

            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }

            finalFileContent = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new FileContent(finalFileContent);
    }
}
