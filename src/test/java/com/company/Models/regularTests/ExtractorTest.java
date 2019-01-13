package com.company.Models.regularTests;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class ExtractorTest {

    @Test
    public void getPageLinks() {
        //Arrange
        Extractor extractor = new Extractor();
        //Act
        extractor.getPageLinks("http://localhost:8888");
        int expected = 22;
        int actual = extractor.getPageCount();
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchByIdForMovie() throws IOException {
        //Arrange
        Extractor extractor = new Extractor();
        //Act
        extractor.getPageLinks("http://localhost:8888");
        //Act
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

        String actual = new Gson().toJson(extractor.searchById("202"));
        String expect = new Gson().toJson(movie);
        //Check if the 2 JSon objects are similar
        //Assert
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void testSearchByIdForBook() throws IOException {
        //Arrange
        Extractor extractor = new Extractor();

        extractor.getPageLinks("http://localhost:8888");
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
        //Check if the 2 JSon objects are similar
        //Assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchByIdForMusic() throws IOException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        //Act
        Music book = new Music();
        book.setName("Elvis Forever");
        book.setGenre("Rock");
        book.setFormat("Vinyl");
        book.setYear("2015");
        book.setArtist("Elvis Presley");
        book.setCategory("Music");
        String expected = new Gson().toJson(book);
        String actual = new Gson().toJson(extractor.searchById("302"));
        ////Assert
        //Check if the 2 JSon objects are similar
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsCorrect() {
        //Arrange
        Extractor extractor = new Extractor();
        Assert.assertEquals(true, extractor.checkIfStringContainsOnlyNumbers("203"));
    }

    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsError() {
        //Arrange
        Extractor extractor = new Extractor();
        Assert.assertNotEquals(true, extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
    }

    @Test
    public void getAllObjects() throws IOException {
        /*Extractor extractor = new Extractor();
        //Act
        extractor.getPageLinks("http://localhost:8888");
        String expected = "{\"result\":{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\" Fran Walsh\",\" Philippa Boyens\",\" Peter Jackson\"],\"stars\":[\"Ron Livingston\",\" Jennifer Aniston\",\" David Herman\",\" Ajay Naidu\",\" Diedrich Bader\",\" Stephen Root\"]},\"id\":\"203\",\"time\":31}\n";
        //Assert
        Assert.assertEquals(expected, extractor.getAllObjects());getAllObjects*/
    }

    @Test
    public void getTimeDuration() {
    }

    @Test
    public void getPageCount() {
    }
}