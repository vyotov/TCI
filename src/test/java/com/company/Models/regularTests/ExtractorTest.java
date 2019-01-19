package com.company.Models.regularTests;

import com.company.Models.Book;
import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

public class ExtractorTest {


    /**
     * Method to test if getPageLinks() returns the expected number of hyper-links we are about to crawl.
     */
    @Test
    public void getPageLinks() throws MalformedURLException {
        //Arrange
        Extractor extractor = new Extractor();
        //Act
        extractor.getPageLinks("http://localhost:8888");
        int expected = 22;
        int actual = extractor.getPageCount();
        //Assert
        Assert.assertEquals(expected, actual);
    }


    /**
     * Method to test if SearchById returns the correct Movie model
     */
    @Test
    public void testSearchByIdForMovieModel() throws IOException, ClassNotFoundException {
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

    /**
     * Method to test if SearchById returns the correct Book model
     */
    @Test
    public void testSearchByIdForBookModel() throws IOException, ClassNotFoundException {
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

    /**
     * Method to test if SearchById returns the correct Music model
     */
    @Test
    public void testSearchByIdForMusicModel() throws IOException, ClassNotFoundException {
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

    /**
     * Method to test if SearchByIdJson returns the correct Json object
     */
    @Test
    public void testSearchByIdJson() throws IOException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        //Act
        JSONObject actual = extractor.getJsonResultForSearchById("203");
        actual.put("time", 0);
        String expected = "{\"result\":{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\"Fran Walsh\",\"Philippa Boyens\",\"Peter Jackson\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"id\":\"203\",\"time\":0}";
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    /**
     * Method to test if StringContainsOnlyNumbers returns the true if a string with only numbers is passed
     */
    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsCorrect() {
        //Arrange
        Extractor extractor = new Extractor();
        //Assert
        Assert.assertEquals(true, extractor.checkIfStringContainsOnlyNumbers("203"));
        Assert.assertTrue(extractor.checkIfStringContainsOnlyNumbers("203"));
    }

    /**
     * Method to test if StringContainsOnlyNumbers returns the false if a string with not only numbers is passed
     */
    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsError() {
        //Arrange
        Extractor extractor = new Extractor();
        //Assert
        Assert.assertNotEquals(true, extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
        Assert.assertFalse(extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
    }

    /**
     * Method to test if GetJsonForSearchByKeyWord returns the correct JSON object
     */
    @Test
    public void testGetJsonForSearchByKeyWord() throws IOException, ClassNotFoundException {
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

    /**
     * Method to test if FindObjectModelForSearchText returns the correct model if search word from Aenre is used
     */
    @Test
    public void testFindObjectModelForSearchTextByArtist() throws IOException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        String actual = new Gson().toJson(extractor.findObjectModelForSearchText(extractor.getAllObjects(), "Elvis Presley"));
        String expected = "{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"}";
        //Assert
        Assert.assertEquals(expected, actual);
    }

    /**
     * Method to test if FindObjectModelForSearchText returns the correct model if search word from Genre is used
     */
    @Test
    public void testFindObjectModelForSearchTextByGenre() throws IOException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        String actual = new Gson().toJson(extractor.findObjectModelForSearchText(extractor.getAllObjects(), "Vinyl"));
        String expected = "{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"}";
        //Assert
        Assert.assertEquals(expected, actual);
    }

    /**
     * Method to test if findCategory returns the correct category if id for music is passed.
     */
    @Test
    public void testFindCategoryForMusic() throws MalformedURLException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        Category actual = extractor.findCategory("http://localhost:8888/sample_site_to_crawl/details.php?id=301");
        Category expected = Category.MUSIC;
        //Assert
        Assert.assertEquals(expected, actual);
    }

    /**
     * Method to test if findCategory returns the correct category if id for book is passed.
     */
    @Test
    public void testFindCategoryForBook() throws MalformedURLException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        Category actual = extractor.findCategory("http://localhost:8888/sample_site_to_crawl/details.php?id=102");
        Category expected = Category.BOOKS;
        //Assert
        Assert.assertEquals(expected, actual);
    }

    /**
     * Method to test if findCategory returns the correct category if id for movie is passed.
     */
    @Test
    public void testFindCategoryForMovie() throws MalformedURLException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        Category actual = extractor.findCategory("http://localhost:8888/sample_site_to_crawl/details.php?id=202");
        Category expected = Category.MOVIE;
        //Assert
        Assert.assertEquals(expected, actual);
    }

    /**
     * Method to test if getAllObject returns the correct JSON string.
     */
    @Test
    public void testGetAllObjects() throws IOException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        JSONObject actual = extractor.getAllObjects();
        //Reset time
        actual.put("time", 0);
        String expected = "{\"movies\":[{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\"Fran Walsh\",\"Philippa Boyens\",\"Peter Jackson\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},{\"title\":\"The Princess Bride\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"DVD\",\"year\":\"1987\",\"director\":\"Rob Reiner\",\"writers\":[\"William Goldman\"],\"stars\":[\"Cary Elwes\",\"Mandy Patinkin\",\"Robin Wright\",\"Chris Sarandon\",\"Christopher Guest\",\"Wallace Shawn\",\"AndrÃ© the Giant\",\"Fred Savage\",\"Peter Falk\",\"Billy Crystal\"]},{\"title\":\"Forrest Gump\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"DVD\",\"year\":\"1994\",\"director\":\"Robert Zemeckis\",\"writers\":[\"Winston Groom\",\"Eric Roth\"],\"stars\":[\"Tom Hanks\",\"Rebecca Williams\",\"Sally Field\",\"Michael Conner Humphreys\"]}],\"books\":[{\"name\":\"Refactoring: Improving the Design of Existing Code\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Hardcover\",\"year\":\"1999\",\"authors\":[\"Martin Fowler\",\" Kent Beck\",\" John Brant\",\" William Opdyke\",\" Don Roberts\"],\"publisher\":\"Addison-Wesley Professional\",\"isbn\":\"978-0201485677\"},{\"name\":\"The Clean Coder: A Code of Conduct for Professional Programmers\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Audio\",\"year\":\"2011\",\"authors\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"007-6092046981\"},{\"name\":\"A Design Patterns: Elements of Reusable Object-Oriented Software\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Paperback\",\"year\":\"1994\",\"authors\":[\"Erich Gamma\",\" Richard Helm\",\" Ralph Johnson\",\" John Vlissides\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-0201633610\"},{\"name\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Ebook\",\"year\":\"2008\",\"authors\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-0132350884\"}],\"music\":[{\"name\":\"Beethoven: Complete Symphonies\",\"category\":\"Music\",\"genre\":\"Clasical\",\"format\":\"CD\",\"year\":\"2012\",\"artist\":\"Ludwig van Beethoven\"},{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"},{\"name\":\"No Fences\",\"category\":\"Music\",\"genre\":\"Country\",\"format\":\"Cassette\",\"year\":\"1990\",\"artist\":\"Garth Brooks\"},{\"name\":\"The Very Thought of You\",\"category\":\"Music\",\"genre\":\"Jaz\",\"format\":\"MP3\",\"year\":\"2008\",\"artist\":\"Nat King Cole\"}],\"time\":0}";
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }


    @Test
    public void testTimeDurationIfSet() throws IOException, ClassNotFoundException {
        //Arrange
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        extractor.getAllObjects();
        Long actual = extractor.getTimeDuration();
        //Assert
        Assert.assertNotEquals(0, actual.toString());
    }
}
