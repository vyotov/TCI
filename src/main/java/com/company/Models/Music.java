package com.company.Models;

import java.io.Serializable;

public class Music  implements Serializable{

    private String name;
    private String category;

    public Music(String name, String category, String genre, String format, String year, String artist) {
        this.name = name;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.artist = artist;
    }

    private String genre;
    private String format;
    private String year;
    private String artist;

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
