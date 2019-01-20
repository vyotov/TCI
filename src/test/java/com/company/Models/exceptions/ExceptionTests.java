package com.company.Models.exceptions;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ExceptionTests {

    private Extractor extractor;
    private DataExtractor dataExtractor = new DataExtractor();

    @Before
    public void before() throws MalformedURLException {
        extractor = new Extractor("http://localhost:8888");
        dataExtractor = new DataExtractor();

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

    @Test(expected = NullPointerException.class)
    public void testSearchByKeyWord() throws IOException, ClassNotFoundException, NullPointerException {
        extractor.getJsonForSearchByKeyWord(null);
    }

    @Test(expected = RuntimeException.class)
    public void testParseMovieDateExtractor() throws IOException {
        dataExtractor.parseMovie(null);
    }


}
