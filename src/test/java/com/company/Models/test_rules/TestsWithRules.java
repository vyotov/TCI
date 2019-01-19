package com.company.Models.test_rules;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.internal.matchers.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestsWithRules {

    @Rule
    public ExpectedException rule = ExpectedException.none();

    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
    }

    @Test
    public void testSearchByIdWithRule() throws IOException, ClassNotFoundException {
        rule.expect(IllegalArgumentException.class);
        extractor.searchById("");
    }
    @Test
    public void testFindCategoryWithRule()throws ClassNotFoundException{
        rule.expect(ClassNotFoundException.class);
        extractor.findCategory("");
    }
    @Test
    public void testIfNotNullWithRule() throws NullPointerException, IOException, ClassNotFoundException {
        rule.expect(NullPointerException.class);
        extractor.searchById(".");
    }



}
