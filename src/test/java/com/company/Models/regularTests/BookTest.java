package com.company.Models.regularTests;

import com.company.Models.Book;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookTest {

    @org.junit.Test
    public void IsGettingRightName() {
        //Arrange
        Book book = new Book();
        String actual = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        book.setName("A Design Patterns: Elements of Reusable Object-Oriented Software");
        // act
        String expected = book.getName();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightGenre() {
        //Arrange
        Book book = new Book();
        String actual = "Tech";
        book.setGenre("Tech");
        // act
        String expected = book.getGenre();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightFormat() {
        //Arrange
        Book book = new Book();
        String actual = "Paperback";
        book.setFormat("Paperback");
        // act
        String expected = book.getFormat();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightYear() {
        //Arrange
        Book book = new Book();
        String actual = "1994";
        book.setYear("1994");
        // act
        String expected = book.getYear();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightAuthors() {
        //Arrange
        Book book = new Book();
        List<String> authors = new ArrayList<>();
        authors.add("Erich Gamma");
        authors.add("Richard Helm");
        authors.add("Ralph Johnson");
        authors.add("John Vlissides");
        book.setAuthors(authors);
        // act
        List<String> expected = book.getAuthors();
        // assert
        Assert.assertEquals(expected, authors);
    }

    @org.junit.Test
    public void IsAuthorsListEmpty() {
        //Arrange
        Book book = new Book();
        //Empty list:
        List<String> authors = new ArrayList<>();
        book.setAuthors(authors);
        // act
        List<String> expected = book.getAuthors();
        // assert
        Assert.assertEquals(expected, authors);
    }


    @org.junit.Test
    public void IsGettingRightPublisher() {
        //Arrange
        Book book = new Book();
        String actual = "Prentice Hall";
        book.setPublisher("Prentice Hall");
        // act
        String expected = book.getPublisher();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightISBN() {
        //Arrange
        Book book = new Book();
        String actual = "978-0201633610";
        book.setIsbn("978-0201633610");
        // act
        String expected = book.getIsbn();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightCategory() {
        //Arrange
        Book book = new Book();
        String actual = "Books";
        book.setCategory("Books");
        // act
        String expected = book.getCategory();
        // assert
        Assert.assertEquals(expected, actual);
    }
}
