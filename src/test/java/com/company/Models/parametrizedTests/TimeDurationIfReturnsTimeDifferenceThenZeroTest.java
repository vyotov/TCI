package com.company.Models.ParametrizedTests;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TimeDurationIfReturnsTimeDifferenceThenZeroTest {
    private final Integer expected;
    private Extractor extractor;
    private DataExtractor dataExtractor;

    public TimeDurationIfReturnsTimeDifferenceThenZeroTest(Integer expected) {
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{0}, {1000}};
        return Arrays.asList(data);
    }

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor);

    }

    @Test
    public void testTimeDuration() throws IOException, ClassNotFoundException, IllegalAccessException {
        extractor.getAllObjects();
        Long actual = extractor.getTimeDuration();
        //Assert
        Assert.assertNotEquals(expected, actual);

    }
}
