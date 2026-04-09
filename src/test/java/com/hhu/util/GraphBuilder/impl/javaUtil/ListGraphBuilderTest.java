package com.hhu.util.GraphBuilder.impl.javaUtil;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.util.datastructureView.GraphBuilderFactory;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class ListGraphBuilderTest {

    private LinkedList<String> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void graphContainsSingleStringElement() {
        list.add("a");

        MutableGraph graph = (MutableGraph) GraphBuilderFactory
                .getBuilder(list)
                .buildGraph(list);

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

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(list).buildGraph(list);

        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Lotte")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Dieter")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Alexa")));
        assertTrue(graph.nodes().stream().anyMatch(n -> n.name().toString().equals("Holland")));
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(list).buildGraph(list);

        assertTrue(graph.nodes().isEmpty());
    }
}

