package com.hhu.util;

import java.nio.file.Path;

/* Returns information about the Calling Class
    since it only checks if the class is called GraphvizApp, 
    the class that first calls the program is found, but also the class after recompilation 
    and the testing class, solong they are called GraphvizApp.
*/
public class GraphvizAppCaller {
    /*
     * Returns the String-representation of the class, that called DrawCalls.record
     * Assumption: Caller Class is named GraphVizApp 
     */
    public static String findCallerClass() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        Path path = walker.walk(stream -> stream
                .filter(f -> f.getClassName().contains("GraphvizApp"))
                .findFirst()
                .map(StackWalker.StackFrame::getDeclaringClass)
                .map(Class::getName)
                .map(name -> "src/main/java/" + name.replace(".", "/") + ".java")
                .map(Path::of)
                .orElse(null));
        return FileManager.fileToString(path);
    }

    /*
     * Returns the line, that called DrawCalls.record
     */
    public static int findCallerLine() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        int lineNumber = walker.walk(stream -> stream
                .filter(f -> f.getClassName().contains("GraphvizApp"))
                .findFirst()
                .map(StackWalker.StackFrame::getLineNumber)
                .orElse(-1));

        // fix off by 1 error
        return lineNumber - 1;

    }

}
