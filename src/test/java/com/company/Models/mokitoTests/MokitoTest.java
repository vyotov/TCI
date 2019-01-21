package com.company.Models.mokitoTests;

import com.company.Models.Book;
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

    // MOCKITO EXCEPTIONS
    @Test(expected = ClassNotFoundException.class)
    public void throwException() throws IllegalAccessException, IOException, ClassNotFoundException {
        when(mockedExtractor.getAllObjects())
                .thenThrow(new ClassNotFoundException());
        mockedExtractor.getAllObjects();
        JSONObject actual = mockedExtractor.getAllObjects();
        String expected = "{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"}";
        Assert.assertEquals(expected, actual.toString());
    }

    //INDIRECT INPUT
    @Test
    public void verifyParseMovie() throws IOException, IllegalAccessException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        //arrange
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        //Act
        extractor.searchById("202", mockedDataExtractor);
        verify(mockedDataExtractor).parseMovie(element);
    }


    @Test
    public void verifyParseBook() throws IOException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=102";
        //arrange
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        //Act
        extractor.searchById("102", mockedDataExtractor);
        verify(mockedDataExtractor).parseBook(element);
    }

    @Test
    public void verifyParseMusic() throws IOException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        //arrange
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MUSIC);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        //Act
        extractor.searchById("302", mockedDataExtractor);
        verify(mockedDataExtractor).parseMusic(element);
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
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        Object obj = extractor.searchById("202", mockedDataExtractor);
        Assert.assertEquals(obj, movie);
    }

    @Test
    public void verifyGetAllObjects() {
        mockedExtractor.getPageCount();
        //behave
        when(mockedExtractor.getPageCount()).thenReturn(22);
        verify(mockedExtractor).getPageCount();

    }

    //INDIRECT OUTPUT
    @Test
    public void testPageLinks() {

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

    private Book getBook() {
        Book book = new Book();
        book.setCategory("Books");
        book.setName("Clean Code: A Handbook of Agile Software Craftsmanship");
        book.setGenre("Tech");
        book.setFormat("Ebook");
        book.setYear("2008");
        book.setAuthors(Arrays.asList("Robert C. Martin"));
        book.setPublisher("Prentice Hall");
        book.setIsbn("978-0132350884");
        return book;
    }


}