package com.hhu.util;

import java.lang.StackWalker.Option;
import java.lang.StackWalker.StackFrame;
import java.nio.file.Path;

/* Returns information about the Calling Class*/
public class Caller {
    /*
     * Returns the String-representation of the class, that called DrawCalls.record
     *  Assumption: Caller Class liegt unter src/main/java
     */
    public static String findCallerClass() {
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

        Path path = walker.walk(stream -> stream
                // filter all steps between outside-caller and this class (such as Application.java)
                .filter(frame -> !frame.getClassName().startsWith("com.hhu.util")
                        && !frame.getClassName().startsWith("com.hhu.datastructures"))
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
                //filter all steps between outside-caller and this class (such as Application.java)
                .filter(frame -> !frame.getClassName().startsWith("com.hhu.util")
                        && !frame.getClassName().startsWith("com.hhu.datastructures"))
                .findFirst()
                .map(StackWalker.StackFrame::getLineNumber)
                .orElse(-1));
    
        // fix off by 1 error
        return lineNumber - 1;
    
    }
    
}
