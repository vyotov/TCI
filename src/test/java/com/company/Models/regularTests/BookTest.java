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
        String expected = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        book.setName("A Design Patterns: Elements of Reusable Object-Oriented Software");
        // act
        // assert
        Assert.assertEquals(expected, book.getName());
    }


    @org.junit.Test
    public void IsGettingRightGenre() {
        //Arrange
        Book book = new Book();
        String expected = "Tech";
        book.setGenre("Tech");
        // act
        String actual = book.getGenre();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightFormat() {
        //Arrange
        Book book = new Book();
        String expected = "Paperback";
        book.setFormat("Paperback");
        // act
        String actual = book.getFormat();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightYear() {
        //Arrange
        Book book = new Book();
        String expected = "1994";
        book.setYear("1994");
        // act
        String actual = book.getYear();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightAuthors() {
        //Arrange
        Book book = new Book();
        List<String> expectedAuthors = new ArrayList<>();
        expectedAuthors.add("Erich Gamma");
        expectedAuthors.add("Richard Helm");
        expectedAuthors.add("Ralph Johnson");
        expectedAuthors.add("John Vlissides");
        book.setAuthors(expectedAuthors);
        // act
        List<String> actual = book.getAuthors();
        // assert
        Assert.assertEquals(expectedAuthors, actual);
    }

    @org.junit.Test
    public void IsAuthorsListEmpty() {
        //Arrange
        Book book = new Book();
        //Empty list:
        List<String> expectedAuthors = new ArrayList<>();
        book.setAuthors(expectedAuthors);
        // act
        List<String> actual = book.getAuthors();
        // assert
        Assert.assertEquals(expectedAuthors, actual);
    }


    @org.junit.Test
    public void IsGettingRightPublisher() {
        //Arrange
        Book book = new Book();
        String expected = "Prentice Hall";
        book.setPublisher("Prentice Hall");
        // act
        String actual = book.getPublisher();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightISBN() {
        //Arrange
        Book book = new Book();
        String expected = "978-0201633610";
        book.setIsbn("978-0201633610");
        // act
        String actual = book.getIsbn();
        // assert
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void IsGettingRightCategory() {
        //Arrange
        Book book = new Book();
        String expected = "Books";
        book.setCategory("Books");
        // act
        String actual = book.getCategory();
        // assert
        Assert.assertEquals(expected, actual);
    }


}
