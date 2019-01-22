package com.company.Models.mokitoTests;

import com.company.Models.Book;
import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.*;

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

    // MOCKITO EXCEPTIONS
    @Test(expected = ClassNotFoundException.class)
    public void throwException() throws IllegalAccessException, IOException, ClassNotFoundException {
        when(mockedExtractor.getAllObjects())
                .thenThrow(new ClassNotFoundException());
        mockedExtractor.getAllObjects();
//        JSONObject actual = mockedExtractor.getAllObjects();
//        String expected = "{\"name\":\"Elvis Forever\",\"category\":\"Music\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":\"2015\",\"artist\":\"Elvis Presley\"}";
//        Assert.assertEquals(expected, actual.toString());
    }

    //INDIRECT INPUT
    @Test
    public void testSearchByIdForMovie() throws IOException, ClassNotFoundException, IllegalAccessException {
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
    public void testSearchByIdForMusic() throws IOException, ClassNotFoundException, IllegalAccessException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        //arrange
        Music music = getMusic();
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MUSIC);
        when(mockedDataExtractor.parseMusic(element)).thenReturn(music);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        Object obj = extractor.searchById("302", mockedDataExtractor);
        Assert.assertEquals(obj, music);
    }

    @Test
    public void testSearchByIdForBook() throws IOException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=102";
        //arrange
        Book book = getBook();
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.BOOKS);
        when(mockedDataExtractor.parseBook(element)).thenReturn(book);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        Object obj = extractor.searchById("102", mockedDataExtractor);
        Assert.assertEquals(obj, book);
    }
    //INDIRECT OUTPUT
    @Test
    public void verifyParseBook() throws IOException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=102";
        //arrange
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.BOOKS);
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
    public void verifyParseMovie() throws IOException, ClassNotFoundException {
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        //arrange
        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedUtils.getElement()).thenReturn(element);
        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        //Act
        extractor.searchById("202", mockedDataExtractor);
        //assert
        verify(mockedDataExtractor).parseMovie(element);
    }
    //DIRECT INPUT - sut if method called on param
    @Test
    public void verifyIfParseMovie() throws IOException, ClassNotFoundException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        Elements elements = mock(Elements.class);
        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);

        when(element.getElementsByTag("th")).thenReturn(elements);
        when(elements.size()).thenReturn(0); //skip for loop

        when(element.getElementsByTag("td")).thenReturn(elements);
        when(mockedUtils.getElement()).thenReturn(element);
        //act
        dataExtractor.parseMovie(element);
        //assert
        verify(element, times(1)).getElementsByTag("th");
        verify(element, times(1)).getElementsByTag("td");

    }
    //DIRECT OUTPUT - sut return value
    @Test
    public void verifyMusicGenre() throws IOException, ClassNotFoundException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        String genre = "Rock";

        Elements elementsKey = mock(Elements.class);
        Elements elementsVaule = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);

        DataExtractor dataExtractor =  new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(elementsKey.size()).thenReturn(1);
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsVaule.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Genre");
        when(tmpValue.text()).thenReturn(genre);

        when(element.getElementsByTag("td")).thenReturn(elementsVaule);
        when(mockedUtils.getElement()).thenReturn(element);

        Music music = dataExtractor.parseMusic(element);
        //Assert
        Assert.assertThat(music,hasProperty("genre",  Matchers.is(genre)));
    }

    @Test
    public void verifyMusicArtist() throws IOException, ClassNotFoundException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        String artist = "Elvis Presley\n";

        Elements elementsKey = mock(Elements.class);
        Elements elementsVaule = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);

        DataExtractor dataExtractor =  new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(element.getElementsByTag("td")).thenReturn(elementsVaule);
        when(elementsKey.size()).thenReturn(1);
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsVaule.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Artist");
        when(tmpValue.text()).thenReturn(artist);

        when(mockedUtils.getElement()).thenReturn(element);
        Music music = dataExtractor.parseMusic(element);
        //Assert
        Assert.assertThat(music,hasProperty("artist",  Matchers.is(artist)));
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

    private Music getMusic(){
        Music music = new Music();
        music.setName("Elvis Forever");
        music.setGenre("Rock");
        music.setFormat("Vinyl");
        music.setYear("2015");
        music.setArtist("Elvis Presley");
        music.setCategory("Music");
        return music;
    }


}