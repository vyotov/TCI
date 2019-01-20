package com.company.Models.matcher;

import com.company.Models.custom_rule.LoggerRule;
import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.net.MalformedURLException;

import static com.company.Models.matcher.custom.IsPage.page;
import static com.company.Models.matcher.custom.ValidateJsonString.isValidJson;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomMatcherTest {
    @Rule
    public LoggerRule performanceLogger = new LoggerRule();

    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor("http://localhost:8888");
    }

    @Test
    public void shouldPassForPageCount() {
        // Given
        int pages = extractor.getPageCount();
        // Then
        assertThat(pages, is(page()));
    }

    @Test
    public void shouldFailForPageCount() {
        // Given
        int pages = 0;
        // Then
        assertThat(0, is(not(page())));
    }

    @Test
    public void checkIsValidJsonStringWithCustomMatcherShouldFail() {
        assertThat(false, isValidJson("dsadsada"));
    }

    @Test
    public void checkIsValidJsonStringWithCustomMatcherShouldPass() {
        assertThat(true, isValidJson("{\"result\":{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\"Fran Walsh\",\"Philippa Boyens\",\"Peter Jackson\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"id\":\"203\",\"time\":0}"));
    }


}
