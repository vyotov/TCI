package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Category;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.utils.Constants;
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
import java.util.*;

public class Extractor {

    private HashSet<String> links;
    private Long startTime;
    private Long endTime;
    private Gson gson = new Gson();
    private DataExtractor dataExtractor = new DataExtractor();
    private Element table = null;

    public Extractor() {
        links = new HashSet<>();
    }

    //Tested
    public void getPageLinks(String URL) throws MalformedURLException {
        if (!isValidURL(URL)) {
            throw new MalformedURLException();
        }
        if (!links.contains(URL) && !URL.contains("twitter") && !URL.contains("facebook")) {
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

    //Tested
    public Object searchById(String searchById) throws IOException, ClassNotFoundException {
        if (searchById.equals("")) {
            throw new IllegalArgumentException();
        }
        Object object = null;
        //Loop over the list:
        for (String url : links) {
            //Check which url matches the search word:
            if (url.contains(searchById)) {
                Category category = findCategory(url);
                switch (category) {
                    case BOOKS:
                        dataExtractor.setUrl(url);
                        object = dataExtractor.parseBook(table);
                        break;
                    case MUSIC:
                        dataExtractor.setUrl(url);
                        object = dataExtractor.parseMusic(table);
                        break;
                    case MOVIE:
                        dataExtractor.setUrl(url);
                        object = dataExtractor.parseMovie(table);
                        break;
                }
                break;
            }
        }
        return object;
    }

    //Tested
    public JSONObject getJsonResultForSearchById(String searchById) throws IOException, ClassNotFoundException {
        JSONObject result = new JSONObject();
        startTime = System.currentTimeMillis();
        result.put("id", searchById);
        result.put("result", gson.toJsonTree(searchById(searchById)));
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        return result;
    }

    //Tested
    public boolean checkIfStringContainsOnlyNumbers(String string) {
        return string.matches("\\d+");
    }

    //Tested
    public JSONObject getJsonForSearchByKeyWord(String text) throws IOException, ClassNotFoundException {
        startTime = System.currentTimeMillis();
        JSONObject result = new JSONObject();
        result.put("filter", text);

        result.put("result", gson.toJsonTree(findObjectModelForSearchText(getAllObjects(), text)));
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        return result;
    }

    //Tested
    public JSONObject getAllObjects() throws IOException, ClassNotFoundException {
        startTime = System.currentTimeMillis();
        List<Object> moviesList = new ArrayList<>();
        List<Object> bookList = new ArrayList<>();
        List<Object> musicList = new ArrayList<>();
        for (String url : links) {
            String number = url.substring(url.lastIndexOf("=") + 1);
            if (!number.equals("") && checkIfStringContainsOnlyNumbers(number)) {
                Category category = findCategory(url);
                switch (category) {
                    case BOOKS:
                        dataExtractor.setUrl(url);
                        bookList.add(dataExtractor.parseBook(table));
                        break;
                    case MUSIC:
                        dataExtractor.setUrl(url);
                        musicList.add(dataExtractor.parseMusic(table));
                        break;
                    case MOVIE:
                        dataExtractor.setUrl(url);
                        moviesList.add(dataExtractor.parseMovie(table));
                        break;
                }
            }
        }

        JSONObject result = new JSONObject();
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        result.put("movies", gson.toJsonTree(moviesList));
        result.put("books", gson.toJsonTree(bookList));
        result.put("music", gson.toJsonTree(musicList));
        // System.out.println(result.toString());
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
            /*
             * do something for each entry
             */
        }
        return new JsonObject();
    }


    public Category findCategory(String url) throws ClassNotFoundException {
        if (url.equals("")) {
            throw new ClassNotFoundException();
        }
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Find the media details tag on the right of the page
        Elements results = doc.getElementsByClass(Constants.media_detail);
        //Table
        table = results.select("table").first();
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            //Check which category:
            if (key.equals(Constants.category)) {
                switch (value) {
                    case Constants.music:
                        return Category.MUSIC;
                    case Constants.movies:
                        return Category.MOVIE;
                    case Constants.books:
                        return Category.BOOKS;
                }
            }
        }
        return null;
    }

    public Long getTimeDuration() {
        return endTime - startTime;
    }


    public int getPageCount() {
        return links.size();
    }

}
