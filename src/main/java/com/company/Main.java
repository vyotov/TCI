package com.company;

import com.company.SearchAlgorithms.Extractor;

import java.io.IOException;

public class Main {

    private static final String SEARCH_WORD = "Clean Code: A Handbook of Agile Software Craftsmanship";

    public static void main(String[] args) throws IOException {
        Extractor bwc = new Extractor();
        bwc.getPageLinks("http://localhost:8888");
        bwc.searchById("101");
        System.out.println(bwc.getPageCount());
    }

}
