package com.company.SearchAlgorithms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataExtractor {
    private JSONArray jsonArray;
    private JSONObject jsonObject2;
    private String url;
    private DataListener dataListener;

    public DataExtractor setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
        return this;
    }

    public DataExtractor(String url) {
        this.jsonArray = new JSONArray();
        this.jsonObject2 = new JSONObject();
        this.url = url;
    }

    public void exact() throws IOException {
        Document doc = Jsoup.connect(url).get();
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

    private void getMusicObject(Element table) {

    }

    private void getMoviesObject(Element table) {

    }

    private void getBooksObject(Element table) {

    }

    public interface DataListener {
        void onDataLoaded();
    }
}
