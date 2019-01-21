package com.company.Models.test_rules;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.MalformedURLException;

public class TestsWithRules {

    @Rule
    public ExpectedException rule = ExpectedException.none();
    private Extractor extractor;
    private DataExtractor dataExtractor;

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor);
    }

    @Test
    public void testSearchByIdWithRule() throws IOException, ClassNotFoundException, IllegalAccessException {
        rule.expect(IllegalArgumentException.class);
        extractor.searchById("");
    }

    @Test
    public void testFindCategoryWithRule() throws ClassNotFoundException {
        rule.expect(ClassNotFoundException.class);
        extractor.findCategory("");
    }

    @Test
    public void testIfNotNullWithRule() throws NullPointerException, IOException, ClassNotFoundException, IllegalAccessException {
        rule.expect(NullPointerException.class);
        extractor.searchById("a");
    }

    @Test
    public void testIfStringContainsOnlyNumbersThrowsException() {
        rule.expect(RuntimeException.class);
        extractor.checkIfStringContainsOnlyNumbers(null);
    }

    @Test
    public void testIfModelIsParsed() throws IllegalArgumentException,IOException {
        rule.expect(IllegalArgumentException.class);
        dataExtractor.parseMovie(null);
    }

    @Test
    public void testIfURLIsSet() throws IllegalArgumentException, IOException, IllegalAccessException {
        rule.expect(IllegalArgumentException.class);
        dataExtractor.setUrl("");
    }


}
