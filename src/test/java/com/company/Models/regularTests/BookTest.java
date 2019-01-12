package com.company.Models.regularTests;

import com.company.Models.Book;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookTest {

    @org.junit.Test
    public void IsGettingRightName() {
        Book book = new Book();
        String actual = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        book.setName("A Design Patterns: Elements of Reusable Object-Oriented Software");
        Assert.assertEquals(book.getName(), actual);
    }

    @org.junit.Test
    public void IsGettingRightGenre() {
        //TODO
    }

    @org.junit.Test
    public void IsGettingRightFormat() {
        //TODO
    }

    @org.junit.Test
    public void IsGettingRightYear() {
        //TODO
    }

    @org.junit.Test
    public void IsGettingRightAuthors() {
        //TODO
    }

    @org.junit.Test
    public void IsAuthorsListEmpty() {
        //TODO
    }

    @org.junit.Test
    public void IsNumberOfAuthorsCorrect() {
        //TODO
    }

    @org.junit.Test
    public void IsGettingRightPublisher() {
        //TODO
    }

    @org.junit.Test
    public void IsGettingRightISBN() {
        //TODO
    }
}
