package com.hhu.datastructureview.graphbuilder.impl.progra;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;
import com.hhu.progradatastructures.generic.PrograHashSet;

import guru.nidi.graphviz.model.MutableGraph;

public class PrograHashSetGraphTest {
    private PrograHashSet<String> set;

    @Before
    public void setUp() {
        set = new PrograHashSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(set);
    }

    @Test
    public void graphContainsSingleStringElement() {
        set.insert("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        set.insert("Lotte");
        set.insert("Dieter");
        set.insert("Alexa");
        set.insert("Holland");

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
