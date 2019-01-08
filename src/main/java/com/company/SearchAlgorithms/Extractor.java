package com.company.SearchAlgorithms;

import com.company.Models.Book;
import com.company.Models.Movie;
import com.company.Models.Music;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Extractor {

    private HashSet<String> links;

    private Gson mGson = new Gson();
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

    public void searchById(String searchById) throws IOException {
        //Loop over the list:
        for (String url : links) {
            //Check which url matches the search word:
            if (url.contains(searchById)) {
                new DataExtractor(url).setDataListener(new DataExtractor.DataListener() {
                    @Override
                    public void onMusic(Music music) {
                        System.out.println(mGson.toJson(music));
                    }

                    @Override
                    public void onMovie(Movie movie) {
                        System.out.println(mGson.toJson(movie));
                    }

                    @Override
                    public void onBook(Book book) {
                        System.out.println(mGson.toJson(book));
                    }
                }).exact();
                break;
            }
        }
    }

    public void getAllObjects() throws IOException {
        for (String url : links) {
            String number = url.substring(url.lastIndexOf("=") + 1);
            if (!number.equals("") && isInt(number)) {
                new DataExtractor(url).setDataListener(new DataExtractor.DataListener() {
                    @Override
                    public void onMusic(Music music) {
                        System.out.println(mGson.toJson(music));
                    }

                    @Override
                    public void onMovie(Movie movie) {
                        System.out.println(mGson.toJson(movie));
                    }

                    @Override
                    public void onBook(Book book) {
                        System.out.println(mGson.toJson(book));
                    }
                }).exact();
            }
        }
    }

    private boolean isInt(String string) {
        return string.matches("\\d+");
    }


    public int getPageCount() {
        return links.size();
    }

}
