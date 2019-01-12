package com.company.Models.mokitoTests;

import com.company.Models.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MokitoBookTest {
    private static final String BOOK_NAME = "A Design Patterns: Elements of Reusable Object-Oriented Software";
    private static final String CATEGORY = "Books";
    private static final String GENRE = "Tech";
    private static final String FORMAT = "Paperback";
    private static final String YEAR = "1994";
    private static final String PUBLISHER = "Prentice Hall";
    private static final String ISBN = "978-0201633610";
    private static final List<String> AUTHORS = new ArrayList<>();
    private void FillInAurthors(){
        AUTHORS.add("Erich Gamma");
        AUTHORS.add("Richard Helm");
        AUTHORS.add("Ralph Johnson");
        AUTHORS.add("John Vlissides");
    }
    //public Book book = mock(Book.class);

    @Test
    public void verifyFakeBookWithRealBook()
    {
        //arrange
        Book spyBook = spy(new Book()); // this is the spying
        spyBook.getName();
        //verify
        verify(spyBook).getName();
        //assert
        Assert.assertEquals(null,spyBook.getName());
    }
    @Test
    public void verifyMockedBookByName() throws IllegalArgumentException{
        // arrange
        Book book = mock(Book.class);
        //act
        book.getName();
        //assert
        verify(book).getName();
        Assert.assertEquals(null,book.getName());

    }
    //Stub Test
    @Test
    public void shouldStubFakeObj()
    {
        //arrange
        FillInAurthors();
        Book spyBook = spy(new Book(BOOK_NAME,CATEGORY,GENRE,FORMAT,YEAR,AUTHORS,PUBLISHER,ISBN)); // this is the spying
        spyBook.getName();
        when(spyBook.getName()).thenReturn(BOOK_NAME); // this is the Stubbing definition above
        Assert.assertEquals(BOOK_NAME, spyBook.getName());
    }

    @Test
    public void shouldGetBookAuthors()
    {
        //TODO
    }
    @Test
    public void shouldGetBookByNameAndAuthor()
    {
        //TODO
    }
    @Test
    public void isGettingBookByYear()
    {
        //TODO
    }
    @Test
    public void isGettingBookByPublisher()
    {
        //TODO
    }
    @Test
    public void isGettingBookByGenre()
    {
        //TODO
    }
    @Test
    public void isGettingBookByISBN()
    {
        //TODO
    }
}
