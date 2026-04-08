package com.hhu.util.GraphBuilder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hhu.legacyDatastructures.VStack;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.util.datastructureView.GraphBuilderFactory;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class VStackGraphBuilderTest {

    @Test
    public void graphContainsSingleStringElement() {
        VStack<String> stack = new VStack<>();
        stack.add("a");

        MutableGraph graph = (MutableGraph) GraphBuilderFactory
                .getBuilder(stack)
                .buildGraph(stack);

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        VStack<String> stack = new VStack<>();
        stack.add("Lotte");
        stack.add("Dieter");
        stack.add("Alexa");
        stack.add("Holland");

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(stack).buildGraph(stack);

        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Lotte")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Dieter")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Alexa")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {
        VStack<String> stack = new VStack<>();

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(stack).buildGraph(stack);

        assertTrue(graph.nodes().isEmpty());
    }
    
}
