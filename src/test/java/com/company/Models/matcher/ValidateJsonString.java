package com.company.Models.matcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.io.IOException;


public class ValidateJsonString extends TypeSafeMatcher<Boolean> {


    private String value;

    public ValidateJsonString(String value) {
        this.value = value;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("checks if string is valid json");
    }


    @Override
    protected boolean matchesSafely(Boolean expected) {
        return isJSONValid(value) || (isJSONValid(value) ==expected);
    }

    private boolean isJSONValid(String jsonInString) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Factory
    public static Matcher isValidJson(String value) {
        return new ValidateJsonString(value);
    }

}
