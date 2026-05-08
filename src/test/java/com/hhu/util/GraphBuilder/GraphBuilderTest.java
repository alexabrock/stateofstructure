package com.hhu.util.graphBuilder;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;
import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;
import com.hhu.datastructures.prograDatastructures.generic.PrograHashSet;
import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilderTest {

    /* Java.util */
    @Test
    public void testUseListRenderer() {
        Collection<?> collection = new LinkedList<>();
        confirmGraphName(collection, "listGraph");
    }

    @Test
    public void testUseStackRenderer() {
        Collection<?> collection = new Stack<>();
        confirmGraphName(collection, "stackGraph");
    }

    @Test
    public void testUseTreeMapRenderer() {
        Map<String, Integer> collection = new TreeMap<>();
        confirmGraphName(collection, "treeMapGraph");
    }

    @Test
    public void testUseArrayListRenderer() {
        ArrayList<?> collection = new ArrayList<>();
        confirmGraphName(collection, "arrayListGraph");
    }

    @Test
    public void testUseLinkedHashSetRenderer() {
        LinkedHashSet<?> collection = new LinkedHashSet<>();
        confirmGraphName(collection, "linkedHashSetGraph");
    }

    @Test
    public void testUseTreeSetRenderer() {
        TreeSet<?> collection = new TreeSet<>();
        confirmGraphName(collection, "treeSetGraph");
    }
    
    /* Progra */

    @Test
    public void testUsePrograLinkedListRenderer() {
        PrograLinkedList<?> collection = new PrograLinkedList<>();
        confirmGraphName(collection, "prograLinkedListGraph");
    }

    @Test
    public void testUseBinarySearchTreeRenderer() {
        BinarySearchTree<?> collection = new BinarySearchTree<>();
        confirmGraphName(collection, "binarySearchTreeGraph");
    }

    @Test
    public void testUseHashSetRenderer() {
        PrograHashSet<?> collection = new PrograHashSet<>();
        confirmGraphName(collection, "hashSetGraph");
    }

    private void confirmGraphName(Object collection, String expectedName) {
        Graph graph = GraphBuilder.buildGraph(collection);
        assertEquals(expectedName, graph.name().toString());
    }
    
    }



