package com.company.Models;

import java.util.List;

public class Movie {

    private String title;
    private String category;
    private String genre;
    private String format;
    private String year;
    private String director;
    private List<String> writers;
    private List<String> stars;


    public String getTitle() {
        return title;
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

    public String getDirector() {
        return director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public Movie(String title,String category, String genre, String format, String year, String director, List<String> writers, List<String> stars) {
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
    }
}


