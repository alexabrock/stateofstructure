package com.hhu.util.compiler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.hhu.util.DrawCalls;
import com.hhu.util.Visualizer;

/* 
Generates a new Java File with the given Sting as content in a Temp directory. 
Expects, that the class the given String contains defines the Method 
    'public void build()'
 */
public class Compiler {
    private final static String RECORD_CALL = "Visualizer.record();";
    private static final String USER_CLASS_NAME = "StateOfStructure";
    private static final Path WORKSPACE_DIR = Path.of(System.getProperty("java.io.tmpdir"), "stateofstructure"); //temp directory
    private static final Path USER_SOURCE_FILE = WORKSPACE_DIR.resolve(USER_CLASS_NAME + ".java");
    private static final Path COMPILED_CLASSES_DIR = WORKSPACE_DIR.resolve("classes");

    public static DrawCalls compile(String code) {

        try {
            Files.createDirectories(WORKSPACE_DIR);
            Files.createDirectories(COMPILED_CLASSES_DIR);

            code = addRecordCalls(code);

            Files.writeString(USER_SOURCE_FILE, code);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            if (compiler == null) {
                throw new CompilationException(
                        "Environment error: No Java compiler was found. Please run StateOfStructure with a JDK, not a JRE.");
            }

            String classpath = System.getProperty("java.class.path");

            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();

            int compilationResult = compiler.run(null, null,
                    errorStream,
                    "-classpath", classpath,
                    "-d", COMPILED_CLASSES_DIR.toString(),
                    USER_SOURCE_FILE.toString());

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
            try (URLClassLoader loader = new URLClassLoader(
                    new URL[] { COMPILED_CLASSES_DIR.toUri().toURL() }, // path to .class file location
                    Compiler.class.getClassLoader()) {
                @Override
                protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                    if (name.equals(USER_CLASS_NAME)) {
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
            }) {

                Class<?> klasse = Class.forName(USER_CLASS_NAME, true, loader);

                Object o = klasse.getConstructor().newInstance();
                // invoke the build method. After this, the static drawcalls-Wrapper Visualizer
                // is filled with drawcall recordings
                klasse.getMethod("main").invoke(o);
                // get the now filled drawcalls
                DrawCalls drawCalls = Visualizer.getDrawCalls();

                return drawCalls;
            }

        } catch (NoSuchMethodException e) {
            throw new CompilationException(
                    """
                            Execution failed: The method 'public void main()' was not found.
                            Make sure the provided code defines a class named 'StateOfStructure' with a 'main()' method.
                            """, e);
        } catch (ClassCastException e) {
            throw new CompilationException(
                    "Result error: The build process did not produce valid visualization data (DrawCalls).", e);
        } catch (IOException e) {
            throw new CompilationException(
                    "Environment error: Failed to write source file or create output directory.", e);
        } catch (ClassNotFoundException e) {
            throw new CompilationException(
                    "Loading error: Could not find the compiled 'StateOfStructure' class. Check for package declaration mismatches.",
                    e);
        } catch (InvocationTargetException e) {
            throw new CompilationException(
                    "Runtime error in user code: " + e.getTargetException().toString(), e);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new CompilationException(
                    "Reflection error: Could not instantiate 'StateOfStructure'. Ensure it has a public no-args constructor.",
                    e);
        }

    }

    /* Adds the Visualizer.record() calls after every line inside the build()
    method, that is after the collection.register(...) */
    private static String addRecordCalls(String code) {
        String methodHeader = "public void main() {";
        int startIdx = code.indexOf(methodHeader);


        // if the build() method doesn't exist, return original code
        if (startIdx == -1) {
            // & method shouldn't be static
            int staticIdx = code.indexOf("public static void main() {");
            if (staticIdx != -1) {
                throw new CompilationException(
                        """
                                Execution failed: The main method should not be static.
                                
                                Make sure the provided code defines a class named 'StateOfStructure' with a not static 'main()' method.
                                """);
                
            }
            return code;
        }   

        // Identify the boundaries of the build() method body
        int bodyStart = startIdx + methodHeader.length();
        int braceCount = 1;
        int bodyEnd = -1;

        for (int i = bodyStart; i < code.length(); i++) {
            if (code.charAt(i) == '{')
                braceCount++;
            else if (code.charAt(i) == '}')
                braceCount--;

            if (braceCount == 0) { //found the last closing brace
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

        // Find the Visualizer.register call in the main method
        int registerIdx = body.indexOf("Visualizer.register");

        if (registerIdx == -1) {
            throw new CompilationException("""
                                            No 'Visualizer.register(your datastructure)' found in code.

                                            Register a datastructure to start the visualization.""");
        }

        // Find the semicolon of the register line to start processing AFTER it
        int startRecordingIdx = body.indexOf(";", registerIdx) + 1;

        //split the body
        String setupPart = body.substring(0, startRecordingIdx);
        String activePart = body.substring(startRecordingIdx);

        String modifiedActivePart = activePart.replaceAll("(?m)$", RECORD_CALL);
        // Reconstruct the full string
        return head + setupPart + modifiedActivePart + tail;
    }

    public static String getRecordCallReplacement() {
        return RECORD_CALL;
    }
    static Path getWorkspaceDir() {
        return WORKSPACE_DIR;
    }

    public static Optional<Path> getGeneratedSourcePath(String className) {
        return USER_CLASS_NAME.equals(className) ? Optional.of(USER_SOURCE_FILE) : Optional.empty();

    }


}