package com.hhu.util.compiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import com.hhu.util.DrawCalls;

/* 
Generates a new Java File with the given Sting as content. 
Expects, that the class the given String contains defines the Method 
    'public DrawCall build()'
 */
public class Compiler {

    public static DrawCalls compile(String code) {

        try {
            Path path = Path.of("src", "main", "java", "com", "hhu", "util", "compiler", "compiledClasses",
                    "GraphvizApp.java");

            String packageName = "com.hhu.util.compiler.compiledClasses";

            // String currentFileContent = FileManager.fileToString(path);
            if (!code.startsWith("package " + packageName)) {
                code = "package " + packageName + ";\n\n" + code;
            }
            
            Files.writeString(path, code);

            try {
                //Wait for file to exist before compiling & reflecting it
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new CompilationException("Exception while sleeping during compilation", e);
            }

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            

            compiler.run(null, null, null, path.toString());

            URLClassLoader loader = URLClassLoader.newInstance(new URL[] {
                    new File(".").toURI().toURL() });

            

            Class<?> klasse = Class.forName("com.hhu.util.compiler.compiledClasses.GraphvizApp", true, loader);

            Object o = klasse.getConstructor().newInstance();
            Object drawCalls = klasse.getMethod("build").invoke(o);

            return (DrawCalls) drawCalls;

        } catch (NoSuchMethodException e) {
            throw new CompilationException(
                    """
                            CompilationException: Expected execution entry method not found.

                            Required signature:
                                public DrawCalls build()
                            """, e);
        } catch (ClassCastException e) {
            throw new CompilationException(
                    "CompilationException: build-Method did not return a DrawCall.", e);
        } catch (IOException e) {
            throw new CompilationException("File error while writing source code", e);

        } catch (ClassNotFoundException e) {
            throw new CompilationException("Class loading failed (classpath/package mismatch)", e);

        } catch (InvocationTargetException e) {
            throw new CompilationException("User code threw exception: " + e.getTargetException(), e);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new CompilationException("Reflection setup failed (constructor/method issue)", e);
        }

    }
}