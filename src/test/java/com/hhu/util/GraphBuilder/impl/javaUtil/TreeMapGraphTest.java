package com.hhu.util.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class TreeMapGraphTest {
        private TreeMap<String, Integer> tree;

    @Before
    public void setUp() {
        tree = new TreeMap<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(tree);
    }

    @Test
    public void graphContainsSingleStringElement() {
        tree.put("a", 1);

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("a, 1")));
    }

    @Test
    public void graphContainsMultipleStringElements() {

        tree.put("Lotte", 1);
        tree.put("Dieter", 2);
        tree.put("Alexa", 3);
        tree.put("Holland", 4);
        
        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Lotte, 1")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Dieter, 2")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Alexa, 3")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Holland, 4")));
    }
    

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }
}