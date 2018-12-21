package com.company.SearchAlgorithms;

import com.company.BaseSearchAlgorithm;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DFS extends BaseSearchAlgorithm {

    public DFS(String URL) {
        this.URL = URL;
    }

    public JSONObject dfsSearchByText(String searchText) throws IOException {
        jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResultObject = new JSONObject();

        sLength = URL.length();
        pages++;
        Elements linksToVisit = doc.getElementsByAttributeValue("class", "nav");
        Elements ok = linksToVisit.get(0).getElementsByTag("li");

        ok.remove(ok.last());
        for (Element link : ok) {
            Elements docRef = link.getElementsByTag("a");
            doc = Jsoup.connect(URL + docRef.attr("href")).get();
            pages++;
            if (depth == 0) {
                depth++;
            }
            Elements detailpage = doc.getElementsByAttributeValue("class", "items");
            Elements results = detailpage.get(0).getElementsByTag("li");
            for (Element result : results) {
                Elements details = result.getElementsByTag("a");
                doc = Jsoup.connect(URL + details.attr("href")).get();
                pages++;
                String name = doc.getElementsByTag("img").attr("alt");
                if (depth == 1) {
                    depth++;
                }
                if (searchText.equals(name)) {
                    Elements detailPage = doc.getElementsByAttributeValue("class", "media-details");
                    Element table = detailPage.select("table").first();
                    Elements th = table.getElementsByTag("th");
                    Elements td = table.getElementsByTag("td");
                    jsonObject = getJSON(th, td);
                    String category = jsonObject.get("Category").toString();
                    jsonObject.put("category", category);
                    jsonResultObject.put("result", jsonObject);
                    return jsonResultObject;
                }
            }
        }
        return new JSONObject();
    }

    public JSONObject dfsSearchById(int id) throws IOException {
        jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonResultObject = new JSONObject();
        sLength = URL.length();
        pages++;
        Elements linksToVisit = doc.getElementsByAttributeValue("class", "nav");
        Elements ok = linksToVisit.get(0).getElementsByTag("li");
        ok.remove(ok.last());
        for (Element link : ok) {
            Elements docRef = link.getElementsByTag("a");
            doc = Jsoup.connect(URL + docRef.attr("href")).get();
            pages++;
            if (depth == 0) {
                depth++;
            }
            Elements detailPage = doc.getElementsByAttributeValue("class", "items");
            Elements results = detailPage.get(0).getElementsByTag("li");
            for (Element result : results) {
                Elements details = result.getElementsByTag("a");
                doc = Jsoup.connect(URL + details.attr("href")).get();
                pages++;
                int theID = Integer.parseInt(doc.baseUri().substring(sLength + 15));
                if (depth == 1) {
                    depth++;
                }
                if (id == theID) {
                    Elements detailspage = doc.getElementsByAttributeValue("class", "media-details");
                    Element table = detailspage.select("table").first();
                    String name = detailspage.select("h1").first().text();
                    Elements th = table.getElementsByTag("th");
                    Elements td = table.getElementsByTag("td");
                    jsonObject = getJSON(th, td);

                    String category = jsonObject.get("Category").toString();
                    jsonObject.put("category", category);
                    jsonObject.put("id", id);
                    jsonObject.put("title", name);
                    jsonResultObject.put("result", getJSON(th, td));

                    // System.out.println(jsonResultObject);
                    return jsonResultObject;
                } else if (results.last().equals(result)) {
                    depth = 0;
                }
            }
        }
        return new JSONObject();
    }

    @Override
    public int getNumberOfPagers() {
        return 0;
    }

    @Override
    public int getTimeElapsed() {
        return 0;
    }

    @Override
    public int getSeachDepth() {
        return 0;
    }
}
