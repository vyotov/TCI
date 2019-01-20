package com.company.Models.regularTests;

import com.company.Models.Book;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class BookTest {

    @org.junit.Test
    public void IsGettingRightName() {
        //Arrange
        Book book = new Book();
        String expected = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        book.setName("A Design Patterns: Elements of Reusable Object-Oriented Software");
        // act
        String actual = book.getName();
        // assert
        Assert.assertEquals("The book is not set correctly or the expect value is wrong",expected, actual);
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
        Assert.assertEquals("The genre is not set correctly or the expect value is wrong",expected, actual);
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
        Assert.assertEquals("The format is not set correctly or the expect value is wrong",expected, actual);
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
        Assert.assertEquals("The year is not set correctly or the expect value is wrong",expected, actual);
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
        Assert.assertEquals("The authors are not set correctly or the expect value is wrong",expectedAuthors, actual);
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
        Assert.assertEquals("The list is empty",expectedAuthors, actual);
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
        Assert.assertEquals("The publisher is not set correctly or the expect value is wrong",expected, actual);
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
        Assert.assertEquals("The ISBN is not set correctly or the expect value is wrong",expected, actual);
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
        Assert.assertEquals("The category is not set correctly or the expect value is wrong",expected, actual);
    }


}
