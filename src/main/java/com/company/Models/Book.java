package com.company.Models;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String name;
    private String category;
    private String genre;
    private String format;
    private String year;
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

    public String getYear() {
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

    public Book(String name, String category,String genre, String format, String year, List<String> authors, String publisher, String isbn) {
        this.name = name;
        this.category  =category;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
