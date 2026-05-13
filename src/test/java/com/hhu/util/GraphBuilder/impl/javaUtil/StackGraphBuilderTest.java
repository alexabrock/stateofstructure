package com.hhu.util.graphBuilder.impl.javaUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class StackGraphBuilderTest {
    private Stack<String> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(stack);
    }

    @Test
    @Ignore
    public void graphContainsSingleStringElement() {
        stack.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }

    @Test
    @Ignore
    public void graphContainsMultipleStringElements() {
        stack.add("Lotte");
        stack.add("Dieter");
        stack.add("Alexa");
        stack.add("Holland");

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Lotte")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Dieter")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Alexa")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }

}
