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
    String expected = "Office Space";
        //Act
    String actual = movie.getTitle();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getGenre return the correct Title for a given movie.*/
    @Test
    public void getGenre() {
        //Arrange
        Movie movie = new Movie();
        movie.setGenre("Comedy");
        String expected = "Comedy";
        //Act
        String actual = movie.getGenre();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getFormat return the correct Title for a given movie.*/
    @Test
    public void getFormat() {
        //Arrange
        Movie movie = new Movie();
        movie.setFormat("Blu-ray");
        String expected = "Blu-ray";
        //Act
        String actual = movie.getFormat();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getYear return the correct Title for a given movie.*/
    @Test
    public void getYear() {
        //Arrange
        Movie movie = new Movie();
        movie.setYear("1999");
        String expected = "1999";
        //Act
        String actual = movie.getYear();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getDirector return the correct Title for a given movie.*/
    @Test
    public void getDirector() {
        //Arrange
        Movie movie = new Movie();
        movie.setDirector("Mike Judge");
        String expected = "Mike Judge";
        //Act
        String actual = movie.getDirector();
        //Assert
        Assert.assertEquals(expected,actual);
    }

    /*** Method to test if getWriters return the correct Title for a given movie.*/
    @Test
    public void getWriters() {
        //Arrange
        Movie movie = new Movie();
        List<String> expected = new ArrayList<>();
        expected.add("Mike Judge");
        movie.setWriters(expected);
        //Act
        List<String> actual = movie.getWriters();
        //Assert
        Assert.assertEquals(actual,expected);
    }

    /*** Method to test if getStars return the correct Title for a given movie.*/
    @Test
    public void getStars() {
        //Arrange
        Movie movie = new Movie();
        List<String> expected = new ArrayList<>();
        expected.add("Ron Livingston");
        expected.add("Jennifer Aniston");
        expected.add("David Herman");
        expected.add("Ajay Naidu");
        expected.add("Diedrich Bader");
        expected.add("Stephen Root");
        movie.setStars(expected);
        //Act
        List<String> actual = movie.getStars();
        //Assert
        Assert.assertEquals(actual,expected);

    }

    /*** Method to test if getWriters return the correct Title for a given movie.*/
    @Test
    public void getCategory() {
        //Arrange
        Movie movie = new Movie();
        movie.setCategory("Movies");
        String expected = "Movies";
        //Act
        String actual = movie.getCategory();
        //Assert
        Assert.assertEquals(expected,actual);
    }
}