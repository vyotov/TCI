package com.company.Models.ParametrizedTests;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SearchByIdParamTest {

    private Object expectedObject;
    private Extractor extractor;
    private DataExtractor dataExtractor;
    private String expectedInput;
    private Utils utils;


    public SearchByIdParamTest(Object object, String input) {
        utils = new Utils();
        this.expectedObject = object;
        this.expectedInput = input;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{getBook(), "102"}, {getMovie(), "202"}, {getMusic(), "302"}};
        return Arrays.asList(data);
    }

    private static Movie getMovie() {
        Movie movie = new Movie();
        movie.setCategory("Movies");
        movie.setGenre("Comedy");
        movie.setFormat("Blu-ray");
        movie.setYear("1999");
        movie.setFormat("Blu-ray");
        movie.setDirector("Mike Judge");
        movie.setTitle("Office Space");
        movie.setWriters(Arrays.asList("William Goldman"));
        movie.setStars(Arrays.asList("Ron Livingston", "Jennifer Aniston", "David Herman", "Ajay Naidu", "Diedrich Bader", "Stephen Root"));
        return movie;
    }

    private static Book getBook() {
        Book book = new Book();
        book.setCategory("Books");
        book.setName("Clean Code: A Handbook of Agile Software Craftsmanship");
        book.setGenre("Tech");
        book.setFormat("Ebook");
        book.setYear("2008");
        book.setAuthors(Arrays.asList("Robert C. Martin"));
        book.setPublisher("Prentice Hall");
        book.setIsbn("978-0132350884");
        //
        return book;
    }

    private static Music getMusic() {
        Music music = new Music();
        music.setName("Elvis Forever");
        music.setGenre("Rock");
        music.setFormat("Vinyl");
        music.setYear("2015");
        music.setArtist("Elvis Presley");
        music.setCategory("Music");
        return music;
    }

    @Before
    public void setup() throws MalformedURLException {
        dataExtractor = new DataExtractor();
        extractor = new Extractor("http://localhost:8888",dataExtractor,utils);
    }

    /*
    INPUT  Direct call of the calculateBonus() method of the SUT with clientId and payment arguments
    OUTPUT bonus value returned by the SUT to the test class after it called the calculateBonus() method
     */
    @Test
    public void shouldPassSearchByIdOutput() throws IOException, ClassNotFoundException, IllegalAccessException {
        String actual = new Gson().toJson(extractor.searchById(expectedInput,dataExtractor));
        String expected = new Gson().toJson(expectedObject);
        //Assert
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void shouldPassSearchById() throws IOException, ClassNotFoundException, IllegalAccessException {
        String actual = new Gson().toJson(extractor.searchById(expectedInput,dataExtractor));
        String expected = new Gson().toJson(expectedObject);
        //Assert
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void shouldFailSearchById() throws IOException, ClassNotFoundException, IllegalAccessException {
        //arrange
        String actual = new Gson().toJson(extractor.searchById(expectedInput,dataExtractor));
        //Assert
        Assert.assertNotEquals(null, actual);
    }

}
