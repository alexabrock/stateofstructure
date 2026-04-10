package com.hhu.datastructuresLegacy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.hhu.datastructures.legacyDatastructures.VStack;

@Ignore
public class VStackTest {

    private VStack<String> stack;

    @Before
    public void setUp() {
        stack = new VStack<>();
    }

    @Test
    public void testPushPopPeek() {
        assertTrue(stack.isEmpty());
        stack.push("one");
        stack.push("two");
        assertEquals("two", stack.peek());
        assertEquals("two", stack.pop());
        assertEquals("one", stack.peek());
        assertEquals("one", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test(expected = EmptyStackException.class)
    public void testPopOnEmptyThrows() {
        stack.pop();
    }

    @Test
    public void testSearchAndOrder() {
        stack.push("a");
        stack.push("b");
        stack.push("c"); // top = c
        assertEquals(1, stack.search("c"));
        assertEquals(2, stack.search("b"));
        assertEquals(-1, stack.search("z"));
    }

    @Test
    public void testClearAndEmpty() {
        stack.push("x");
        stack.push("y");
        stack.clear();
        assertTrue(stack.empty());
    }
}