package com.company.Models.ParametrizedTests;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PageCountTest {

    private final int expected;
    private Extractor extractor;
    private DataExtractor dataExtractor;
    private Utils utils;
    private String inputUrl;


    public PageCountTest(int expected, String inputUrl) {
        utils= new Utils();
        this.expected = expected;
        this.inputUrl = inputUrl;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{22, "http://localhost:8888"},
                {0, "https://whysor.com"},
                {0, "http://fontys.nl"}};
        return Arrays.asList(data);
    }


    @Test
    public void ifPageCountValid() throws MalformedURLException {
        //Arrange
        dataExtractor = new DataExtractor();
        extractor = new Extractor(inputUrl,dataExtractor,utils);
        //Act
        int actual = extractor.getPageCount();
        //Assert
        Assert.assertEquals(expected, actual);

    }
    @Test
    public void ifPageCountNotValid() throws MalformedURLException {
        //Arrange
        dataExtractor = new DataExtractor();
        extractor = new Extractor(inputUrl,dataExtractor,utils);
        //Act
        int actual = extractor.getPageCount();
        //Assert
        Assert.assertNotEquals("", actual);

    }
}
