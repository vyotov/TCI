package com.company.Models.parametrizedTests;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PageCountTest {

    private Extractor extractor;
    private final int expected;
    private String inputUrl;


    public PageCountTest(int expected, String inputUrl) {
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
        extractor = new Extractor(inputUrl);
        //Act
        int actual = extractor.getPageCount();
        //Assert
        Assert.assertEquals(expected, actual);

    }
}
