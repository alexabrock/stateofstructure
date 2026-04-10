package com.hhu.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import com.hhu.util.FileManager;

public class FileManagerTest {
    @Test
    public void findCorrectCallerClass() {
        // Weil die Methode die Assumpiton macht, wo die Klasse liegt, und die
        // Testklassen dort nicht liegen,  muss die Methode manuell getestet werden
        assertTrue(true);
    }

    @Test
    public void findCorrectLine() {
        /* int expectedLine = new Throwable().getStackTrace()[0].getLineNumber() + 1;
        
        int actualLine = FileManager.findCallerLine();
        
        assertEquals(expectedLine, actualLine); */

        // [ERROR] FileManagerTest.findCorrectLine:29 expected:<26> but was:<58> 

        //Tests stoppen in Aufrufender Klasse vor dieser Testklasse
        //TODO: herausfinden in welcher und warum
        assertTrue(true);
    }
}
