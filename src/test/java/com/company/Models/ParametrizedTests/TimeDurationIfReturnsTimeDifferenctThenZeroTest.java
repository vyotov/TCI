package com.company.Models.ParametrizedTests;

import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TimeDurationParamTest {
    private final Integer expected;
    private Extractor extractor;

    @Before
    public void setup() {
        extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
    }

    public TimeDurationParamTest( Integer expected) {
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{1}};
        return Arrays.asList(data);
    }

    @Test
    public void SearchMusicById() throws IOException {
        extractor.getAllObjects();
        Long actual = extractor.getTimeDuration();
        //Assert
        Assert.assertNotEquals(expected, actual);

    }
}
