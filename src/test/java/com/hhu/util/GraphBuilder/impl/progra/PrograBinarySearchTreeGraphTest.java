package com.hhu.util.graphBuilder.impl.progra;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilder;
import com.hhu.prograDatastructures.generic.BinarySearchTree;

import guru.nidi.graphviz.model.MutableGraph;

public class PrograBinarySearchTreeGraphTest {
    private BinarySearchTree<String> tree;

    @Before
    public void setUp() {
        tree = new BinarySearchTree<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(tree);
    }

    @Test
    public void graphContainsSingleStringElement() {
        tree.insert("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.get("label") != null && n.get("label").toString().equals("a")));
    }

    @Test
    public void graphContainsMultipleStringElements() {
        tree.insert("Lotte");
        tree.insert("Dieter");
        tree.insert("Alexa");
        tree.insert("Holland");

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
