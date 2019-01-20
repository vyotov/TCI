package com.company.Models.matcher;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.Models.custom_rule.LoggerRule;
import com.company.SearchAlgorithms.Extractor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class HamcrestMatcherTest {

    @Rule
    public LoggerRule performanceLogger = new LoggerRule();

    private Extractor extractor;

    @Before
    public void setup() throws MalformedURLException {
        extractor = new Extractor("http://localhost:8888");
    }

    @Test
    public void testSearchByIdForBookModel() throws IOException, ClassNotFoundException {
        //Assert
        assertThat(extractor.searchById("102"), instanceOf(Book.class));
    }

    @Test
    public void testSearchByIdForMusicModel() throws IOException, ClassNotFoundException {
        //Assert
        assertThat(extractor.searchById("302"), instanceOf(Music.class));
    }

    @Test
    public void testSearchByIdForMovieModel() throws IOException, ClassNotFoundException {
        //Assert
        assertThat(extractor.searchById("202"), instanceOf(Movie.class));
    }
}
