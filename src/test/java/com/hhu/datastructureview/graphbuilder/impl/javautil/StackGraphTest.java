package com.hhu.datastructureview.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class StackGraphTest {
    private Stack<String> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(stack);
    }

    @Test
    public void graphContainsSingleStringElement() {
        stack.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        stack.add("Lotte");
        stack.add("Dieter");
        stack.add("Alexa");
        stack.add("Holland");

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
