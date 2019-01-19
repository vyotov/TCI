package com.company.Models.exceptions;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class TestUrlException {

    private Extractor extractor;

    @Before
    public void before() {
        extractor = new Extractor();
    }

    @Test(expected = MalformedURLException.class)
    public void testIsValidURL() throws MalformedURLException {
        extractor.getPageLinks("323");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchById() throws IOException {
        extractor.searchById("");
    }


}
