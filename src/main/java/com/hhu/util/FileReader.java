package com.hhu.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public static String fileToString(Path path){
        String content;

        try {
            content = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading code file.";
        }
        return content;
    }

}


