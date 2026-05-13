package com.hhu.util.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class LinkedListGraphTest {

    private LinkedList<String> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(list);
    }

    @Test
    public void graphContainsSingleStringElement() {
        list.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        list.add("Lotte");
        list.add("Dieter");
        list.add("Alexa");
        list.add("Holland");

        MutableGraph graph = createMutableGraph();
        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Lotte")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Dieter")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Alexa")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }
}
