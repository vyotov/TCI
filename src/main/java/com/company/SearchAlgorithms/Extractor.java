package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Extractor {

    private HashSet<String> links;
    private Long startTime;
    private Long endTime;
    private Gson gson = new Gson();

    private String URL;
    private Utils utils;
    private DataExtractor dataExtractor;

    public Extractor(String URL, DataExtractor dataExtractor,Utils utils) throws MalformedURLException {
        this.dataExtractor = dataExtractor;
        this.URL = URL;
        this.utils = utils;
        links = new HashSet<>();
        getPageLinks(URL);
    }

    public String getURL() {
        return URL;
    }

    public boolean canConnectToUrl(String URL) {
        try {
            URL myURL = new URL(URL);
            // also you can put a port
            //  URL myURL = new URL("http://localhost:8080");
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();
        } catch (IOException e) {
            // openConnection() failed
            // ...
            return false;
        }
        return true;
    }


    public void getPageLinks(String URL) throws MalformedURLException {
        if (!isValidURL(URL) && !canConnectToUrl(URL)) {
            throw new MalformedURLException();
        }
            if (!links.contains(URL) && !URL.contains("twitter") && !URL.contains("facebook") && URL.contains("localhost:8888")) {
            try {
                //Set the start time
                // startTime = System.currentTimeMillis();
                Document document = Jsoup.connect(URL).get();
                Elements allHyperLinks = document.select("a[href]");
                for (Element page : allHyperLinks) {
                    //Add
                    links.add(URL);
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public boolean isValidURL(String urlStr) {
        try {
            new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public Object searchById(String searchById,DataExtractor dataExtractor) throws IOException, ClassNotFoundException {
        if (searchById.equals("")) {
            throw new IllegalArgumentException("Empty search id " + searchById);
        }
        Object object = null;
        //Loop over the list:

        for (String url : links) {
            //Check which url matches the search word:
            if (url.contains(searchById)) {
                Category category = utils.findCategory(url);
                switch (category) {
                    case BOOKS:
                        dataExtractor.setUrl(url);
                        object = dataExtractor.parseBook(utils.getElement());
                        break;
                    case MUSIC:
                        dataExtractor.setUrl(url);
                        object = dataExtractor.parseMusic(utils.getElement());
                        break;
                    case MOVIE:
                        dataExtractor.setUrl(url);
                        object = dataExtractor.parseMovie(utils.getElement());
                        break;
                }
                break;
            }
        }
        return object;
    }

    public JSONObject getJsonResultForSearchById(String searchById) throws IOException, ClassNotFoundException {
        JSONObject result = new JSONObject();
        startTime = System.currentTimeMillis();
        result.put("id", searchById);
        result.put("result", gson.toJsonTree(searchById(searchById,dataExtractor)));
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        return result;
    }

    public boolean checkIfStringContainsOnlyNumbers(String text) {
        if (text == null) {
            throw new RuntimeException("Parameter text should not be called with null value");
        }
        return text.matches("\\d+");
    }

    public JSONObject getJsonForSearchByKeyWord(String text) throws IOException, ClassNotFoundException {

        if (text.equals("")) {
            throw new NullPointerException("Empty search keyword" + text);
        }
        startTime = System.currentTimeMillis();
        JSONObject result = new JSONObject();
        result.put("filter", text);

        result.put("result", gson.toJsonTree(findObjectModelForSearchText(getAllObjects(), text)));
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        return result;
    }

    public JSONObject getAllObjects() throws IOException, ClassNotFoundException {
        startTime = System.currentTimeMillis();
        List<Object> moviesList = new ArrayList<>();
        List<Object> bookList = new ArrayList<>();
        List<Object> musicList = new ArrayList<>();
        for (String url : links) {
            String number = url.substring(url.lastIndexOf("=") + 1);
            if (!number.equals("") && checkIfStringContainsOnlyNumbers(number)) {
                Category category = utils.findCategory(url);
                switch (category) {
                    case BOOKS:
                        dataExtractor.setUrl(url);
                        bookList.add(dataExtractor.parseBook(utils.getElement()));
                        break;
                    case MUSIC:
                        dataExtractor.setUrl(url);
                        musicList.add(dataExtractor.parseMusic(utils.getElement()));
                        break;
                    case MOVIE:
                        dataExtractor.setUrl(url);
                        moviesList.add(dataExtractor.parseMovie(utils.getElement()));
                        break;
                }
            }
        }

        JSONObject result = new JSONObject();
        if (musicList.isEmpty() || bookList.isEmpty() || moviesList.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Empty objects");
        }
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        result.put("movies", gson.toJsonTree(moviesList));
        result.put("books", gson.toJsonTree(bookList));
        result.put("music", gson.toJsonTree(musicList));
        return result;
    }


    public Object findObjectModelForSearchText(JSONObject allJsonObject, String text) {
        Iterator it = allJsonObject.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next(); //current entry in a loop
            if (entry.getKey().toString().equals("movies")) {
                JsonArray jsonArray = (JsonArray) entry.getValue();
                for (JsonElement jsonElement : jsonArray) {
                    if (((JsonObject) jsonElement).get("title").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Movie.class);
                    }
                    if (((JsonObject) jsonElement).get("category").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Movie.class);
                    }
                    if (((JsonObject) jsonElement).get("genre").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Movie.class);
                    }
                    if (((JsonObject) jsonElement).get("format").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Movie.class);
                    }
                    if (((JsonObject) jsonElement).get("year").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Movie.class);
                    }
                    if (((JsonObject) jsonElement).get("director").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Movie.class);
                    } else {
                        for (JsonElement element : ((JsonObject) jsonElement).get("writers").getAsJsonArray()) {
                            if (element.getAsString().trim().equals(text)) {
                                return new Gson().fromJson(jsonElement, Movie.class);
                            }
                        }

                        for (JsonElement element : ((JsonObject) jsonElement).get("stars").getAsJsonArray()) {
                            if (element.getAsString().trim().equals(text)) {
                                return new Gson().fromJson(jsonElement, Movie.class);
                            }
                        }
                    }

                }
            }
            if (entry.getKey().toString().equals("books")) {
                JsonArray jsonArray = (JsonArray) entry.getValue();

                for (JsonElement jsonElement : jsonArray) {
                    if (((JsonObject) jsonElement).get("name").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    }
                    if (((JsonObject) jsonElement).get("category").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    }
                    if (((JsonObject) jsonElement).get("genre").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    }
                    if (((JsonObject) jsonElement).get("format").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    }
                    if (((JsonObject) jsonElement).get("year").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    }

                    if (((JsonObject) jsonElement).get("publisher").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    }
                    if (((JsonObject) jsonElement).get("isbn").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Book.class);
                    } else {
                        for (JsonElement element : ((JsonObject) jsonElement).get("authors").getAsJsonArray()) {
                            if (element.getAsString().trim().equals(text)) {
                                return new Gson().fromJson(jsonElement, Book.class);
                            }
                        }
                    }
                }
            }
            if (entry.getKey().toString().equals("music")) {
                JsonArray jsonArray = (JsonArray) entry.getValue();

                for (JsonElement jsonElement : jsonArray) {
                    if (((JsonObject) jsonElement).get("name").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Music.class);
                    }
                    if (((JsonObject) jsonElement).get("category").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Music.class);
                    }
                    if (((JsonObject) jsonElement).get("genre").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Music.class);
                    }
                    if (((JsonObject) jsonElement).get("format").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Music.class);
                    }
                    if (((JsonObject) jsonElement).get("year").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Music.class);
                    }
                    if (((JsonObject) jsonElement).get("artist").getAsString().equals(text)) {
                        return new Gson().fromJson(jsonElement, Music.class);
                    }
                }
            }

        }
        return new JsonObject();
    }


    public Long getTimeDuration() {
        return endTime - startTime;
    }


    public int getPageCount() {
        return links.size();
    }

}
