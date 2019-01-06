package com.company.Models;

public class Music {

    private String name;
    private String category;
    private String genre;
    private String format;
    private String year;
    private String artist;

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

    public Music(String name, String category, String genre, String format, String year, String artist) {
        this.name = name;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.artist = artist;
    }
}
