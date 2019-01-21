package com.company.utils;

import com.company.Models.Category;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Utils {

    private Element table;

    public Element getElement() {
        return table;
    }

    public void setTable(Element table) {
        this.table = table;
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
        System.out.println("RESULT: " + results);
        //Table
        if (results != null) {
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
        }
        return null;
    }

}
