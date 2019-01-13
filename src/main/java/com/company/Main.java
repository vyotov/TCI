package com.company;

import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;

import java.io.IOException;

public class Main {

    private static final String SEARCH_WORD = "Clean Code: A Handbook of Agile Software Craftsmanship";

    public static void main(String[] args) throws IOException {
        Extractor extractor = new Extractor();
        extractor.getPageLinks("http://localhost:8888");
        String actual = new Gson().toJson(extractor.findObjectModelForSearchText(extractor.getAllObjects(),"Elvis Presley"));
        System.out.println(actual);
    }

}
