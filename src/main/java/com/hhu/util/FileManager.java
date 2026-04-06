package com.hhu.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public static String fileToString(Path path) {
        String content;

        try {
            content = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading code file.";
        }
        return content;
    }


    //Assumption: Caller Class liegt unter src/main/java
    public static String findCallerClass() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        Path path = walker.walk(stream -> stream
                .filter(frame -> !frame.getClassName().startsWith("com.hhu.util")
                        && !frame.getClassName().startsWith("com.hhu.datastructures"))
                .findFirst()
                .map(StackWalker.StackFrame::getDeclaringClass)
                .map(Class::getName)
                .map(name -> "src/main/java/" + name.replace(".", "/") + ".java")
                .map(Path::of)
                .orElse(null));
        

        return fileToString(path);
    }
    

    public static int findCallerLine() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        int lineNumber = walker.walk(stream -> stream
                .filter(frame -> !frame.getClassName().startsWith("com.hhu.util")
                        && !frame.getClassName().startsWith("com.hhu.datastructures"))
                .findFirst()
                .map(StackWalker.StackFrame::getLineNumber)
                .orElse(-1));

        //fix off by 1 error
        return lineNumber-1;

    }

}
