package com.company.Models.exceptions;

import com.company.SearchAlgorithms.Extractor;
import org.junit.Test;

import java.net.MalformedURLException;

public class TestUrlException {

   private Extractor extractor;

    @Test(expected = MalformedURLException.class)
    public void testIsValidURL() throws MalformedURLException {
        extractor = new Extractor();
        extractor.getPageLinks("323");
    }

}
