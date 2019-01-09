package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;


public class DataExtractor {
    private DataListener dataListener;
    private Document doc;


    /*** Method used to set the data listener to notify the Extractor class once object is found.
     * In case the work is executed async.*/
    public DataExtractor setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
        return this;
    }

    DataExtractor(String url) throws IOException {
        this.doc = Jsoup.connect(url).get();
    }


    public void exact() {
        //Find the media details tag on the right of the page
        Elements results = doc.getElementsByClass(Constants.media_detail);
        //Table
        Element table = results.select("table").first();
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals(Constants.category)) {
                switch (value) {
                    case Constants.music:
                        //Extra specific object
                        parseMusic(table);
                        break;
                    case Constants.movies:
                        //Extra specific object
                        parseMovie(table);
                        break;
                    case Constants.books:
                        //Extra specific object
                        parseBook(table);
                        break;
                }
            }
        }
    }


    /**
     * Method used to get the specific object name
     *
     * @return
     */
    private String getTitle() {
        return doc.getElementsByTag("h1").last().text();
    }


    private void parseMusic(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");

        Music music = new Music();
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

        if (dataListener != null) {
            dataListener.onMusic(music);
        }
    }

    private void parseMovie(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        Movie movie = new Movie();
        //Set the name

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
                movie.setWriters(Arrays.asList(value.split(",")));
            }
            if (key.equals("Stars")) {
                movie.setStars(Arrays.asList(value.split(",")));
            }
        }
        if (dataListener != null) {
            dataListener.onMovie(movie);
        }
    }

    private void parseBook(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        Book book = new Book();

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
        if (dataListener != null) {
            dataListener.onBook(book);
        }
    }

    public interface DataListener {

        void onMusic(Music music);

        void onMovie(Movie movie);

        void onBook(Book book);
    }
}
