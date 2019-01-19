package com.company.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable{

    private String name;
    private String category;
    private String genre;
    private String format;
    private String year;
    private List<String> authors = new ArrayList<>();
    private String publisher;
    private String isbn;

    public Book(String name, String category, String genre, String format, String year, List<String> authors, String publisher, String isbn) {
        this.name = name;
        this.category = category;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
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

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


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

    public Book(){}

    public String getCategory() {
        return  category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(category, book.category) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(format, book.format) &&
                Objects.equals(year, book.year) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, genre, format, year, authors, publisher, isbn);
    }
}
