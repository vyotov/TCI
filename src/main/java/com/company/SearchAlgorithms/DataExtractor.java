package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataExtractor {

    private Document doc;
    private String url;


    public void setUrl(String url) throws IllegalAccessException {
        if (url.equals("")) {
            throw new IllegalArgumentException("Empty url " + url);
        }
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    /**
     * Method used to get the specific object name
     *
     * @return
     */
    private String getTitle() {
        return doc.getElementsByTag("h1").last().text();
    }


    public Music parseMusic(Element table) throws IOException {
        if (url.equals("") && table == null) {
            throw new RuntimeException("method set url must be called");
        }

        this.doc = Jsoup.connect(url).get();
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");

        Music music = new Music();
        music.setName(getTitle());
        music.setCategory("Music");

        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                music.setGenre(value);
            }
            if (key.equals("Format")) {
                music.setFormat(value);
            }
            if (key.equals("Year")) {
                music.setYear(value);
            }
            if (key.equals("Artist")) {
                music.setArtist(value);
            }
        }
        return music;
    }

    public Movie parseMovie(Element table) throws IOException {
        this.doc = Jsoup.connect(url).get();

        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        Movie movie = new Movie();
        //Set the name/title
        movie.setTitle(getTitle());
        movie.setCategory("Movie");

        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                movie.setGenre(value);
            }
            if (key.equals("Category")) {
                movie.setCategory(value);
            }
            if (key.equals("Format")) {
                movie.setFormat(value);
            }
            if (key.equals("Year")) {
                movie.setYear(value);
            }
            if (key.equals("Director")) {
                movie.setDirector(value);
            }
            if (key.equals("Writers")) {
                String[] writters = value.split(",");
                List<String> result = new ArrayList();
                for (String w : writters) {
                    result.add(w.trim());
                }
                movie.setWriters(result);
            }
            if (key.equals("Stars")) {
                String[] stars = value.split(",");
                List<String> result = new ArrayList();
                for (String w : stars) {
                    result.add(w.trim());
                }
                movie.setStars(result);
            }
        }
        System.out.println(movie);
        return movie;
    }

    public Book parseBook(Element table) throws IOException {
        if (url.equals("") || url == null) {
            throw new RuntimeException("method set url must be called");
        }
        this.doc = Jsoup.connect(url).get();

        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        Book book = new Book();
        book.setName(getTitle());
        book.setCategory("Book");

        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                book.setGenre(value);
            }
            if (key.equals("Category")) {
                book.setCategory(value);
            }
            if (key.equals("Format")) {
                book.setFormat(value);
            }
            if (key.equals("Year")) {
                book.setYear(value);
            }
            if (key.equals("Authors")) {
                book.setAuthors(Arrays.asList(value.split(",")));
            }
            if (key.equals("Publisher")) {
                book.setPublisher(value);
            }
            if (key.equals("ISBN")) {
                book.setIsbn(value);
            }
        }

        return book;

    }


}
