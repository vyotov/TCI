package com.company.Models.parametrizedTests;

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
public class TimeDurationIfReturnsTimeDifferenceThenZeroTest {
    private final Integer expected;
    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor("http://localhost:8888");
    }

    public TimeDurationIfReturnsTimeDifferenceThenZeroTest(Integer expected) {
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{0}, {1000}};
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
