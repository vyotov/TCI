package com.company.Models.regularTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.company.Models.Movie;
public class MovieTest {

    /*** Method to initialize a movie object*/
    @Before
    public void setUp() {

    }

    /*** Method to test if getTitle return the correct Title for a given movie.*/
    @Test
    public void getTitle() {
        //Arrange
    Movie movie = new Movie();
    movie.setTitle("Office Space");
    String actual = "Office Space";
        //Act
    String expected = movie.getTitle();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getGenre return the correct Title for a given movie.*/
    @Test
    public void getGenre() {
    }

    /*** Method to test if getFormat return the correct Title for a given movie.*/
    @Test
    public void getFormat() {
    }

    /*** Method to test if getYear return the correct Title for a given movie.*/
    @Test
    public void getYear() {
    }

    /*** Method to test if getDirector return the correct Title for a given movie.*/
    @Test
    public void getDirector() {
    }

    /*** Method to test if getWriters return the correct Title for a given movie.*/
    @Test
    public void getWriters() {
    }

    /*** Method to test if getStars return the correct Title for a given movie.*/
    @Test
    public void getStars() {

    }
}