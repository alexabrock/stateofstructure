package com.hhu.datastructuresLegacy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.hhu.legacyDatastructures.VTreeMap;

@Ignore
public class VTreeMapTest {

    private VTreeMap<String, Integer> map;

    @Before
    public void setUp() {
        map = new VTreeMap<>();
    }

    @Test
    public void testPutGetRemoveAndContains() {
        map.put("one", 1);
        map.put("two", 2);

        assertTrue(map.containsKey("one"));
        assertEquals(Integer.valueOf(1), map.get("one"));

        assertEquals(Integer.valueOf(2), map.remove("two"));
        assertNull(map.get("two"));
    }

    @Test
    public void testFirstLastAndKeyOrdering() {
        map.put("b", 2);
        map.put("a", 1);
        map.put("c", 3);

        assertEquals("a", map.firstKey());
        assertEquals("c", map.lastKey());

        assertArrayEquals(new String[] { "a", "b", "c" }, map.keySet().toArray(new String[0]));
    }

    @Test
    public void testFloorCeilingHigherLowerKeys() {
        map.put("a", 1);
        map.put("c", 3);
        map.put("e", 5);

        assertEquals("c", map.ceilingKey("b"));
        assertEquals("a", map.floorKey("b"));
        assertEquals("e", map.higherKey("c"));
        assertEquals("a", map.lowerKey("c"));
    }

    @Test
    public void testSubMapViewReflectsParent() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        NavigableMap<String, Integer> sub = map.subMap("a", true, "c", false);

        assertEquals(2, sub.size());

        sub.put("aa", 11);
        assertTrue(map.containsKey("aa"));

        sub.remove("b");
        assertFalse(map.containsKey("b"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullKeyThrows() {
        map.put(null, 0);
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(map.isEmpty());

        map.put("a", 1);
        map.put("b", 2);

        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
    }

    @Test
    public void testClear() {
        map.put("a", 1);
        map.put("b", 2);

        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void testOverwriteValue() {
        map.put("a", 1);
        map.put("a", 100);

        assertEquals(1, map.size());
        assertEquals(Integer.valueOf(100), map.get("a"));
    }

    @Test
    public void testDescendingMapOrder() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        NavigableMap<String, Integer> desc = map.descendingMap();

        assertArrayEquals(
                new String[] { "c", "b", "a" },
                desc.keySet().toArray(new String[0]));
    }

    @Test
    public void testValuesCollection() {
        map.put("a", 1);
        map.put("b", 2);

        assertTrue(map.values().contains(1));
        assertTrue(map.values().contains(2));
    }

    @Test
    public void testEntrySetIterationOrder() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();

        assertEquals("a", it.next().getKey());
        assertEquals("b", it.next().getKey());
        assertEquals("c", it.next().getKey());
    }

    @Test
    public void testContainsValue() {
        map.put("x", 100);
        map.put("y", 200);

        assertTrue(map.containsValue(100));
        assertFalse(map.containsValue(999));
    }

}