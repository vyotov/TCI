package com.company;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import com.company.utils.Utils;
import com.google.gson.Gson;

import java.io.IOException;

public class Main {

    private static final String SEARCH_WORD = "Clean Code: A Handbook of Agile Software Craftsmanship";

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException {
        DataExtractor dataExtractor = new DataExtractor();
        Utils util = new Utils();
        Extractor extractor = new Extractor("http://localhost:8888",dataExtractor,util);
        String actual = new Gson().toJson(extractor.searchById("202",dataExtractor));
        System.out.println(actual);
    }

}
