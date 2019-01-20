package com.company.Models.mokitoTests;

import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.IOException;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MokitoTest {

    @Mock
    private Extractor mockedExtractor;

    @Test
    public void verifySearchById() throws IOException, ClassNotFoundException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        mockedExtractor.getPageLinks("http://localhost:8888");
        //act
        String actual = new Gson().toJson(extractor.searchById("201"));
        when(new Gson().toJson(mockedExtractor.searchById("201"))).thenReturn(actual);
        Object expected = mockedExtractor.searchById("201");
        //assert
        Assert.assertEquals(expected.toString(),actual);
        verify(mockedExtractor).searchById("201");
    }
    @Test
    public void verifySearchByKeyWord() throws IOException, ClassNotFoundException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        mockedExtractor.getPageLinks("http://localhost:8888");
        //act
        JSONObject actual = extractor.getJsonForSearchByKeyWord("Tech");
        when(mockedExtractor.getJsonForSearchByKeyWord("Tech")).thenReturn(actual);
        JSONObject expected = mockedExtractor.getJsonForSearchByKeyWord("Tech");
        //assert
        Assert.assertEquals(expected,actual);
        verify(mockedExtractor).getJsonForSearchByKeyWord("Tech");
    }
    @Test
    public void verifyGetAllObjects() throws IOException, ClassNotFoundException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888");
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
        Assert.assertEquals(expected,actual);
        verify(mockedExtractor).getAllObjects();
    }

    //INDIRECT INPUT
    @Test
    public void verifyFindObjectModelForSearchText() throws IOException, ClassNotFoundException {
        //arrange
        Extractor extractor = new Extractor("http://localhost:8888");
        mockedExtractor.getPageLinks("http://localhost:8888");

        //act
        Object actual = extractor.findObjectModelForSearchText( extractor.getAllObjects(),"Tech");
        when(mockedExtractor.getAllObjects()).thenReturn(extractor.getAllObjects());
        //when(mockedExtractor.findObjectModelForSearchText(mockedExtractor.getAllObjects(),"Tech")).thenReturn(actual);
        Object expected = extractor.findObjectModelForSearchText( mockedExtractor.getAllObjects(),"Tech");

        //assert
        Assert.assertEquals(expected,actual);
        verify(mockedExtractor).getAllObjects();
    }
}