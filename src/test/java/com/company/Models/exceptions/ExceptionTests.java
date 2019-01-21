package com.company.Models.exceptions;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class ExceptionTests {

    private Extractor extractor;
    private DataExtractor dataExtractor = new DataExtractor();
    private Utils utils;

    @Before
    public void before() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor,utils);


    }

    @Test(expected = MalformedURLException.class)
    public void testIsUrlValid() throws MalformedURLException {
        extractor.getPageLinks("323");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchById() throws IOException, ClassNotFoundException, IllegalAccessException {
        extractor.searchById("",new DataExtractor());
    }

    //@Test(expected = ClassNotFoundException.class)
    //public void testFindCategory() throws ClassNotFoundException {
        //extractor.findCategory("");
   // }

    @Test(expected = NullPointerException.class)
    public void testSearchByKeyWord() throws IOException, ClassNotFoundException, NullPointerException, IllegalAccessException {
        extractor.getJsonForSearchByKeyWord(null);
    }

    @Test(expected = RuntimeException.class)
    public void testParseMovieDateExtractor() throws IOException {
        dataExtractor.parseMovie(null);
    }


}
