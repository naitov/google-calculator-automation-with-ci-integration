package org.example.framework.test;

import java.util.ResourceBundle;

public class TestEnvironmentReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
