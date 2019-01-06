package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

public class DataExtractor {
    private JSONArray jsonArray;
    private JSONObject jsonResult;
    private String url;
    private DataListener dataListener;
    private Document doc;

    /*** Method used to set the data listener to notify the Extractor class once object is found.
     * In case the work is executed async.*/
    public DataExtractor setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
        return this;
    }

    public DataExtractor(String url) throws IOException {
        this.jsonArray = new JSONArray();
        this.jsonResult = new JSONObject();
        this.url = url;
        this.doc = Jsoup.connect(url).get();

    }

    public void exact() throws IOException {
        //Find the media details tag on the right of the page
        Elements results = doc.getElementsByClass("media-details");
        //Table
        Element table = results.select("table").first();
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Category")) {
                switch (value) {
                    case "Music":
                        //Extra specific object
                        getMusicObject(table);
                        System.out.println("Music");
                        break;
                    case "Movies":
                        //Extra specific object
                        getMoviesObject(table);
                        System.out.println("Movies");
                        break;
                    case "Books":
                        //Extra specific object
                        getBooksObject(table);
                        System.out.println("Books");
                        break;
                }

            }
        }
    }

    private String getId() {
        return "";
    }

    private Music getMusicObject(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        String genre = "";
        String format = "";
        String year = "";
        String artist = "";
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                genre = value;
            }
            if (key.equals("Format")) {
                format = value;
            }
            if (key.equals("Year")) {
                year = value;
            }
            if (key.equals("Artist")) {
                artist = value;
            }
        }
        return new Music("", "Music", genre, format, year, artist);
    }

    private Movie getMoviesObject(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        String genre = "";
        String category = "";
        String format = "";
        String year = "";
        String director = "";
        String writers = "";
        String stars = "";
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                genre = value;
            }
            if(key.equals("Category")){
                category = value;
            }
            if (key.equals("Format")) {
                format = value;
            }
            if (key.equals("Year")) {
                year = value;
            }
            if (key.equals("Director")) {
                director = value;
            }
            if (key.equals("Writers")) {
                writers = value;
            }
            if (key.equals("Stars")) {
                stars = value;
            }
        }
        return new Movie("",category,genre,format,year,director, Arrays.asList(writers.split(",")),Arrays.asList(stars.split(",")));
    }

    private Book getBooksObject(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        String genre = "";
        String category = "";
        String format = "";
        String year = "";
        String authors = "";
        String publisher = "";
        String ISBN = "";
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                genre = value;
            }
            if(key.equals("Category")){
                category = value;
            }
            if (key.equals("Format")) {
                format = value;
            }
            if (key.equals("Year")) {
                year = value;
            }
            if (key.equals("Authors")) {
                authors = value;
            }
            if (key.equals("Publisher")) {
                publisher = value;
            }
            if (key.equals("ISBN")) {
                ISBN = value;
            }
        }
        return new Book("",category,genre,format,year,Arrays.asList(authors.split(",")),publisher,ISBN);
    }

    public interface DataListener {
        void onDataLoaded();
    }
}
