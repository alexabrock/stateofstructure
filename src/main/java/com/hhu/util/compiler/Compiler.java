package com.hhu.util.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.hhu.util.DrawCalls;
import com.hhu.util.Visualizer;

/* 
Generates a new Java File with the given Sting as content. 
Expects, that the class the given String contains defines the Method 
    'public void build()'
 */
public class Compiler {
    private final static String recordCallReplacement = "Visualizer.record();";

    public static DrawCalls compile(String code) {

        try {
            Path path = Path.of("src", "main", "java", "GraphvizApp.java");

            code = addRecordCalls(code);

            Files.writeString(path, code);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            // Compile into isolated dir, not target/classes
            Path outputDir = Path.of("target", "user-compiled");
            Files.createDirectories(outputDir);

            String classpath = System.getProperty("java.class.path");

            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

            int compilationResult = compiler.run(null, null,
                    errorStream,
                    "-classpath", classpath,
                    "-d", outputDir.toString(),
                    path.toString());
            if (compilationResult != 0) {
                throw new CompilationException("Syntax Error in User Code:\n\n" + errorStream.toString());
            }
            /*
             * URLClassLoader loader = URLClassLoader.newInstance(new URL[] {
             * new File(".").toURI().toURL() });
             * The default ClassLoader doesn't reload a class once it got compiled and
             * loaded.
             * We want the ClassLoader to always reload the GraphVizApp as if it has never
             * seen it bevore
             * (thus not chaching any state).
             * 
             * Normally, the Classloader loads classes parent-first. Every Classloader,
             * except the very toplevel one, has a parent.
             * When asked to load a class, it recursevely askes their parent
             * "Have you already loaded this class?"
             * Only if no parent has seen the class, the bottom-level Classloader will load
             * the class itself.
             * 
             * By overwriting loadClass this behaviour is changed.
             * It now does child-first (aka load the class yourself) for GraphvizApp
             * But still delegated all other classes parent-first.
             */
            URLClassLoader loader = new URLClassLoader(
                    new URL[] { outputDir.toUri().toURL() }, // path to .class file location
                    Compiler.class.getClassLoader()) {
                @Override
                protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                    if (name.equals("GraphvizApp")) {
                        Class<?> c = findLoadedClass(name);
                        if (c == null)
                            c = findClass(name); // look in outputDir first. Does not ask parent.
                                                 // asking parent-first for GraphVisApp
                                                 // resulted in not having the newest .class file, once it
                                                 // had been recompiled
                        if (resolve)
                            resolveClass(c);
                        return c;
                    }
                    return super.loadClass(name, resolve); // normal parent-first for everything else
                }
            };

            Class<?> klasse = Class.forName("GraphvizApp", true, loader);

            Object o = klasse.getConstructor().newInstance();
            // invoke the build method. After this, the static drawcalls-Wrapper Visualizer
            // is filled with drawcall recordings
            klasse.getMethod("build").invoke(o);
            // get the now filled drawcalls
            DrawCalls drawCalls = Visualizer.getDrawCalls();

            return drawCalls;

        } catch (NoSuchMethodException e) {
            throw new CompilationException(
                    """
                            Execution failed: The method 'public void build()' was not found.
                            Make sure the provided code defines a class named 'GraphvizApp' with a 'build()' method.
                            """, e);
        } catch (ClassCastException e) {
            throw new CompilationException(
                    "Result error: The build process did not produce valid visualization data (DrawCalls).", e);
        } catch (IOException e) {
            throw new CompilationException(
                    "Environment error: Failed to write source file or create output directory.", e);
        } catch (ClassNotFoundException e) {
            throw new CompilationException(
                    "Loading error: Could not find the compiled 'GraphvizApp' class. Check for package declaration mismatches.",
                    e);
        } catch (InvocationTargetException e) {
            // the user's code itself threw an exception
            throw new CompilationException(
                    "Runtime error in user code: " + e.getTargetException().toString(), e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CompilationException(
                    "Reflection error: Could not instantiate 'GraphvizApp'. Ensure it has a public no-args constructor.",
                    e);
        }

    }

    // Adds the Visualizer.record() calls after every line inside the build()
    // method, that is after the collection.register(...)
    private static String addRecordCalls(String code) {
        String methodHeader = "public void build() {";
        int startIdx = code.indexOf(methodHeader);

        // if the build() method doesn't exist, return original code
        if (startIdx == -1)
            return code;

        // Identify the boundaries of the build() method body
        int bodyStart = startIdx + methodHeader.length();
        int braceCount = 1;
        int bodyEnd = -1;

        for (int i = bodyStart; i < code.length(); i++) {
            if (code.charAt(i) == '{')
                braceCount++;
            else if (code.charAt(i) == '}')
                braceCount--;

            if (braceCount == 0) {
                bodyEnd = i;
                break;
            }
        }

        if (bodyEnd == -1)
            return code; // Safety check for malformed code
        
        // Split the code into Head (imports/class), Body (build method), and Tail
        // (rest of class)
        String head = code.substring(0, bodyStart);
        String body = code.substring(bodyStart, bodyEnd);
        String tail = code.substring(bodyEnd);

        // Find the Visualizer.register call to use as a pivot
        String registerKey = "Visualizer.register";
        int registerIdx = body.indexOf(registerKey);

        if (registerIdx == -1) {
            // If no register is found, don't start recording
            return head + body + tail;
        }

        // Find the semicolon of the register line to start processing AFTER it
        int startRecordingIdx = body.indexOf(";", registerIdx) + 1;

        String setupPart = body.substring(0, startRecordingIdx);
        String activePart = body.substring(startRecordingIdx);

        // Pattern: [Target] [Lookahead]
        // Lookahead :( ? [Direction to look to] [Logic] [When to stop looking])
        // Uses Negative Lookahead (?!) to avoid semicolons inside parentheses
        String regex = ";(?!.*\\))";

        String modifiedActivePart = activePart.replaceAll(regex, ";" + recordCallReplacement);
        modifiedActivePart = activePart.replaceAll("(?m)$", recordCallReplacement);
        // Reconstruct the full string
        return head + setupPart + modifiedActivePart + tail;
    }

    public static String getRecordCallReplacement(){
        return recordCallReplacement;
    }
}