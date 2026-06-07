package com.hhu.datastructureView.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;

import guru.nidi.graphviz.model.MutableGraph;

public class HashSetGraphTest {
    private HashSet<String> hashSet;

    @Before
    public void setUp() {
        hashSet = new HashSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(hashSet);
    }

    @Test
    public void graphContainsSingleStringElement() {
        hashSet.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        hashSet.add("Lotte");
        hashSet.add("Dieter");
        hashSet.add("Alexa");
        hashSet.add("Holland");

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
        //only hashtable node
        assertTrue(graph.nodes().isEmpty());
    }
}
