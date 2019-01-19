package com.company.Models.exceptions;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class ExceptionTests {

    private Extractor extractor;

    @Before
    public void before() {
        extractor = new Extractor();
    }

    @Test(expected = MalformedURLException.class)
    public void testIsUrlValid() throws MalformedURLException {
        extractor.getPageLinks("323");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchById() throws IOException, ClassNotFoundException {
        extractor.searchById("");
    }

    @Test(expected = ClassNotFoundException.class)
    public void testFindCategory() throws ClassNotFoundException {
        extractor.findCategory("");
    }


}