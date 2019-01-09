package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Extractor {

    private HashSet<String> links;
    private List<Book> bookList;
    private List<Music> musicList;
    private List<Movie> movieList;
    private Movie mMovie;
    private Book mBook;
    private Music mMusic;
    private Long startTime;
    private Long endTime;
    private Gson gson = new Gson();

    public Extractor() {
        links = new HashSet<>();
        bookList = new ArrayList<>();
        musicList = new ArrayList<>();
        movieList = new ArrayList<>();
    }


    //Find all URLs that start with "http://www.mkyong.com/page/" and add them to the HashSet
    public void getPageLinks(String URL) {
        if (!links.contains(URL) && !URL.contains("twitter") && !URL.contains("facebook")) {
            try {
                //Set the start time
                // startTime = System.currentTimeMillis();
                Document document = Jsoup.connect(URL).get();
                Elements allHyperLinks = document.select("a[href]");
                for (Element page : allHyperLinks) {
                    if (links.add(URL)) {
                        //Remove the comment from the line below if you want to see it running on your editor
                        System.out.println(URL);
                    }
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void searchById(String searchById) throws IOException {
        startTime = System.currentTimeMillis();
        //Loop over the list:
        for (String url : links) {
            //Check which url matches the search word:
            if (url.contains(searchById)) {
                new DataExtractor(url).setDataListener(new DataExtractor.DataListener() {
                    @Override
                    public void onMusic(Music music) {
                        mMusic = music;
                        //System.out.println(gson.toJson(music));
                    }

                    @Override
                    public void onMovie(Movie movie) {
                        //System.out.println(gson.toJson(movie));
                        mMovie = movie;
                    }

                    @Override
                    public void onBook(Book book) {
                        mBook = book;
                        //System.out.println(gson.toJson(book));
                    }
                }).exact();
                break;
            }
        }
        createJsonForSingle(searchById);

    }

    private String getJsonStringForObject(Object object) {
        return new Gson().toJson(object);
    }


    public void getAllObjects() throws IOException {
        startTime = System.currentTimeMillis();
        for (String url : links) {
            String number = url.substring(url.lastIndexOf("=") + 1);
            if (!number.equals("") && isInt(number)) {
                new DataExtractor(url).setDataListener(new DataExtractor.DataListener() {

                    @Override
                    public void onMusic(Music music) {
                        musicList.add(music);
                    }

                    @Override
                    public void onMovie(Movie movie) {
                        movieList.add(movie);
                    }

                    @Override
                    public void onBook(Book book) {
                        bookList.add(book);
                    }
                }).exact();
            }
        }
        createJsonForAll();
    }

    @SuppressWarnings("unchecked")
    private JSONObject createJsonForAll() {
        JSONObject result = new JSONObject();
        endTime = System.currentTimeMillis();
        result.put("time", (endTime - startTime));
        result.put("movies", getJsonStringForObject(movieList));
        result.put("books", getJsonStringForObject(bookList));
        result.put("music", getJsonStringForObject(musicList));
        //System.out.println(result.toString());
        return result;
    }

    @SuppressWarnings("unchecked")
    private JSONObject createJsonForSingle(String id) {
        JSONObject result = new JSONObject();
        endTime = System.currentTimeMillis();
        result.put("id", id);
        result.put("time", (endTime - startTime));
        result.put("result", mMusic != null ? gson.toJson(mMusic) : (mBook != null ? gson.toJson(mBook) : (mMovie != null ? gson.toJson(mMovie) : "")));

        System.out.println(result.toString());
        return result;
    }

    public Long getTimeDuration() {
        return endTime - startTime;
    }


    private boolean isInt(String string) {
        return string.matches("\\d+");
    }


    public int getPageCount() {
        return links.size();
    }

}
