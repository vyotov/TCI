package com.company.Models.custom_rule;

import com.company.Models.Book;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

public class TestsWithCustomRule {

    @Rule
    public LoggerRule performanceLogger = new LoggerRule();
    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor("http://localhost:8888");

    }

    @Test
    public void testSearchById() throws IOException, ClassNotFoundException {
        //Act
        Book book = new Book();
        book.setCategory("Books");
        book.setName("Clean Code: A Handbook of Agile Software Craftsmanship");
        book.setGenre("Tech");
        book.setFormat("Ebook");
        book.setYear("2008");
        book.setAuthors(Arrays.asList("Robert C. Martin"));
        book.setPublisher("Prentice Hall");
        book.setIsbn("978-0132350884");
        String actual = new Gson().toJson(extractor.searchById("102"));

        String expected = new Gson().toJson(book);

        //Assert
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testSearchByKeyWord() throws IOException, ClassNotFoundException {
        //Act
        Book book = new Book();
        book.setCategory("Books");
        book.setName("Clean Code: A Handbook of Agile Software Craftsmanship");
        book.setGenre("Tech");
        book.setFormat("Ebook");
        book.setYear("2008");
        book.setAuthors(Arrays.asList("Robert C. Martin"));
        book.setPublisher("Prentice Hall");
        book.setIsbn("978-0132350884");
        JSONObject actual = extractor.getJsonForSearchByKeyWord("978-0132350884");

        String expected = new Gson().toJson(book);

        //Assert
        Assert.assertEquals(expected, actual.toString());

    }


}
