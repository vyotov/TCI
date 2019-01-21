package com.company.Models.mokitoTests;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MokitoTest {
    @Mock
    private DataExtractor mockedDataExtractor;
    @Mock
    private Extractor mockedExtractor;
    @Mock
    private Element table;

    @Test
    public void verifySearchById() throws IOException, ClassNotFoundException, IllegalAccessException {
        //arrange
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

        //mockedDataExtractor = new();

        when(mockedDataExtractor.parseMovie(table)).thenReturn(movie);
        Extractor extractor = new Extractor("http://localhost:8888", mockedDataExtractor);
        Object obj = extractor.searchById("202");
        Assert.assertEquals(obj,movie);

        //indirefct output change instace of another object    reads writes
        //act


        // assert

//        Book b = new book("abc");
//        assert(obj == expected);
//
//        mockedExtractor.getPageLinks("http://localhost:8888");
//        //act
//        String actual = new Gson().toJson();
//
//        when(new Gson().toJson(mockedExtractor.searchById("201"))).thenReturn(actual);
//        Object expected = mockedExtractor.searchById("201");
//        //assert
//        Assert.assertEquals(expected.toString(), actual);
//        verify(mockedExtractor).searchById("201");
    }

    @Test
    public void verifySearchByKeyWord() throws IOException, ClassNotFoundException, IllegalAccessException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888",mockedDataExtractor);
        mockedExtractor.getPageLinks("http://localhost:8888");
        //act
        JSONObject actual = extractor.getJsonForSearchByKeyWord("Tech");
        when(mockedExtractor.getJsonForSearchByKeyWord("Tech")).thenReturn(actual);
        JSONObject expected = mockedExtractor.getJsonForSearchByKeyWord("Tech");
        //assert
        Assert.assertEquals(expected, actual);
        verify(mockedExtractor).getJsonForSearchByKeyWord("Tech");
    }

    @Test
    public void verifyGetAllObjects() throws IOException, ClassNotFoundException, IllegalAccessException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888",mockedDataExtractor);
        mockedExtractor.getPageLinks("http://localhost:8888");
        //act
        JSONObject actual = extractor.getAllObjects();
        when(mockedExtractor.getAllObjects()).thenReturn(actual);
        JSONObject expected = mockedExtractor.getAllObjects();
        //        Indirect Input
        //        Object stub = extractor.findObjectModelForSearchText(expected,"Tech");
        //        Object actuall = extractor.findObjectModelForSearchText(actual,"Tech");
        //        //assert
        //        Assert.assertEquals(stub,actuall);

        //assert
        Assert.assertEquals(expected, actual);
        verify(mockedExtractor).getAllObjects();
    }

    //INDIRECT INPUT
    @Test
    public void verifyFindObjectModelForSearchText() throws IOException, ClassNotFoundException, IllegalAccessException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888",mockedDataExtractor);
        mockedExtractor.getPageLinks("http://localhost:8888");

        //act
        Object actual = extractor.findObjectModelForSearchText(extractor.getAllObjects(), "Tech");
        when(mockedExtractor.getAllObjects()).thenReturn(extractor.getAllObjects());
        //when(mockedExtractor.findObjectModelForSearchText(mockedExtractor.getAllObjects(),"Tech")).thenReturn(actual);
        Object expected = extractor.findObjectModelForSearchText(mockedExtractor.getAllObjects(), "Tech");

        //assert
        Assert.assertEquals(expected, actual);
        verify(mockedExtractor).getAllObjects();
    }

    @Test public void verifyMockedDummy() throws IOException
    {
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
       // Extractor extractor = new Extractor("http://localhost:8888");
       // table = extractor.getTable();

        Movie mockedMovie = mock(Movie.class);

       // MailServer mailServer = mock(MailServer.class);
       // Messenger sut = new Messenger(mailServer, templateEngine);
       // sut.sendMessage(client, template);
       // verify(mailServer).send("some@email.com", msgContent);
        //arrange
    }
}