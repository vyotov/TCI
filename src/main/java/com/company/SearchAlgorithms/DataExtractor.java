package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.company.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    private JSONObject jsonObject;
    private String url;
    private DataListener dataListener;
    private Document doc;
    private Gson gson = new Gson();
    private String objectName;

    /*** Method used to set the data listener to notify the Extractor class once object is found.
     * In case the work is executed async.*/
    public DataExtractor setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
        return this;
    }

    DataExtractor(String url) throws IOException {
        this.jsonArray = new JSONArray();
        this.jsonResult = new JSONObject();
        this.jsonObject = new JSONObject();
        this.url = url;
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
                        getMusicObject(table);
                        System.out.println("Music");
                        break;
                    case Constants.movies:
                        //Extra specific object
                        getMoviesObject(table);
                        System.out.println("Movies");
                        break;
                    case Constants.books:
                        //Extra specific object
                        getBooksObject(table);
                        System.out.println("Books");
                        break;
                }
            }
        }
    }

    /**
     * Method to find the id from a given url
     *
     * @return the id of the object that is searched
     */
    private String getId() {
        String number = url.substring(url.lastIndexOf("=") + 1);
        System.out.println(number);
        return number;
    }

    /**
     * Method used to get the specific object name
     *
     * @return
     */
    private String getTitle() {
        return doc.getElementsByTag("h1").last().text();
    }

    //TODO
    private void getMusicObject(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        String genre = "";
        String format = "";
        String year = "";
        String artist = "";
        jsonResult.put("id", getId());
        jsonResult.put("time", "timeee");
        //TODO fix name
        jsonObject.put("name", getTitle());
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                genre = value;
                jsonObject.put("genre", genre);
            }
            if (key.equals("Format")) {
                format = value;
                jsonObject.put("format",format);
            }
            if (key.equals("Year")) {
                year = value;
                jsonObject.put("year",format);
            }
            if (key.equals("Artist")) {
                artist = value;
                jsonObject.put("artist",artist);
            }
        }

        jsonResult.put("result", jsonObject);

        if (dataListener != null) {
            dataListener.onObjectFound(jsonResult);
        }
    }

    //DONE
    private void getMoviesObject(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        String genre = "";
        String category = "";
        String format = "";
        String year = "";
        String director = "";
        String writers = "";
        String stars = "";
        //Set the name


        jsonResult.put("id", getId());
        jsonResult.put("time", "timeee");
        //TODO fix name
        jsonObject.put("name", getTitle());
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                genre = value;
                jsonObject.put("genre", genre);
            }
            if (key.equals("Category")) {
                category = value;
                jsonObject.put("category", category);
            }
            if (key.equals("Format")) {
                format = value;
                jsonObject.put("format", format);
            }
            if (key.equals("Year")) {
                year = value;
                jsonObject.put("year", year);
            }
            if (key.equals("Director")) {
                director = value;
                jsonObject.put("director", director);
            }
            if (key.equals("Writers")) {
                writers = value;
                jsonObject.put("director", director);
            }
            if (key.equals("Stars")) {
                stars = value;
            }
        }
        jsonResult.put("result", jsonObject);

        if (dataListener != null) {
            dataListener.onObjectFound(jsonResult);
        }
    }

    private void getBooksObject(Element table) {
        Elements th = table.getElementsByTag("th");
        Elements td = table.getElementsByTag("td");
        String genre = "";
        String category = "";
        String format = "";
        String year = "";
        String authors = "";
        String publisher = "";
        String ISBN = "";
        jsonResult.put("id", getId());
        jsonResult.put("time", "timeee");
        //TODO fix name
        jsonObject.put("name", getTitle());
        for (int i = 0, l = th.size(); i < l; i++) {
            String key = th.get(i).text();
            String value = td.get(i).text();
            if (key.equals("Genre")) {
                genre = value;
                jsonObject.put("genre",genre);
            }
            if (key.equals("Category")) {
                category = value;
                jsonObject.put("category",category);
            }
            if (key.equals("Format")) {
                format = value;
                jsonObject.put("format",format);
            }
            if (key.equals("Year")) {
                year = value;
                jsonObject.put("year",year);
            }
            if (key.equals("Authors")) {
                authors = value;
                jsonObject.put("authors",authors);
            }
            if (key.equals("Publisher")) {
                publisher = value;
                jsonObject.put("publisher",publisher);
            }
            if (key.equals("ISBN")) {
                ISBN = value;
                jsonObject.put("ISBN",ISBN);
            }
        }
        jsonResult.put("result", jsonObject);

        if (dataListener != null) {
            dataListener.onObjectFound(jsonResult);
        }
    }

    public interface DataListener {
        void onObjectFound(JSONObject jsonObject);

    }
}
