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
    public void testSearchById() throws IOException, ClassNotFoundException, IllegalAccessException {
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
    public void testSearchByKeyWord() throws IOException, ClassNotFoundException, IllegalAccessException {
        //Act
        //Reset time
        JSONObject actual = extractor.getJsonForSearchByKeyWord("Mike Judge");
        actual.put("time", 0);
        String expected = "{\"filter\":\"Mike Judge\",\"result\":{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"time\":0}";
        //Assert
        Assert.assertEquals(expected, actual.toString());

    }

    @Test
    public void testGetAllObjects() throws IOException, ClassNotFoundException, IllegalAccessException {
        //Arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        JSONObject actual = extractor.getAllObjects();
        //Reset time
        actual.put("time", 0);
        String expected = "{\"movies\":[{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},{\"title\":\"The Lord of the Rings: The Fellowship of the Ring\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"Blu-ray\",\"year\":\"2001\",\"director\":\"Peter Jackson\",\"writers\":[\"J.R.R. Tolkien\",\"Fran Walsh\",\"Philippa Boyens\",\"Peter Jackson\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},{\"title\":\"The Princess Bride\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"DVD\",\"year\":\"1987\",\"director\":\"Rob Reiner\",\"writers\":[\"William Goldman\"],\"stars\":[\"Cary Elwes\",\"Mandy Patinkin\",\"Robin Wright\",\"Chris Sarandon\",\"Christopher Guest\",\"Wallace Shawn\",\"AndrÃ© the Giant\",\"Fred Savage\",\"Peter Falk\",\"Billy Crystal\"]},{\"title\":\"Forrest Gump\",\"category\":\"Movies\",\"genre\":\"Drama\",\"format\":\"DVD\",\"year\":\"1994\",\"director\":\"Robert Zemeckis\",\"writers\":[\"Winston Groom\",\"Eric Roth\"],\"stars\":[\"Tom Hanks\",\"Rebecca Williams\",\"Sally Field\",\"Michael Conner Humphreys\"]}],\"books\":[{\"name\":\"Refactoring: Improving the Design of Existing Code\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Hardcover\",\"year\":\"1999\",\"authors\":[\"Martin Fowler\",\" Kent Beck\",\" John Brant\",\" William Opdyke\",\" Don Roberts\"],\"publisher\":\"Addison-Wesley Professional\",\"isbn\":\"978-0201485677\"},{\"name\":\"The Clean Coder: A Code of Conduct for Professional Programmers\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Audio\",\"year\":\"2011\",\"authors\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"007-6092046981\"},{\"name\":\"A Design Patterns: Elements of Reusable Object-Oriented Software\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Paperback\",\"year\":\"1994\",\"authors\":[\"Erich Gamma\",\" Richard Helm\",\" Ralph Johnson\",\" John Vlissides\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-0201633610\"},{\"name\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\"category\":\"Books\",\"genre\":\"Tech\",\"format\":\"Ebook\",\"year\":\"2008\",\"authors\":[\"Robert C. Martin\"],\"publisher\":\"Prentice Hall\",\"isbn\":\"978-0132350884\"}],\"music\":[{\"name\":\"Beethoven: Complete Symphonies\",\"category\":\"Music\",\"genre\":\"Clasical\",\"format\":\"CD\",\"year\":\"2012\",\"artist\":\"Ludwig van Beethoven\"},{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"},{\"name\":\"No Fences\",\"category\":\"Music\",\"genre\":\"Country\",\"format\":\"Cassette\",\"year\":\"1990\",\"artist\":\"Garth Brooks\"},{\"name\":\"The Very Thought of You\",\"category\":\"Music\",\"genre\":\"Jaz\",\"format\":\"MP3\",\"year\":\"2008\",\"artist\":\"Nat King Cole\"}],\"time\":0}";
        //Assert
        Assert.assertEquals(expected, actual.toString());

    }

    @Test
    public void testGetJsonForSearchByKeyWord() throws IOException, ClassNotFoundException, IllegalAccessException {
        //Arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        //Act
        //Reset time
        JSONObject actual = extractor.getJsonForSearchByKeyWord("Mike Judge");
        actual.put("time", 0);
        String expected = "{\"filter\":\"Mike Judge\",\"result\":{\"title\":\"Office Space\",\"category\":\"Movies\",\"genre\":\"Comedy\",\"format\":\"Blu-ray\",\"year\":\"1999\",\"director\":\"Mike Judge\",\"writers\":[\"William Goldman\"],\"stars\":[\"Ron Livingston\",\"Jennifer Aniston\",\"David Herman\",\"Ajay Naidu\",\"Diedrich Bader\",\"Stephen Root\"]},\"time\":0}";
        //Assert
        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsError() throws MalformedURLException {
        //Arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        //Assert
        Assert.assertNotEquals(true, extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
        Assert.assertFalse(extractor.checkIfStringContainsOnlyNumbers("cat=Movies"));
    }

    @Test
    public void testIfStringContainsOnlyNumbersMethodReturnsCorrect() throws MalformedURLException {
        //Arrange
        Extractor extractor = new Extractor("http://localhost:8888");

        //Assert
        Assert.assertEquals(true, extractor.checkIfStringContainsOnlyNumbers("203"));
        Assert.assertTrue(extractor.checkIfStringContainsOnlyNumbers("203"));
    }

    @Test
    public void getPageLinks() throws MalformedURLException {
        //Arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        //Act
        int expected = 22;
        int actual = extractor.getPageCount();
        //Assert
        Assert.assertEquals(expected, actual);
    }
}
