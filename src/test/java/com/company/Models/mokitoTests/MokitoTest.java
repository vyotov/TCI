package com.company.Models.mokitoTests;

import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MokitoTest {
    @Mock
    private DataExtractor mockedDataExtractor;
    @Mock
    private Extractor mockedExtractor;
    @Mock
    private Utils mockedUtils;
    @Mock
    private Element element;
    @Mock
    private HashSet<String> mockedLinks;
    //MOCKITO DUMMY OBJ
    @Test
    public void mockitoDummyObjectTest()throws IOException, ClassNotFoundException, IllegalAccessException {
//        //Arrange
//        String url = "http://localhost:8888";
//        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
//
//        Movie movie = getMovie();
//
//        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
//        when(mockedDataExtractor.parseMovie(element)).thenReturn(movie);
//        when(mockedUtils.getElement()).thenReturn(element);
//        //Creation of SUT
//        // mockedDataExtractor and mockedUtiles are injected into SUT
//        Extractor extractor = new Extractor(url,mockedDataExtractor,mockedUtils);
//        //Execution of the SUT’s method using a dummy mockedDataExtractor object as one of the parameters
//        extractor.searchById("202",mockedDataExtractor);

    }
    // MOCKITO EXCEPTIONS
    @Test(expected = ClassNotFoundException.class)
    public void throwException() throws IllegalAccessException, IOException, ClassNotFoundException {
        when(mockedExtractor.getAllObjects())
                .thenThrow(new ClassNotFoundException());
        mockedExtractor.getAllObjects();
        JSONObject actual = mockedExtractor.getAllObjects();
        String expected = "{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"}";
        Assert.assertEquals(expected,actual.toString());
    }

    //INDIRECT INPUT
    @Test
    public void verifyParseMovie() throws IOException, IllegalAccessException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        //arrange
        Movie movie = getMovie();

        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url,mockedDataExtractor,mockedUtils);

        extractor.searchById("202",mockedDataExtractor);
        verify(mockedDataExtractor).parseMovie(element);
    }
    @Test
    public void testSearchById() throws IOException, ClassNotFoundException, IllegalAccessException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        //arrange
        Movie movie = getMovie();
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedDataExtractor.parseMovie(element)).thenReturn(movie);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor,mockedUtils);
        Object obj = extractor.searchById("202", mockedDataExtractor);
        Assert.assertEquals(obj, movie);
    }

    @Test
    public void verifyGetAllObjects() throws IOException, ClassNotFoundException, IllegalAccessException {


        mockedExtractor.getPageCount();
        //behave
        when(mockedExtractor.getPageCount()).thenReturn(22);
        verify(mockedExtractor).getPageCount();

    }
    //INDIRECT OUTPUT
    @Test
    public void testPageLinks() throws MalformedURLException {

    }

    private Movie getMovie() {
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

}