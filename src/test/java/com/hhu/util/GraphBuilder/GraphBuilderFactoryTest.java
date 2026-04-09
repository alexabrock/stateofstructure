package com.hhu.util.GraphBuilder;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import org.junit.Test;

import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.util.datastructureView.GraphBuilderFactory;
import com.hhu.util.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilderFactoryTest {

    @Test
    public void testUseListRenderer() {

        Collection<?> collection = new LinkedList<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertEquals(graph.name().toString(), "listGraph");
    }

    @Test
    public void testUseStackRenderer() {
        Collection<?> collection = new Stack<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertEquals(graph.name().toString(), "stackGraph");
    }

    @Test
    public void testUseTreeMapRenderer() {
        Map<String, Integer> collection = new TreeMap<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertEquals(graph.name().toString(), "treeMapGraph");
    }
    }



