package com.hhu.datastructures;


import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class VLinkedListListTests {

    private VLinkedList<String> list;

    @Before
    public void setUp() {
        list = new VLinkedList<>();
    }

    @Test
    public void testAddGetSizeAndBounds() {
        assertEquals(0, list.size());
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals(3, list.size());

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));

        try {
            list.get(3);
            fail("IndexOutOfBoundsException erwartet");
        } catch (IndexOutOfBoundsException e) {
            /* ok */ }

        try {
            list.add(5, "X");
            fail("IndexOutOfBoundsException erwartet");
        } catch (IndexOutOfBoundsException e) {
            /* ok */ }
    }

    @Test
    public void testAddAtIndexAndSet() {
        list.add("A");
        list.add("C");
        list.add(1, "B"); 
        assertEquals("B", list.get(1));
        assertEquals(3, list.size());

        String old = list.set(1, "B2");
        assertEquals("B", old);
        assertEquals("B2", list.get(1));
    }

    @Test
    public void testRemoveByIndexAndObject() {
        list.add("A");
        list.add("B");
        list.add("C");

        String r = list.remove(1);
        assertEquals("B", r);
        assertEquals(2, list.size());

        boolean b = list.remove("C");
        assertTrue(b);
        assertEquals(1, list.size());

        boolean notFound = list.remove("Z");
        assertFalse(notFound);
    }

    @Test
    public void testIndexOfAndLastIndexOfWithNulls() {
        list.add(null);
        list.add("A");
        list.add(null);
        list.add("A");

        assertEquals(0, list.indexOf(null));
        assertEquals(2, list.lastIndexOf(null));
        assertEquals(1, list.indexOf("A"));
        assertEquals(3, list.lastIndexOf("A"));
    }

    @Test
    public void testClearAndGetFirstLast() {
        list.add("A");
        list.add("B");
        assertEquals("A", list.getFirst());
        assertEquals("B", list.getLast());

        list.clear();
        assertEquals(0, list.size());
        try {
            list.getFirst();
            fail("NoSuchElementException erwartet");
        } catch (NoSuchElementException e) {
            /* ok */ }
    }

    @Test
    public void testToArrayAndToArrayWithType() {
        list.add("A");
        list.add("B");
        Object[] arr = list.toArray();
        assertArrayEquals(new Object[] { "A", "B" }, arr);

        String[] sarr = list.toArray(new String[0]);
        assertArrayEquals(new String[] { "A", "B" }, sarr);

        String[] bigger = new String[5];
        bigger[0] = "foo";
        String[] res = list.toArray(bigger);
        assertEquals("A", res[0]);
        assertEquals("B", res[1]);
        assertNull(res[2]); // rest null according to contract
    }

}