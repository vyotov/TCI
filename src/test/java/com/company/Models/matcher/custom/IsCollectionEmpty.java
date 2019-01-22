package com.company.Models.matcher.custom;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.HashSet;
import java.util.List;


public class IsCollectionEmpty extends TypeSafeMatcher<Boolean> {
    private HashSet<String> list;

    public IsCollectionEmpty(HashSet<String> list) {
        this.list = list;

    }


    @Override
    protected boolean matchesSafely(Boolean actual) {
        return list.isEmpty() || (list.isEmpty() == actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an empty collection");
    }

    @Factory
    public static Matcher isListEmpty(HashSet<String> value) {
        return new IsCollectionEmpty(value);
    }
}
