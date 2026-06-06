package com.hhu.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static String fileToString(Path path) {
        try {
            if (Files.exists(path))
                return Files.readString(path); //simply read the path for maven compile

            String jarPath = path.toString().replace('\\', '/').replaceFirst("^src/main/resources/", ""); //remove directory for jar
            if (jarPath.startsWith("/"))
                jarPath = jarPath.substring(1);

            if (!jarPath.isBlank()) {
                InputStream inputStream = getStream(jarPath);
                if (inputStream != null) {
                    try (inputStream) {
                        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                    }
                }
            }

            return Files.readString(path);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read file or bundled resource: " + path, e);
        }
    }

    private static InputStream getStream(String name) {
        InputStream inputStream = FileManager.class.getResourceAsStream("/" + name);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return inputStream != null ? inputStream
                : (classLoader != null ? classLoader.getResourceAsStream(name)
                        : FileManager.class.getClassLoader().getResourceAsStream(name));
    }
}