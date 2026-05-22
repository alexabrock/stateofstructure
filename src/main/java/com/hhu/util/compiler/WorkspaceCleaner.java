package com.hhu.util.compiler;

import java.nio.file.Files;
import java.nio.file.Path;

public class WorkspaceCleaner {
    /*
     * replaces the .java File with the Default file and
     * deletes the .class file in util/compiler/compiledClasses
     */
    private static final Path COMPILED_CLASSES_DIR = Path.of(
            "src",
            "main",
            "java");
    private static final Path DEFAULT_APP = Path.of(
            "src",
            "main",
            "resources",
            "StateOfStructureDefault.txt");

    public void registerShutdownCleanup() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::cleanup));
    }

    private void cleanup() {
        try {
            Files.deleteIfExists(COMPILED_CLASSES_DIR.resolve("StateOfStructure.class"));
            restoreDefaultClass();
        } catch (Exception ignored) {
        }
    }

    private void restoreDefaultClass() throws java.io.IOException {
        if (Files.exists(DEFAULT_APP)) {
            Files.writeString(
                    COMPILED_CLASSES_DIR.resolve("StateOfStructure.java"),
                    Files.readString(DEFAULT_APP));
        }
    }
}
