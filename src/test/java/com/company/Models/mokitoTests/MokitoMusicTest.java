package com.company.Models.mokitoTests;

import com.company.Models.Music;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MokitoMusicTest {

    private static final String MUSIC_NAME = "Elvis Forever";
    private static final String CATEGORY = "Music";
    private static final String GENRE = "Rock";
    private static final String FORMAT = "Vinyl";
    private static final String YEAR = "2015";
    private static final String ARTIST = "Elvis Presley";

    @Test
    public void verifySpyMusicByName()
    {
        //arrange
        //spy
        Music spyMusic = spy(new Music());
        spyMusic.getName();
        //verify
        verify(spyMusic).getName();
        //assert
        Assert.assertEquals(null,spyMusic.getName());
    }
    //Mock Test
    @Test
    public void verifyMockedMusicByName() throws IllegalArgumentException{
        // arrange
        Music music = mock(Music.class);
        //act
        music.getName();
        //assert
        verify(music).getName();
        Assert.assertEquals(null,music.getName());

    }
    //Stub Test
    @Test
    public void shouldStubFakeObj()
    {
        //arrange
        //spy
        Music spyMusic = spy(new Music(MUSIC_NAME,CATEGORY,GENRE,FORMAT,YEAR,ARTIST));
        spyMusic.getName();
        //stub
        when(spyMusic.getName()).thenReturn(MUSIC_NAME);
        Assert.assertEquals(MUSIC_NAME, spyMusic.getName());
    }
}
