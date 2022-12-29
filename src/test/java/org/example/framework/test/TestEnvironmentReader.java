package framework.example.org.test;

import java.util.ResourceBundle;

public class TestEnvironmentReader {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
