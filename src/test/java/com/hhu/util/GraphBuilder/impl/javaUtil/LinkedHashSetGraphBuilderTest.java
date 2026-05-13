package com.hhu.util.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;

import guru.nidi.graphviz.model.MutableGraph;

public class LinkedHashSetGraphBuilderTest {
    
    private LinkedHashSet<String> hashSet;

    @Before
    public void setUp() {
        hashSet = new LinkedHashSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(hashSet);
    }

    @Test
    @Ignore
    public void graphContainsSingleStringElement() {
        hashSet.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }

    @Test
    @Ignore
    public void graphContainsMultipleStringElements() {
        hashSet.add("Lotte");
        hashSet.add("Dieter");
        hashSet.add("Alexa");
        hashSet.add("Holland");

        MutableGraph graph = createMutableGraph();
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Lotte")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Dieter")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Alexa")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();
        //only hashtable node
        assertTrue(graph.nodes().isEmpty());
    }
}
