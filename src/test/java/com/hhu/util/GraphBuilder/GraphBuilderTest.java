package com.hhu.util.graphBuilder;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilderTest {

    @Test
    public void testUseListRenderer() {

        Collection<?> collection = new LinkedList<>();

        Graph graph = GraphBuilder.buildGraph(collection);

        assertEquals(graph.name().toString(), "listGraph");
    }

    @Test
    public void testUseStackRenderer() {
        Collection<?> collection = new Stack<>();

        Graph graph = GraphBuilder.buildGraph(collection);

        assertEquals(graph.name().toString(), "stackGraph");
    }

    @Test
    public void testUseTreeMapRenderer() {
        Map<String, Integer> collection = new TreeMap<>();

        Graph graph = GraphBuilder.buildGraph(collection);

        assertEquals(graph.name().toString(), "treeMapGraph");
    }
    }



