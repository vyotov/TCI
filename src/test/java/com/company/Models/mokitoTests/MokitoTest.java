package com.company.Models.mokitoTests;

import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;
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
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        mockedExtractor.getPageLinks("http://localhost:8888");
        //act
        String actual = new Gson().toJson(extractor.searchById("201"));
        when(new Gson().toJson(mockedExtractor.searchById("201"))).thenReturn(actual);
        Object expected = mockedExtractor.searchById("201");
        //assert
        Assert.assertEquals(expected.toString(),actual);
        verify(mockedExtractor).searchById("201");
    }
}