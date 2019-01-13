package com.company.Models.regularTests;
import com.company.Models.Movie;
import com.company.Models.Music;
import org.junit.Before;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MusicTest{

    @Test
    public void IsGettingRightName() {
        //Arrange
        Music music = new Music();
        music.setName("Elvis Forever");
        String expected = "Elvis Forever";
        //Act
        String actual = music.getName();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRgihtCategory() {
        //Arrange
        Music music = new Music();
        music.setCategory("Music");
        String expected = "Music";
        //Act
        String actual = music.getCategory();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightGenre() {
        //Arrange
        Music music = new Music();
        music.setGenre("Rock");
        String expected = "Rock";
        //Act
        String actual = music.getGenre();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightFormat() {
        //Arrange
        Music music = new Music();
        music.setFormat("Vinyl");
        String expected = "Vinyl";
        //Act
        String actual = music.getFormat();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightYear() {
        //Arrange
        Music music = new Music();
        music.setYear("2015");
        String expected = "2015";
        //Act
        String actual = music.getYear();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightArtist() {
        //Arrange
        Music music = new Music();
        music.setArtist("Elvis Presley");
        String expected = "Elvis Presley";
        //Act
        String actual = music.getArtist();
        //Assert
        Assert.assertEquals(expected,actual);
    }
}