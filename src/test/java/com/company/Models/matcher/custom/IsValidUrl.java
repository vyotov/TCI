package com.company.Models.matcher.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class IsValidUrl extends TypeSafeMatcher<Boolean> {

    private String value;

    public IsValidUrl(String value) {
        this.value = value;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("method to check if the string  is valid json String: ");
    }

    @Override
    protected boolean matchesSafely(Boolean expected) {
        return isValidURL(value) || (isValidURL(value) == expected);
    }

    public boolean isValidURL(String urlStr) {
        try {
            new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
    
    @Factory
    public static Matcher IsValidUrl(String value) {
        return new IsValidUrl(value);
    }

}
