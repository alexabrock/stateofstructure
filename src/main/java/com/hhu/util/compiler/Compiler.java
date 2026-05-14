package com.hhu.util.compiler;

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

    public static DrawCalls compile(String code) {

        try {
            Path path = Path.of("src", "main", "java", "GraphvizApp.java");
            Files.writeString(path, code);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            // Compile into isolated dir, not target/classes
            Path outputDir = Path.of("target", "user-compiled");
            Files.createDirectories(outputDir);

            String classpath = System.getProperty("java.class.path");
            compiler.run(null, null, null,
                    "-classpath", classpath,
                    "-d", outputDir.toString(),
                    path.toString());

            /*
             * URLClassLoader loader = URLClassLoader.newInstance(new URL[] {
             * new File(".").toURI().toURL() });
             * The default ClassLoader doesn't reload a class once it got compiled and loaded. 
             * We want the ClassLoader to always reload the GraphVizApp as if it has never seen it bevore
             * (thus not chaching any state).
             * 
             * Normally, the Classloader loads classes parent-first. Every Classloader, except the very toplevel one, has a parent. 
             * When asked to load a class, it recursevely askes their parent "Have you already loaded this class?" 
             * Only if no parent has seen the class, the bottom-level Classloader will load the class itself. 
             * 
             * By overwriting loadClass this behaviour is changed.
             * It now does child-first (aka load the class yourself) for GraphvizApp
             * But still delegated all other classes parent-first.
             */
            URLClassLoader loader = new URLClassLoader(
                    new URL[] { outputDir.toUri().toURL() }, //path to .class file location
                    Compiler.class.getClassLoader()) {
                @Override
                protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                    if (name.equals("GraphvizApp")) {
                        Class<?> c = findLoadedClass(name);
                        if (c == null)
                            c = findClass(name); // look in outputDir first. Does not ask parent. 
                                                 //asking parent-first for GraphVisApp  
                                                 //resulted in not having the newest .class file, once it 
                                                 //had been recompiled 
                        if (resolve)
                            resolveClass(c);
                        return c;
                    }
                    return super.loadClass(name, resolve); // normal parent-first for everything else
                }
            };

            Class<?> klasse = Class.forName("GraphvizApp", true, loader);

            Object o = klasse.getConstructor().newInstance();
            //invoke the build method. After this, the static drawcalls-Wrapper Visualizer is filled with drawcall recordings
            klasse.getMethod("build").invoke(o);
            //get the now filled drawcalls
            DrawCalls drawCalls = Visualizer.getDrawCalls();

            return drawCalls;

        } catch (NoSuchMethodException e) {
            throw new CompilationException(
                    """
                            CompilationException: Expected execution entry method not found.

                            Required signature:
                                public void build()
                            """, e);
        } catch (ClassCastException e) {
            throw new CompilationException(
                    "CompilationException: build-Method did not record any Visualizations.", e);
        } catch (IOException e) {
            throw new CompilationException("File error while writing source code into a File", e);

        } catch (ClassNotFoundException e) {
            throw new CompilationException("Class loading failed (classpath/package mismatch)", e);

        } catch (InvocationTargetException e) {
            throw new CompilationException("User code threw exception: " + e.getTargetException(), e);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new CompilationException("Reflection setup failed (constructor/method issue)", e);
        }

    }
}