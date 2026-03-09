package com.hhu.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class FileReader {

    public static String fileToString(Path path)
            throws IOException {

        String content = Files.readString(path);
        return content;
    }
    

    //Nice to have, but breaks line numbers for highlighting, so not used for now
    private static String removeImports(String content) throws IOException {
        return content.lines()
                .filter(line -> !line.trim().startsWith("import "))
                .collect(Collectors.joining("\n"));
    }

    // Nice to have, but breaks line numbers for highlighting, so not used for now
    private static String removeImportsAndBlankLines(String content) {
        return content.lines()
                .filter(line -> !line.trim().startsWith("import"))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        lines -> {
                            return java.util.stream.IntStream.range(0, lines.size())
                                    .filter(i -> {
                                        String line = lines.get(i);
                                        if (!line.trim().isEmpty())
                                            return true;
                                        return i == 0 || !lines.get(i - 1).trim().isEmpty();
                                    })
                                    .mapToObj(lines::get)
                                    .collect(Collectors.joining("\n"));
                        }));
    }
}
