package com.company.Models;

public class Music {

    private String name;
    private String category;
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
