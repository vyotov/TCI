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
        String actual = "Elvis Forever";
        //Act
        String expected = music.getName();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRgihtCategory() {
        //Arrange
        Music music = new Music();
        music.setCategory("Music");
        String actual = "Music";
        //Act
        String expected = music.getCategory();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightGenre() {
        //Arrange
        Music music = new Music();
        music.setGenre("Rock");
        String actual = "Rock";
        //Act
        String expected = music.getGenre();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightFormat() {
        //Arrange
        Music music = new Music();
        music.setFormat("Vinyl");
        String actual = "Vinyl";
        //Act
        String expected = music.getFormat();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightYear() {
        //Arrange
        Music music = new Music();
        music.setYear("2015");
        String actual = "2015";
        //Act
        String expected = music.getYear();
        //Assert
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void IsGettingRightArtist() {
        //Arrange
        Music music = new Music();
        music.setArtist("Elvis Presley");
        String actual = "Elvis Presley";
        //Act
        String expected = music.getArtist();
        //Assert
        Assert.assertEquals(expected,actual);
    }
}