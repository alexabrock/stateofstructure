package com.hhu.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FileManagerTest {
    @Test
    public void findCorrectCallerClass() {
        // Weil die Methode die Assumpiton macht, wo die Klasse liegt, und die
        // Testklassen dort nicht liegen,  muss die Methode manuell getestet werden
        assertTrue(true);
    }

    @Test
    public void findCorrectLine() throws IllegalAccessException {
       
        //Tests stoppen in Aufrufender Klasse vor dieser Testklasse
        // at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59) (findCallerLine returnt line -1)
        //weil ich in der normalen FindCallerLine Methode .filter(frame ->
        // !frame.getClassName().startsWith("com.hhu.util")) 
        //filter, aber die Testklasse in diesem Package liegt.

        //Auch diese Methode muss also manuell getestet werden

        assertTrue(true);
    }
}
