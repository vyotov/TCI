package com.company.Models;

import java.io.Serializable;
import java.util.Objects;

public class Music  implements Serializable{

    private String name;
    private String category;
    private String genre;
    private String format;
    private String year;
    private String artist;

    public Music(String name, String category, String genre, String format, String year, String artist) {
        this.name = name;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.artist = artist;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Music)) return false;
        Music music = (Music) o;
        return Objects.equals(name, music.name) &&
                Objects.equals(category, music.category) &&
                Objects.equals(genre, music.genre) &&
                Objects.equals(format, music.format) &&
                Objects.equals(year, music.year) &&
                Objects.equals(artist, music.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, genre, format, year, artist);
    }

    public String getFormat() {
        return format;
    }

    public String getYear() {
        return year;
    }

    public String getArtist() {
        return artist;
    }
    public Music(){}
}
