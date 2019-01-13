package com.company;

import com.company.SearchAlgorithms.Extractor;
import com.google.gson.Gson;

import java.io.IOException;

public class Main {

    private static final String SEARCH_WORD = "Clean Code: A Handbook of Agile Software Craftsmanship";

    public static void main(String[] args) throws IOException {
        Extractor bwc = new Extractor();
        bwc.getPageLinks("http://localhost:8888");
      //  System.out.println( new Gson().toJsonTree( bwc.searchById("202")));
        //bwc.getAllObjects();
      //  bwc.searchModelByKeyWord("Mike Judge");
        System.out.println(bwc.getAllObjects());
        //System.out.println(bwc.getPageCount());
    }

}
