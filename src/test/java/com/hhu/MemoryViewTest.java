package com.hhu;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;
import com.hhu.progradatastructures.generic.PrograBinarySearchTree;
import com.hhu.progradatastructures.generic.PrograHashSet;
import com.hhu.progradatastructures.generic.PrograLinkedList;

public class MemoryViewTest {
    //Test, if every Memory view works. Not testing in detail, since using
    //an external libary (LJV), but since i merge custom 
@Test
    public void shouldBuildGraphForAllSupportedDataStructures() {

        assertNotNull(GraphBuilder.buildGraph(new Stack<>()));

        assertNotNull(GraphBuilder.buildGraph(new LinkedList<>()));

        assertNotNull(GraphBuilder.buildGraph(new ArrayList<>()));

        assertNotNull(GraphBuilder.buildGraph(new HashSet<>()));

        assertNotNull(GraphBuilder.buildGraph(new LinkedHashSet<>()));

        assertNotNull(GraphBuilder.buildGraph(new TreeSet<>()));

        assertNotNull(GraphBuilder.buildGraph(new TreeMap<>()));

        assertNotNull(GraphBuilder.buildGraph(new PrograLinkedList<>()));

        assertNotNull(GraphBuilder.buildGraph(new PrograHashSet<>()));

        assertNotNull(GraphBuilder.buildGraph(new PrograBinarySearchTree<>()));
    }
}
