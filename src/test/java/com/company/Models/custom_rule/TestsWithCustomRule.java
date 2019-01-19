package com.company.Models.custom_rule;

import com.company.Models.Book;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class TestsWithCustomRule {

    private Extractor extractor;

    @Rule
    public InitializationRule rule = new InitializationRule(extractor);

    @Test
    public void testSearchById() throws IOException, ClassNotFoundException {
        //Act
        Book book = new Book();
        book.setCategory("Books");
        book.setName("Ccclean Code: A Handbook of Agile Software Craftsmanship");
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


}
