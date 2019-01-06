package com.company.SearchAlgorithms;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Extractor implements DataExtractor.DataListener {

    private HashSet<String> links;
    private long startTime;
    private long endTime;

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
                //endTime = System.currentTimeMillis();

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
                new DataExtractor(url).setDataListener(this).exact();
                break;
            }
        }

    }


    public int getPageCount() {
        return links.size();
    }

    @Override
    public void onDataLoaded() {

    }
}
