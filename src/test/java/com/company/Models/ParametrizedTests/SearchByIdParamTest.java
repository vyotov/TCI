package com.company.Models.ParametrizedTests;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SearchByIdParamTest {

    private Object expectedObject;
    private Extractor extractor;
    private String expectedInput;


    public SearchByIdParamTest(Object object,String input) {
        this.expectedObject = object;
        this.expectedInput = input;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Object[][] data = new Object[][]{{getBook(),"102"}, {getMovie(),"202"}, {getMusic(),"302"}};
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
    public void setup() {
        extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
    }

    @Test
    public void SearchMusicById() throws IOException {
        String actual = new Gson().toJson(extractor.searchById(expectedInput));
        String expected = new Gson().toJson(expectedObject);
        //Assert
        Assert.assertEquals(expected, actual);
    }
}
