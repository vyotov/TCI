package com.company.Models;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    private String title;
    private String category;

    public void setTitle(String title) {
        this.title = title;
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

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public void setStars(List<String> stars) {
        this.stars = stars;
    }

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

    public Movie(){}
}


