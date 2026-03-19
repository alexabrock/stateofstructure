package com.hhu.util.GraphBuilder;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.Test;

import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VStack;
import com.hhu.datastructures.VTreeMap;
import com.hhu.util.graphBuilder.GraphBuilderFactory;
import com.hhu.util.graphBuilder.api.GraphBuilder;

import guru.nidi.graphviz.model.Graph;

public class GraphBuilderTest {

    @Test
    public void testUseVListRenderer() {

        Collection<?> collection = new VLinkedList<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertTrue(graph.name().toString().equals("listGraph"));
    }

    @Test
    public void testUseVStackRenderer() {
        Collection<?> collection = new VStack<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertTrue(graph.name().toString().equals("stackGraph"));
    }

    @Test
    public void testUseVTreeMapRenderer() {
        Map<String, Integer> collection = new VTreeMap<>();

        GraphBuilder builder = GraphBuilderFactory.getBuilder(collection);

        Graph graph = builder.buildGraph(collection);

        assertTrue(graph.name().toString().equals("mapGraph"));
    }
    }



