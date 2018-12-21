package com.company.Models;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String name;
    private String genre;
    private String format;
    private int year;
    private List<String> authors = new ArrayList<>();
    private String publisher;
    private String isbn;


    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getFormat() {
        return format;
    }

    public int getYear() {
        return year;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }
}
