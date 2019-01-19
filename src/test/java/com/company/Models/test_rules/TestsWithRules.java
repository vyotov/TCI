package com.company.Models.test_rules;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestsWithRules {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
    }

    @Test
    public void testSearchByIdWithRule() throws IOException, ClassNotFoundException {
        exception.expect(IllegalArgumentException.class);
        extractor.searchById("");
    }
}
