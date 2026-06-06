package com.hhu.util;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import com.hhu.util.compiler.Compiler;
/* Returns information about the Calling Class
    since it only checks if the class is called GraphvizApp, 
    the class that first calls the program is found, but also the class after recompilation 
    and the testing class, solong they are called GraphvizApp.
*/
public class Caller {
    private static final StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
    /*
     * Returns the String-representation of the class, that called DrawCalls.record
     */
    static String findCallerClass() {
        Optional<Path> path = walker.walk(stream -> stream.filter(Caller::isNotUtilityClass).findFirst())
                .map(f -> {
                    String name = f.getDeclaringClass().getName();
                    return Compiler.getGeneratedSourcePath(name).orElseGet(() -> {
                        // Check test or main
                        String baseDir = name.endsWith("Test") ? "src/test/java/" : "src/main/java/";
                        return Path.of(baseDir + name.replace(".", "/") + ".java");
                    });
                });

        return path.map(FileManager::fileToString).orElse("");
    }
    /*
     * Returns the line, that called DrawCalls.record
     */
    static int findCallerLine() {
        return walker.walk(stream -> stream.filter(Caller::isNotUtilityClass).findFirst())
                .map(f -> f.getLineNumber() - 1) //fix off by 1 error
                .orElse(-1);
    }
    
    private static boolean isNotUtilityClass(StackWalker.StackFrame frame) {
        String name = frame.getClassName();
        // Ensure we skip internal walker frames
        return !name.contains("java.lang.StackWalker") && 
                !Set.of(Caller.class.getName(), DrawCalls.class.getName(), Visualizer.class.getName()).contains(name);
    }
}
