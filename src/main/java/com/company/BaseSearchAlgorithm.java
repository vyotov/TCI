package com.company;

import com.company.SearchAlgorithms.ISearch;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public abstract class BaseSearchAlgorithm implements ISearch {

    protected int pages;
    protected int depth;
    protected String URL;
    protected Document doc;
    protected int sLength;
    protected JSONArray jsonArray;

    @Override
    public void connectToJsoup() {
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected JSONObject getJSON(Elements th, Elements td) {
        JSONObject result = new JSONObject();
        for (int i = 0, l = th.size(); i < l; i++) {
            String k = th.get(i).text();
            String v = td.get(i).text();
            if (k.equals("Authors") | k.equals("Writers") | k.equals("Stars")) {
                jsonArray = new JSONArray();
                String[] temp = v.split(",");
                for (int j = 0, length = temp.length; j < length; j++) {
                    jsonArray.add(temp[j]);
                }
                result.put(k, jsonArray);
            } else
                result.put(k, v);
        }
        return result;
    }


    public abstract int getNumberOfPagers();
    public abstract int getTimeElapsed();
    public abstract int getSearchDepth();
}
