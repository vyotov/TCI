package com.company;

import java.io.IOException;

public class Main {

    private static final String SEARCH_WORD = "Clean Code: A Handbook of Agile Software Craftsmanship";

    public static void main(String[] args) throws IOException {
        //Create instance:
        WebCrawler webCrawler = new WebCrawler("http://localhost:8888/sample_site_to_crawl/");
        //Start crawler:
        webCrawler.start();
        System.out.print("################################## WebCrawler started ##################################\n");

        //Search by key word:
        if (webCrawler.searchAlgorithmWithWordBFS(SEARCH_WORD) != null) {
            System.out.print(SEARCH_WORD);
        }

        //Search by id:
        if (webCrawler.searchById(1) != null) {
            System.out.print(SEARCH_WORD);
        }
        System.out.println("");

    }
}
