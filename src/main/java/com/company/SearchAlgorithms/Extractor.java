package com.company.SearchAlgorithms;

import com.company.Models.Category;
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
import java.util.*;

public class Extractor {

    private HashSet<String> links;
    private Long startTime;
    private Long endTime;
    private Gson gson = new Gson();
    private DataExtractor dataExtractor = new DataExtractor();
    private String searchById;
    private Element table = null;
    private Object object = null;
    
    public Extractor() {
        links = new HashSet<>();
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

    public Object searchById(String searchById) throws IOException {
        this.searchById = searchById;
        startTime = System.currentTimeMillis();

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

    public JSONObject getJsonForSearch() {
        JSONObject result = new JSONObject();
        endTime = System.currentTimeMillis();
        result.put("id", searchById);
        result.put("time", getTimeDuration());
        result.put("result", gson.toJsonTree(object));

        return result;
    }

    public JSONObject getAllObjects() throws IOException {
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
        System.out.println(result.toString());
        return result;
    }

    public JSONObject searchByCategory(String text) throws IOException {
        startTime = System.currentTimeMillis();
        JSONObject jsObject = getAllObjects();
        JsonObject jsonObject = findJsonForSearchText(jsObject, text);
        JSONObject result = new JSONObject();
        endTime = System.currentTimeMillis();
        result.put("time", getTimeDuration());
        result.put("filter", text);
        result.put("result", jsonObject);
        return result;
    }


    public JsonObject findJsonForSearchText(JSONObject jsObject, String text) {
        Iterator it = jsObject.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next(); //current entry in a loop
            if (entry.getKey().toString().equals("movies")) {
                JsonArray jsonArray = (JsonArray) entry.getValue();
                for (JsonElement jsonElement : jsonArray) {
                    if (((JsonObject) jsonElement).get("title").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("category").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("genre").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("format").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("year").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("director").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    } else {
                        for (JsonElement element : ((JsonObject) jsonElement).get("writers").getAsJsonArray()) {
                            if (element.getAsString().trim().equals(text)) {
                                return (JsonObject) jsonElement;
                            }
                        }

                        for (JsonElement element : ((JsonObject) jsonElement).get("stars").getAsJsonArray()) {
                            if (element.getAsString().trim().equals(text)) {
                                return (JsonObject) jsonElement;
                            }
                        }
                    }

                }
            }
            if (entry.getKey().toString().equals("books")) {
                JsonArray jsonArray = (JsonArray) entry.getValue();

                for (JsonElement jsonElement : jsonArray) {
                    if (((JsonObject) jsonElement).get("name").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("category").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("genre").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("format").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("year").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }

                    if (((JsonObject) jsonElement).get("publisher").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("isbn").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    } else {
                        for (JsonElement element : ((JsonObject) jsonElement).get("authors").getAsJsonArray()) {
                            if (element.getAsString().trim().equals(text)) {
                                return (JsonObject) jsonElement;
                            }
                        }
                    }
                }
            }
            if (entry.getKey().toString().equals("music")) {
                JsonArray jsonArray = (JsonArray) entry.getValue();

                for (JsonElement jsonElement : jsonArray) {
                    if (((JsonObject) jsonElement).get("name").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("category").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("genre").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("format").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("year").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                    if (((JsonObject) jsonElement).get("artist").getAsString().equals(text)) {
                        return (JsonObject) jsonElement;
                    }
                }
            }
            /*
             * do something for each entry
             */
        }
        return new JsonObject();
    }


    public Category findCategory(String url) {
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


    public boolean checkIfStringContainsOnlyNumbers(String string) {
        return string.matches("\\d+");
    }

    public int getPageCount() {
        return links.size();
    }

}
