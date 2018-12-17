package com.company;

import com.company.SearchAlgorithms.*;
import org.json.simple.JSONObject;

import java.io.IOException;

public class WebCrawler {
    private ISearch dfs;

    public WebCrawler(String URL) {
        dfs = new DFS(URL);
    }

    public void start() {
        dfs.connectToJsoup();
    }

    public JSONObject searchAlgorithmWithWordBFS(String searchWord) throws IOException {
        return ((DFS) dfs).dfsSearchByText(searchWord);

    }


    public JSONObject searchById(int id) throws IOException {
        return ((DFS) dfs).dfsSearchById(id);

    }
}
