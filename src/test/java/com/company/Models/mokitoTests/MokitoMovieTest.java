package com.company.Models.mokitoTests;

import com.company.Models.Movie;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MokitoMovieTest {

    private static final String MOVIE_NAME = "The Lord of the Rings: The Fellowship of the Ring";
    private static final String CATEGORY = "Movies";
    private static final String GENRE = "Drama";
    private static final String FORMAT = "Blu-ray";
    private static final String YEAR = "2001";
    private static final String DIRECTOR = "Peter Jackson";
    private static final List<String> WRITERS = new ArrayList<>();
    private static final List<String> STARS = new ArrayList<>();
    private void FillInWritersAndStars(){
        WRITERS.add("J.R.R. Tolkien");
        WRITERS.add("Fran Walsh");
        WRITERS.add("Philippa Boyens");
        WRITERS.add("Peter Jackson");

        STARS.add("Ron Livingston");
        STARS.add("Jennifer Aniston");
        STARS.add("David Herman");
        STARS.add("Ajay Naidu");
        STARS.add("Diedrich Bader");
        STARS.add("Stephen Root");
    }

    @Test
    public void verifySpyMovieByTitle()
    {
        //arrange
        //spy
        Movie spyMovie = spy(new Movie());
        spyMovie.getTitle();
        //verify
        verify(spyMovie).getTitle();
        //assert
        Assert.assertEquals(null,spyMovie.getTitle());
    }
    //Mock Test
    @Test
    public void verifyMockedMovieByTitle() throws IllegalArgumentException{
        // arrange
        Movie movie = mock(Movie.class);
        //act
        movie.getTitle();
        //assert
        verify(movie).getTitle();
        Assert.assertEquals(null,movie.getTitle());

    }
    //Stub Test
    @Test
    public void shouldStubFakeObj()
    {
        //arrange
        FillInWritersAndStars();
        //spy
        Movie spyMovie = spy(new Movie(MOVIE_NAME,CATEGORY,GENRE,FORMAT,YEAR,DIRECTOR,WRITERS,STARS));
        spyMovie.getTitle();
        //stub
        when(spyMovie.getTitle()).thenReturn(MOVIE_NAME);
        Assert.assertEquals(MOVIE_NAME, spyMovie.getTitle());
    }

}
