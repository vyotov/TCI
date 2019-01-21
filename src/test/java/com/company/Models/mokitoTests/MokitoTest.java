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
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MokitoTest {
    @Mock
    private DataExtractor mockedDataExtractor;
    @Mock
    private Utils mockedUtils;
    @Mock
    private Element element;

    @Test
    public void testSearchByIdForMovie() throws IOException, ClassNotFoundException, IllegalAccessException {

        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        //arrange
        Movie movie = getMovie();
        when(mockedDataExtractor.getUrl()).thenReturn(detailPage);
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedDataExtractor.parseMovie(element)).thenReturn(movie);
        when(mockedUtils.getElement()).thenReturn(element);
        //when()
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        //when(mockedExtractor.searchById("202")).thenReturn(movie);
        Object obj = extractor.searchById("202", mockedDataExtractor);
        Assert.assertEquals(obj, movie);
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