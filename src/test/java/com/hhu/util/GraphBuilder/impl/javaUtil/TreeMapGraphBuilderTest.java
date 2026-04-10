package com.hhu.util.GraphBuilder.impl.javaUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilderFactory;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class TreeMapGraphBuilderTest {
        private TreeMap<String, Integer> tree;

    @Before
    public void setUp() {
        tree = new TreeMap<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilderFactory.getBuilder(tree).buildGraph(tree);
    }

    @Test
    public void graphContainsSingleStringElement() {
        tree.put("a", 1);

        MutableGraph graph = createMutableGraph();

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a, 1")));
    }

    @Test
    public void graphContainsMultipleStringElements() {

        tree.put("Lotte", 1);
        tree.put("Dieter", 2);
        tree.put("Alexa", 3);
        tree.put("Holland", 4);
        
        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Lotte, 1")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Dieter, 2")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Alexa, 3")));

        assertTrue(graph.nodes().stream()
                .anyMatch(n -> n.name().toString().equals("Holland, 4")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }

    @Test
    //There are many merged noded not visible in the final Graph, so testing for Nodes isn't advisable
    public void correctAmountOfEdges() {
        tree.put("Lotte", 1);
        tree.put("Dieter", 2);
        tree.put("Alexa", 3);
        tree.put("Holland", 4);
        
        MutableGraph graph = createMutableGraph();
        
        //all but the root node are connected to the tree by one edge
        assertEquals(3, graph.edges().size());
    }


}