package com.company.Models.mokitoTests;

import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

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
    //MOCKITO DUMMY OBJ
    @Test
    public void mockitoDummyObjectTest()throws IOException, ClassNotFoundException, IllegalAccessException {

        String url = "http://localhost:8888";
        //Creation of SUT
        // mockedDataExtractor and mockedUtiles are injected into SUT
        Extractor extractor = new Extractor(url,mockedDataExtractor,mockedUtils);
        //Execution of the SUT’s method using a dummy mockedDataExtractor object as one of the parameters
        extractor.searchById("201",mockedDataExtractor);
        //mockedDataExtractor.parseBook(element);

    }
    //INDIRECT INPUT
    @Test
    public void verifySearchById() throws IOException, ClassNotFoundException, IllegalAccessException {

        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        //arrange
        Movie movie = getMovie();

        when(mockedDataExtractor.getUrl()).thenReturn(detailPage);
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedDataExtractor.parseMovie(element)).thenReturn(movie);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor,mockedUtils);

        Object obj = extractor.searchById("202", mockedDataExtractor);
        Assert.assertEquals(obj, movie);
        //verify(mockedDataExtractor).getUrl();
    }

    @Test
    public void verifyGetAllObjects() throws IOException, ClassNotFoundException, IllegalAccessException {


        mockedExtractor.getPageCount();
        //behave
        when(mockedExtractor.getPageCount()).thenReturn(22);
        verify(mockedExtractor).getPageCount();

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