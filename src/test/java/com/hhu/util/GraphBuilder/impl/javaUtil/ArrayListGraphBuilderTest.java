package com.hhu.util.GraphBuilder.impl.javaUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilderFactory;

import guru.nidi.graphviz.model.MutableGraph;

public class ArrayListGraphBuilderTest {
     private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }
    
    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilderFactory.getBuilder(list).buildGraph(list);
    }

    @Test
    public void graphContainsSingleStringElement() {
        list.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }
    
    @Test
    public void graphContainsMultipleStringElements() {
        list.add("Lotte");
        list.add("Dieter");
        list.add("Alexa");
        list.add("Holland");

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

    @Test
    public void correctAmountOfNodes() {
        list.add("Lotte");
        list.add("Dieter");
        list.add("Alexa");
        list.add("Holland");

        MutableGraph graph = createMutableGraph();

        //no pointers to account for
        assertEquals(list.size(), graph.nodes().size());
    }
}
