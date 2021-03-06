package com.company.Models.ParametrizedTests;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.json.simple.JSONObject;
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
public class SearchByKeywordTest {
    private final String keyWord;
    private final String expected;
    private Extractor extractor;
    private DataExtractor dataExtractor;
    private Utils utils;
    public SearchByKeywordTest(String keyWord, String expected) {
        utils = new Utils();
        this.keyWord = keyWord;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{
                //movie
                {"Mike Judge", "{\"filter\":\"Mike Judge\",\"result\":{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"time\":0}"}
                //music
                , {"Elvis Forever", "{\"filter\":\"Elvis Forever\",\"result\":{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"},\"time\":0}"}
                //book
                , {"Refactoring: Improving the Design of Existing Code", "{\"filter\":\"Refactoring: Improving the Design of Existing Code\",\"result\":{\"name\":\"Refactoring: Improving the Design of Existing Code\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Hardcover\",\"year\":\"1999\",\"authors\":[\"Martin Fowler\",\" Kent Beck\",\" John Brant\",\" William Opdyke\",\" Don Roberts\"],\"publisher\":\"Addison-Wesley Professional\",\"isbn\":\"978-0201485677\"},\"time\":0}"}
        };
        return Arrays.asList(data);
    }

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor,utils);
    }

    @Test
    public void shouldPassTestSearchByKeywordTest() throws IOException, ClassNotFoundException, IllegalAccessException {
        JSONObject actual = extractor.getJsonForSearchByKeyWord(keyWord);
        actual.put("time", 0);
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void shouldFailTestSearchByKeywordTest() throws IOException, ClassNotFoundException, IllegalAccessException {
        JSONObject actual = extractor.getJsonForSearchByKeyWord(keyWord);
        actual.put("time", 0);
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }
}
