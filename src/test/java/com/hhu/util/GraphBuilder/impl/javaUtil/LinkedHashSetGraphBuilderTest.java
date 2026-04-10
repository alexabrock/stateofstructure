package com.hhu.util.GraphBuilder.impl.javaUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureView.GraphBuilderFactory;

import guru.nidi.graphviz.model.MutableGraph;

public class LinkedHashSetGraphBuilderTest {
    
    private LinkedHashSet<String> hashSet;

    @Before
    public void setUp() {
        hashSet = new LinkedHashSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilderFactory.getBuilder(hashSet).buildGraph(hashSet);
    }

    @Test
    public void graphContainsSingleStringElement() {
        hashSet.add("a");

        MutableGraph graph = createMutableGraph();

        assertTrue(
                graph.nodes().stream()
                        .anyMatch(n -> n.name().toString().equals("a")));
    }

    @Test
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
        assertTrue(graph.nodes().size()==1);
    }

    @Test
    public void correctAmountOfNodes() {
        hashSet.add("Lotte");
        hashSet.add("Dieter");
        hashSet.add("Alexa");
        hashSet.add("Holland");
        
        MutableGraph graph = createMutableGraph();
        System.out.println(graph.nodes());

        //all nodes come with 23pointers (before & afet & hashtable link), except for the first and last node, which have one pointer each
        assertEquals(hashSet.size()*3-2 , graph.edges().size());
    }
}
