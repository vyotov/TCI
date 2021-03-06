package com.company.Models.mokitoTests;

import com.company.Models.Book;
import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockitoTest {
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
    public void throwException() throws IOException, ClassNotFoundException {
        when(mockedExtractor.getAllObjects())
                .thenThrow(new ClassNotFoundException());
        mockedExtractor.getAllObjects();
    }

    //INDIRECT INPUT
    @Test
    public void testSearchByIdForMovie() throws IOException, ClassNotFoundException {
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
    public void testSearchByIdForMusic() throws IOException, ClassNotFoundException {
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


    //DIRECT OUTPUT - sut return value
    @Test
    public void verifyMusicGenre() throws IOException {
        //arrange
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        String genre = "Rock";

        Elements elementsKey = mock(Elements.class);
        Elements elementsValue = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);

        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(elementsKey.size()).thenReturn(1);
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsValue.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Genre");
        when(tmpValue.text()).thenReturn(genre);

        when(element.getElementsByTag("td")).thenReturn(elementsValue);

        Music music = dataExtractor.parseMusic(element);
        //Assert
        Assert.assertThat(music, hasProperty("genre", is(genre)));
    }

    @Test
    public void verifyMusicArtist() throws IOException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        String artist = "Elvis Presley";

        Elements elementsKey = mock(Elements.class);
        Elements elementsVaule = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);

        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(element.getElementsByTag("td")).thenReturn(elementsVaule);
        when(elementsKey.size()).thenReturn(1);
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsVaule.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Artist");
        when(tmpValue.text()).thenReturn(artist);

        Music music = dataExtractor.parseMusic(element);
        //Assert
        Assert.assertThat(music, hasProperty("artist", is(artist)));
    }

    @Test
    public void verifyMusicName() throws IOException {
        //arrange
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=302";
        String name = "Elvis Forever";

        Elements elementsKey = mock(Elements.class);
        Elements elementsVaule = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);

        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(element.getElementsByTag("td")).thenReturn(elementsVaule);
        when(elementsKey.size()).thenReturn(1);
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsVaule.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Name");
        when(tmpValue.text()).thenReturn(name);

        Music music = dataExtractor.parseMusic(element);
        //Assert
        Assert.assertThat(music, hasProperty("name", is(name)));
    }

    ////DIRECT INPUT - sut if method is called
    @Test
    public void testSearchByIdForMovieDirect() throws IOException, ClassNotFoundException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        Movie movie = getMovie();

        when(mockedUtils.findCategory(detailPage)).thenReturn(Category.MOVIE);
        when(mockedDataExtractor.parseMovie(element)).thenReturn(movie);
        when(mockedUtils.getElement()).thenReturn(element);

        Extractor extractor = new Extractor(url, mockedDataExtractor, mockedUtils);
        Object obj = extractor.searchById("202", mockedDataExtractor);
        System.out.println(obj);
        Assert.assertNotNull(obj);
    }
    //DIRECT OUTPUT PARAMS - sut if method called on param

    //DIRECT INPUT PARAMS - sut if method called on param
    @Test
    public void verifyIfGetElementsByTagIsCalled() throws IOException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        Elements elements = mock(Elements.class);
        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);

        when(element.getElementsByTag("th")).thenReturn(elements);
        when(elements.size()).thenReturn(0); //skip for loop

        when(element.getElementsByTag("td")).thenReturn(elements);
        //when(mockedUtils.getElement()).thenReturn(element);
        //act
        dataExtractor.parseMovie(element);
        //assert
        verify(element, times(1)).getElementsByTag("th");
        verify(element, times(1)).getElementsByTag("td");

    }

    @Test
    public void verifyIfParseMovieProperty() throws IOException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        String year = "1999";
        Elements mockedElementsKey = mock(Elements.class);
        Elements mockedEementsVaule = mock(Elements.class);
        Element mockedTmpKey = mock(Element.class);
        Element mockedTmpValue = mock(Element.class);
        Movie mockedMovie = mock(Movie.class);

        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);

        when(element.getElementsByTag("th")).thenReturn(mockedElementsKey);
        when(element.getElementsByTag("td")).thenReturn(mockedEementsVaule);
        when(mockedElementsKey.size()).thenReturn(1);
        when(mockedElementsKey.get(0)).thenReturn(mockedTmpKey);
        when(mockedEementsVaule.get(0)).thenReturn(mockedTmpValue);
        when(mockedTmpKey.text()).thenReturn("Year");
        when(mockedTmpValue.text()).thenReturn(year);

        //act
        dataExtractor.parseMovie(element);
        mockedMovie.setYear("1999");
        //assert
        verify(mockedMovie, times(1)).setYear("1999");

    }

    @Test
    public void verifyIfParseBookAuthors() throws IOException {
        //arrange
        String url = "http://localhost:8888";
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=101";
        Elements mockedElementsKey = mock(Elements.class);
        Elements mockedEementsVaule = mock(Elements.class);
        Element mockedTmpKey = mock(Element.class);
        Element mockedTmpValue = mock(Element.class);
        Book mockedBook = mock(Book.class);

        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);
        List<String> authorss = null;
        List<String> authors = new ArrayList<>();
        authors.add("Erich Gamma");
        authors.add("Rchard Helm");
        authors.add("Ralphasdas Johnson");
        authors.add("John Vlissides");


        when(element.getElementsByTag("th")).thenReturn(mockedElementsKey);
        when(element.getElementsByTag("td")).thenReturn(mockedEementsVaule);
        when(mockedElementsKey.size()).thenReturn(1);
        when(mockedElementsKey.get(0)).thenReturn(mockedTmpKey);
        when(mockedEementsVaule.get(0)).thenReturn(mockedTmpValue);
        when(mockedTmpKey.text()).thenReturn("Authors");
        when(mockedTmpValue.text()).thenReturn(String.valueOf(authors));

        //act
        dataExtractor.parseMovie(element);
        mockedBook.setAuthors(authors);
        //assert
        verify(mockedBook, times(1)).setAuthors(authors);

    }

    @Test
    public void verifyIfParseMovie() throws IOException {
        //arrange
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=202";
        Elements elements = mock(Elements.class);
        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);

        when(element.getElementsByTag("th")).thenReturn(elements);
        when(elements.size()).thenReturn(0); //Skip

        when(element.getElementsByTag("td")).thenReturn(elements);
        when(mockedUtils.getElement()).thenReturn(element);
        //act
        dataExtractor.parseMovie(element);
        //assert
        verify(element, times(1)).getElementsByTag("th");
        verify(element, times(1)).getElementsByTag("td");
    }

    @Test
    public void verifyIfMovieStars() throws IOException {
        //arrange
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=201";
        String keyWord = "Tom Hanks";

        Elements elementsKey = mock(Elements.class);
        Elements elementsValue = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);
        String arrayAuthors = "Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys";
        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(element.getElementsByTag("td")).thenReturn(elementsValue);
        when(elementsKey.size()).thenReturn(1); //Loop 1
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsValue.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Stars");
        when(tmpValue.text()).thenReturn(arrayAuthors);
        Movie movie = dataExtractor.parseMovie(element);
        //Assert
        assertThat(movie.getStars(), hasItem(keyWord));
    }

    @Test
    public void verifyIfBookAuthorsCalled() {
        //arrange
        String detailPage = "http://localhost:8888/sample_site_to_crawl/details.php?id=102";
        String keyWord = "Robert C. Martin";

        Elements elementsKey = mock(Elements.class);
        Elements elementsValue = mock(Elements.class);
        Element tmpKey = mock(Element.class);
        Element tmpValue = mock(Element.class);

        String arrayAuthors = "Robert C. Martin";
        DataExtractor dataExtractor = new DataExtractor();
        dataExtractor.setUrl(detailPage);
        //act
        when(element.getElementsByTag("th")).thenReturn(elementsKey);
        when(element.getElementsByTag("td")).thenReturn(elementsValue);
        when(elementsKey.size()).thenReturn(1); //Loop 1
        when(elementsKey.get(0)).thenReturn(tmpKey);
        when(elementsValue.get(0)).thenReturn(tmpValue);
        when(tmpKey.text()).thenReturn("Authors");
        when(tmpValue.text()).thenReturn(arrayAuthors);
        Book movie = dataExtractor.parseBook(element);
        //Assert
        assertThat(movie.getAuthors(), hasItem(keyWord));
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

    private Music getMusic() {
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