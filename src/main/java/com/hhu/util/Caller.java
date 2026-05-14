package com.hhu.util;

import java.nio.file.Path;
import java.util.Optional;

/* Returns information about the Calling Class
    since it only checks if the class is called GraphvizApp, 
    the class that first calls the program is found, but also the class after recompilation 
    and the testing class, solong they are called GraphvizApp.
*/
public class Caller {
    /*
     * Returns the String-representation of the class, that called DrawCalls.record
     */
    static String findCallerClass() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        Optional<Path> path = walker.walk(stream -> stream
                .filter(Caller::isNotUtilityClass)
                .findFirst()
                .map(f -> {
                    String name = f.getDeclaringClass().getName();
                    // Check test or main 
                    String baseDir = name.endsWith("Test") ? "src/test/java/" : "src/main/java/";
                    return Path.of(baseDir + name.replace(".", "/") + ".java");
                }));

        return path.map(FileManager::fileToString).orElse("");
    }

    /*
     * Returns the line, that called DrawCalls.record
     */
    static int findCallerLine() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        return walker.walk(stream -> stream
                .filter(Caller::isNotUtilityClass)
                .findFirst()
                .map(f -> f.getLineNumber() - 1) //fix off by 1 error
                .orElse(-1));
    }
    
    private static boolean isNotUtilityClass(StackWalker.StackFrame frame) {
        String name = frame.getClassName();
        return !name.equals(Caller.class.getName()) &&
                !name.equals(DrawCalls.class.getName()) &&
                !name.equals(Visualizer.class.getName()) &&
                !name.contains("java.lang.StackWalker"); // Ensure we skip internal walker frames
    }

}
