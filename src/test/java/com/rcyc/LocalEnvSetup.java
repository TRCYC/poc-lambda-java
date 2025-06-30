package com.rcyc;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class LocalEnvSetup {

    private static final String FILE_PATH_PREFIX = "/Users/personal/Documents/git/testfiles/";

    public static void setupEnvironment() {
        String env = System.getenv("ENV");
        if (env == null) {
            env = "dev"; // Default to 'dev' if not set
        }

        try {
            Properties devProperties = loadProperties("config." + env + ".properties");
            Properties secretProperties = loadProperties(FILE_PATH_PREFIX + "config." + env + ".secret.properties");

            devProperties.forEach((key, value) -> System.setProperty(key.toString().toUpperCase(), value.toString()));
            secretProperties.forEach((key, value) -> System.setProperty(key.toString().toUpperCase(), value.toString()));

        } catch (IOException e) {
            System.err.println("Error loading properties: " + e.getMessage());
        }
    }

    private static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(Paths.get(filePath).toFile())) {
            properties.load(fis);
        }
        return properties;
    }

    public static String get(String key) {
        return System.getProperty(key.toUpperCase());
    }
}