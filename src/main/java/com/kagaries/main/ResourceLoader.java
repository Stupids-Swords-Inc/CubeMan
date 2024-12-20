package com.kagaries.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class ResourceLoader {
    public InputStream getResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        return inputStream;
    }

    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        // Ensure the path ends with a "/"
        if (!path.endsWith("/")) {
            path += "/";
        }

        // Use ClassLoader to list resources
        Enumeration<java.net.URL> resources = getClass().getClassLoader().getResources(path);

        while (resources.hasMoreElements()) {
            java.net.URL resource = resources.nextElement();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.openStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    filenames.add(path + line); // Prepend the directory path
                }
            }
        }

        return filenames;
    }

    public void preloadResources() throws IOException {
        String resourceDirectory = "src/main/resources"; // Specify your directory
        List<String> names = getResourceFiles(resourceDirectory);

        for (String string : names) {
            Game.getLogger().info("Loading: {}", string);
            getResourceAsStream(string);
        }
    }
}
