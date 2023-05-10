package com.coke.problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeclarationInvertTextTest {
    private InvertText invertText;

    @BeforeEach
    void beforeEach() {
        invertText = new DeclarationInvertText();
    }

    @Test
    void givenValidDeclarationAsFile_thenAssertTheFirstFour_asCsvFormat() {
        FileContent fileContent = invertText.readFile("declaration_of_independence.txt");
        assertNotNull(fileContent);
        assertNotNull(fileContent.getText());
        assertTrue(fileContent.getText().trim().length() > 0);

        Collection<String> rows = invertText.convertToCsvRows(fileContent);
        List<String> list = rows.stream()
                .limit(4)
                .collect(Collectors.toList());

        assertEquals("26 , our", list.get(0));
        assertEquals("21 , has", list.get(1));
        assertEquals("20 , their", list.get(2));
        assertEquals("19 , he", list.get(3));

        invertText.printCsvFormat(rows);
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", ""})
    @NullSource
    void givenInvalidFileName_thenExpectException(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> invertText.readFile(input));

        assertEquals("invalid-file-name", exception.getMessage());
    }
}