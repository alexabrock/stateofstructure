package com.hhu.util.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import com.hhu.datastructureView.GraphBuilder;

import guru.nidi.graphviz.model.MutableGraph;

public class TreeSetGraphBuilderTest {
    private TreeSet<String> set;

    @Before
    public void setUp() {
        set = new TreeSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(set);
    }

    @Test
    public void graphContainsSingleStringElement() {
        set.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {

        set.add("Lotte");
        set.add("Dieter");
        set.add("Alexa");
        set.add("Holland");
        
        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Lotte")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Dieter")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Alexa")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }

    //There are many merged nodes & edges not visible in the final Graph, so testing for Nodes isn't advisable


}
