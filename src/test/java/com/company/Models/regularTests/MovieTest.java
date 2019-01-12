package com.company.Models.regularTests;

import com.company.Models.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    String expected = movie.getGenre();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getGenre return the correct Title for a given movie.*/
    @Test
    public void getGenre() {
        //Arrange
        Movie movie = new Movie();
        movie.setGenre("Comedy");
        String actual = "Comedy";
        //Act
        String expected = movie.getTitle();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getFormat return the correct Title for a given movie.*/
    @Test
    public void getFormat() {
        //Arrange
        Movie movie = new Movie();
        movie.setFormat("Blu-ray");
        String actual = "Blu-ray";
        //Act
        String expected = movie.getFormat();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getYear return the correct Title for a given movie.*/
    @Test
    public void getYear() {
        //Arrange
        Movie movie = new Movie();
        movie.setYear("1999");
        String actual = "1999";
        //Act
        String expected = movie.getYear();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getDirector return the correct Title for a given movie.*/
    @Test
    public void getDirector() {
        //Arrange
        Movie movie = new Movie();
        movie.setDirector("Mike Judge");
        String actual = "Mike Judge";
        //Act
        String expected = movie.getFormat();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getWriters return the correct Title for a given movie.*/
    @Test
    public void getWriters() {
        //Arrange
        Movie movie = new Movie();
        List<String> writers = new ArrayList<>();
        writers.add("Mike Judge");
        movie.setWriters(writers);
        //Act
        List<String> expected = movie.getWriters();
        //Assert
        Assert.assertEquals(expected,writers);
    }

    /*** Method to test if getStars return the correct Title for a given movie.*/
    @Test
    public void getStars() {
        //Arrange
        Movie movie = new Movie();
        List<String> stars = new ArrayList<>();
        stars.add("Ron Livingston");
        stars.add("Jennifer Aniston");
        stars.add("David Herman");
        stars.add("Ajay Naidu");
        stars.add("Diedrich Bader");
        stars.add("Stephen Root");
        movie.setWriters(stars);
        //Act
        List<String> expected = movie.getWriters();
        //Assert
        Assert.assertEquals(expected,stars);

    }
}