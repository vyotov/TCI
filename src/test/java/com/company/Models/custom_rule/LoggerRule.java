package com.company.Models.custom_rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class LoggerRule implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long start = System.currentTimeMillis();
                try {
                    base.evaluate();
                } finally {
                    System.out.printf("Time taken for %s: %s milli sec%n", description.getDisplayName(), System.currentTimeMillis() - start);
                }
            }
        };
    }
}
