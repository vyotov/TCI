package com.company.Models.matcher;

import com.company.Models.custom_rule.LoggerRule;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import com.google.gson.Gson;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.company.Models.matcher.custom.IsPage.page;
import static com.company.Models.matcher.custom.IsValidUrl.IsValidUrl;
import static com.company.Models.matcher.custom.ValidateJsonString.isValidJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomMatcherTest {
    @Rule
    public LoggerRule performanceLogger = new LoggerRule();

    @Rule
    public ExpectedException rule = ExpectedException.none();

    private Extractor extractor;
    private DataExtractor dataExtractor;
    private Utils utils;

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888", dataExtractor, utils);
        utils = new Utils();
    }

    @Test
    public void shouldPassForPageCount() {
        // Given
        Integer pages = extractor.getPageCount();
        // Then
        assertThat(pages, (not(page())));
    }

    @Test
    public void shouldFailForPageCount() {
        // Given
        int pages = 0;
        // Then
        assertThat(pages, is(not(page())));
    }

    //TEST TO SEE IF MATCHER WORKS
    @Test
    public void testIfGetJsonForSearchByKeyWordReturnsCorrectJsonObject() throws IOException, ClassNotFoundException {
        //Arrange
        DataExtractor dataExtractor = new DataExtractor();
        Utils util = new Utils();
        Extractor extractor = new Extractor("http://localhost:8888", dataExtractor, util);

        String jsonString = new Gson().toJson(extractor.findObjectModelForSearchText(extractor.getAllObjects(), "Elvis Presley"));
        assertThat(true, isValidJson(jsonString.substring(0,10)));
    }

    //TEST TO SEE IF MATCHER WORKS
    @Test
    public void checkIfTestPassesWithValidUrl() throws MalformedURLException {
        //Arrange
        DataExtractor dataExtractor = new DataExtractor();
        Utils util = new Utils();
        Extractor extractor = new Extractor("http://localhost:8888", dataExtractor, util);
        assertThat(true, IsValidUrl(extractor.getURL()));
    }

    @Test
    public void testWithLengthMatcher() throws MalformedURLException {
        //Arrange
        String url = "http://localhost:8888";
        DataExtractor dataExtractor = new DataExtractor();
        Utils util = new Utils();
        Extractor extractor = new Extractor(url, dataExtractor, util);
        assertThat(url, length(is(extractor.getURL().length())));
    }

    public static Matcher<String> length(Matcher<? super Integer> matcher) {
        return new FeatureMatcher<String, Integer>(matcher, "a String of length :", "length") {
            @Override
            protected Integer featureValueOf(String actual) {
                return actual.length();
            }
        };
    }
}
