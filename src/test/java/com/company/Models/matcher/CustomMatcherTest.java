package com.company.Models.matcher;

import com.company.Models.custom_rule.LoggerRule;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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

    private Extractor extractor;
    private DataExtractor dataExtractor;
    private Utils utils;

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor,utils);
        utils = new Utils();
    }

    @Test
    public void shouldPassForPageCount() {
        // Given
        Integer pages = extractor.getPageCount();
        System.out.println("WAHAT: "+pages);
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

    public void checkIsValidJson() {
    }

    //TEST TO SEE IF MATCHER WORKS
    @Test
    public void checkIsValidJsonStringWithCustomMatcherShouldFail() {

        assertThat(false, isValidJson("dsadsada"));
    }

    //TEST TO SEE IF MATCHER WORKS
    @Test
    public void checkIsValidJsonStringWithCustomMatcherShouldPass() {
        assertThat(true, isValidJson("{\"result\":{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\"Fran Walsh\",\"Philippa Boyens\",\"Peter Jackson\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"id\":\"203\",\"time\":0}"));
    }

    //TEST TO SEE IF MATCHER WORKS
    @Test
    public void checkIfIsValidUrlSucceedsWithInCorrectUrl() {
        assertThat(true, IsValidUrl("http://localhost:8888"));
    }

    //TEST TO SEE IF MATCHER WORKS
    @Test
    public void checkIfIsValidUrlFailWithInCorrectUrl() {
        assertThat(false, IsValidUrl("fafdsa"));
    }

}
