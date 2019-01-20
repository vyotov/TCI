package com.company.Models.custom_rule;

import com.company.SearchAlgorithms.Extractor;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.net.MalformedURLException;

public class InitializationRule implements TestRule {

    private Extractor extractor;

    public InitializationRule(Extractor extractor){
        this.extractor = extractor;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                extractor = new Extractor("http://localhost:8888");

            }
        };
    }
}