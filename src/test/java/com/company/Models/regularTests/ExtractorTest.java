package com.company.Models.regularTests;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
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
    public void testSearchByIdForMovieModel() throws IOException {
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
    public void testSearchByIdForBookModel() throws IOException {
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
    public void testSearchByIdForMusicModel() throws IOException {
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

    public void testSearchByIdJson() throws IOException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        //Act
        JSONObject actual =  extractor.getJsonResultForSearchById("getJsonResultForSearchById");
    }

    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsCorrect() {
        //Arrange
        Extractor extractor = new Extractor();
        //Assert
        Assert.assertEquals(true, extractor.checkIfStringContainsOnlyNumbers("203"));
        Assert.assertTrue(extractor.checkIfStringContainsOnlyNumbers("203"));
    }

    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsError() {
        //Arrange
        Extractor extractor = new Extractor();
        //Assert
        Assert.assertNotEquals(true, extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
        Assert.assertFalse(extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
    }

    @Test
    public void testGetJsonForSearchByKeyWord() throws IOException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        //Act
        //Reset time
        JSONObject actual = extractor.getJsonForSearchByKeyWord("Mike Judge");
        actual.put("time", 0);
        String expected = "{\"filter\":\"Mike Judge\",\"result\":{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"time\":0}";
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void getAllObjects() throws IOException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        JSONObject actual = extractor.getAllObjects();
        //Reset time
        actual.put("time", 0);
        String expected = "{\"movies\":[{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\"Fran Walsh\",\"Philippa Boyens\",\"Peter Jackson\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},{\"title\":\"The Princess Bride\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"DVD\",\"year\":\"1987\",\"director\":\"Rob Reiner\",\"writers\":[\"William Goldman\"],\"stars\":[\"Cary Elwes\",\"Mandy Patinkin\",\"Robin Wright\",\"Chris Sarandon\",\"Christopher Guest\",\"Wallace Shawn\",\"AndrÃ© the Giant\",\"Fred Savage\",\"Peter Falk\",\"Billy Crystal\"]},{\"title\":\"Forrest Gump\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"DVD\",\"year\":\"1994\",\"director\":\"Robert Zemeckis\",\"writers\":[\"Winston Groom\",\"Eric Roth\"],\"stars\":[\"Tom Hanks\",\"Rebecca Williams\",\"Sally Field\",\"Michael Conner Humphreys\"]}],\"books\":[{\"name\":\"Refactoring: Improving the Design of Existing Code\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Hardcover\",\"year\":\"1999\",\"authors\":[\"Martin Fowler\",\" Kent Beck\",\" John Brant\",\" William Opdyke\",\" Don Roberts\"],\"publisher\":\"Addison-Wesley Professional\",\"isbn\":\"978-0201485677\"},{\"name\":\"The Clean Coder: A Code of Conduct for Professional Programmers\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Audio\",\"year\":\"2011\",\"authors\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"007-6092046981\"},{\"name\":\"A Design Patterns: Elements of Reusable Object-Oriented Software\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Paperback\",\"year\":\"1994\",\"authors\":[\"Erich Gamma\",\" Richard Helm\",\" Ralph Johnson\",\" John Vlissides\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-0201633610\"},{\"name\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Ebook\",\"year\":\"2008\",\"authors\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-0132350884\"}],\"music\":[{\"name\":\"Beethoven: Complete Symphonies\",\"category\":\"Music\",\"genre\":\"Clasical\",\"format\":\"CD\",\"year\":\"2012\",\"artist\":\"Ludwig van Beethoven\"},{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"},{\"name\":\"No Fences\",\"category\":\"Music\",\"genre\":\"Country\",\"format\":\"Cassette\",\"year\":\"1990\",\"artist\":\"Garth Brooks\"},{\"name\":\"The Very Thought of You\",\"category\":\"Music\",\"genre\":\"Jaz\",\"format\":\"MP3\",\"year\":\"2008\",\"artist\":\"Nat King Cole\"}],\"time\":0}";
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void getTimeDuration() {
    }

}