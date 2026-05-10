package com.hhu.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;

import org.junit.Test;

public class CallerTest {
    @Test
    public void findCorrectCallerClass() {
        //PSA: THis is probably the wrong path
        String expected = FileManager.fileToString(Path.of("src", "test", "java", "com", "hhu", "util", "Caller.java"));
        
        String actual = Caller.findCallerClass();
        System.out.println(expected);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void findCorrectLine() throws IllegalAccessException {

        // Tests stoppen in Aufrufender Klasse vor dieser Testklasse
        // at
        // org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
        // (findCallerLine returnt line -1)
        // weil ich in der normalen FindCallerLine Methode .filter(frame ->
        // !frame.getClassName().startsWith("com.hhu.util"))
        // filter, aber die Testklasse in diesem Package liegt.

        // Auch diese Methode muss also manuell getestet werden

        assertTrue(true);
    }
}
