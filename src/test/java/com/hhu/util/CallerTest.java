package com.hhu.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

public class CallerTest {
    @Test 
    public void correctCallerLine() {
        //starts counting at 0
        int expected = StackWalker.getInstance().walk(frames -> frames.findFirst().get().getLineNumber());
        int actual = Caller.findCallerLine();
        //starts counting at 1

        assertEquals(expected, actual);
    }

    @Test
    public void correctCallerClass () throws IOException {
            String path = "src/test/java/" + this.getClass().getName().replace(".", "/") + ".java";
            String expected = Files.readString(Path.of(path));

            String actual = Caller.findCallerClass();

            assertEquals(expected, actual);
        
    }
}
