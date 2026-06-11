package com.hhu.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DrawCallsTest {
    private DrawCalls drawCalls;
    private ArrayList<Integer> mockCollection;

    @Before
    public void setUp() {
        mockCollection = new ArrayList<>();
        drawCalls = new DrawCalls(mockCollection);
    }

    @Test
    public void correctIfNoRecord() {
        assertFalse(drawCalls.hasNextStep());
        assertFalse(drawCalls.hasPrevStep());
    }
    
    @Test
    public void recordWorks() {
        drawCalls.record();

        assertTrue(drawCalls.hasNextStep());
        assertNotNull(drawCalls.nextStep());
    }

    @Test
    public void correctIfOneRecord() {
        drawCalls.record();

        assertTrue(drawCalls.hasNextStep());
        assertFalse(drawCalls.hasPrevStep());
    }
}
