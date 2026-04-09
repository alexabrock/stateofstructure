package com.hhu.util.GraphBuilder.impl.javaUtil;

import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.hhu.util.datastructureView.GraphBuilderFactory;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class TreeMapGraphBuilderTest {
        private TreeMap<String, Integer> tree;

    @Before
    public void setUp() {
        tree = new TreeMap<>();
    }

    private boolean hasEdge(MutableGraph graph, String from, String to) {
        return graph.nodes().stream()
                .filter(n -> n.name().toString().equals(from))
                .findFirst()
                .orElseThrow()
                .links().stream()
                .anyMatch(link -> link.to().name().toString().equals(to));
    }

    @Test
    public void graphContainsSingleStringElement() {
        tree.put("a",1);

        MutableGraph graph = (MutableGraph) GraphBuilderFactory
                .getBuilder(tree)
                .buildGraph(tree);

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        tree.put("Lotte",  1);
        tree.put("Dieter",2);
        tree.put("Alexa",3);
        tree.put("Holland",4);

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(tree).buildGraph(tree);

        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Lotte")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Dieter")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Alexa")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(tree).buildGraph(tree);

        assertTrue(graph.nodes().isEmpty());
    }

    @Test
    public void graphHasCorrectEdge() {
        tree.put("Lotte", 1);
        tree.put("Dieter", 2);
        tree.put("Alexa", 3);
        tree.put("Holland", 4);

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(tree).buildGraph(tree);

        assertTrue(hasEdge(graph, "Alexa", "3"));
        
    }


}
