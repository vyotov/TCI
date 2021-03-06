package com.company.Models.parametrizedTests;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestStringContainsOnlyNumbersMethod {

    private final boolean output;
    private final String input;
    private Extractor extractor;
    private DataExtractor dataExtractor;
    private Utils utils;
    public TestStringContainsOnlyNumbersMethod(boolean output, String input) {
        this.output = output;
        this.input = input;
        utils = new Utils();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{false, "cat=Movies"}, {true, "203"}, {false, ""}, {true, "0"}, {true, "23273273843"}, {false, "3282d3323"}};
        return Arrays.asList(data);
    }

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor,utils);
    }

    @Test
    public void ifContainsOnlyNumbers() {
        boolean actual = extractor.checkIfStringContainsOnlyNumbers(input);
        boolean expected = output;
        //Assert
        Assert.assertEquals(expected, actual);
    }
}
