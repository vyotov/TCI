package com.company.Models.ParametrizedTests;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TimeDurationIfReturnsTimeDifferenctThenZeroTest {
    private final Integer expected;
    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
    }

    public TimeDurationIfReturnsTimeDifferenctThenZeroTest(Integer expected) {
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{0},{1000}};
        return Arrays.asList(data);
    }

    @Test
    public void testTimeDuration() throws IOException, ClassNotFoundException {
        extractor.getAllObjects();
        Long actual = extractor.getTimeDuration();
        //Assert
        Assert.assertNotEquals(expected, actual);

    }
}
