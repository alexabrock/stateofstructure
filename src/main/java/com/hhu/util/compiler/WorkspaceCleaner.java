package com.hhu.util.compiler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class WorkspaceCleaner {
    /**
     * Deletes the temporary workspace used for user-provided source files and
     * compiled classes.
     */
    public void registerShutdownCleanup() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::cleanup));
    }

    private void cleanup() {
        Path workspaceDir = Compiler.getWorkspaceDir();
        if (!Files.exists(workspaceDir))
            return;

        try (Stream<Path> paths = Files.walk(workspaceDir)) { // paths is closed automatically after try/catch
            paths.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (Exception _) {
                            //ignored, application is closed now
                        }
                    });
        } catch (Exception _) {
            //ignored, application is closed now
        }

    }
}
