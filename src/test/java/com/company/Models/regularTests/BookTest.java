package com.company.Models.regularTests;
import com.company.Models.Book;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookTest{

    @org.junit.Test
    public void IsGettingRgihtName()
    {
        //act
        Book book = new Book();
        book.setName("Clean Code: A Handbook of Agile Software Cr");
        //arange
        String actual = "Clean Code: A Handbook of Agile Software Craftsmanship";
        //assert
        Assert.assertEquals("Clean Code: A Handbook of Agile Software Craftsmanship","Clean Code: A Handbook of Agile Software Cr");
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
