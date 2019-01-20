package com.company.Models.matcher;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.Models.custom_rule.LoggerRule;
import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.hamcrest.*;
import java.net.MalformedURLException;

import static com.company.Models.matcher.IsPage.page;
import static org.hamcrest.CoreMatchers.*;


public class CustomMatcherTest {
    @Rule
    public LoggerRule performanceLogger = new LoggerRule();

    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor("http://localhost:8888");
    }
    @Test
    public void shouldPassForPageCount() throws Exception {
        // Given
        int pages = extractor.getPageCount();
        // Then
        assertThat(pages, is(page()));
    }

    private void assertThat(int pages, Matcher<Extractor> extractorMatcher) {
    }

    @Test
    public void shouldFailForPageCount() throws Exception {
        // Given
        int pages = 0;
        // Then
        assertThat(0, is(not(page())));
    }




}
