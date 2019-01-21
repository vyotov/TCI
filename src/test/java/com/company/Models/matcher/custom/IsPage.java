package com.company.Models.matcher.custom;

import com.company.SearchAlgorithms.DataExtractor;
import com.company.SearchAlgorithms.Extractor;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.net.MalformedURLException;

public class IsPage extends TypeSafeMatcher {
    @Override
    public void describeTo(Description description) {
        description.appendText("22");
    }

    @Override
    protected boolean matchesSafely(Object e) {
        try {
            DataExtractor dataExtractor = new DataExtractor();
            Extractor extractor = new Extractor("http://localhost:8888",dataExtractor);
            if (extractor.getPageCount() != 0) {
                return false;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        return true;
    }

    @Factory
    public static Matcher page() {
        return new IsPage();
    }
}
