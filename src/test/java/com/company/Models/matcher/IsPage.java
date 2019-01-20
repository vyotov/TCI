package com.company.Models.matcher;

import com.company.SearchAlgorithms.Extractor;
import org.hamcrest.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class IsPage extends TypeSafeMatcher {
    @Override
    public void describeTo(Description description) {
        description.appendText("no pages");
    }

    @Override
    protected boolean matchesSafely(Object e) {
        try {
            Extractor extractor = new Extractor("http://localhost:8888");
            if(extractor.getPageCount()!=0)
            {
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
