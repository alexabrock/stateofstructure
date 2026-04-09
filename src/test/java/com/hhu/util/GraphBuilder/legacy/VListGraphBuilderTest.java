package com.hhu.util.GraphBuilder.legacy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.legacyDatastructures.VTreeMap;
import com.hhu.util.datastructureView.GraphBuilderFactory;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;
import org.junit.Ignore;

@Ignore
public class VListGraphBuilderTest {

    @Test
    public void graphContainsSingleStringElement() {
        VLinkedList<String> list = new VLinkedList<>();
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
        VLinkedList<String> list = new VLinkedList<>();
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
        VLinkedList<String> list = new VLinkedList<>();

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(list).buildGraph(list);

        assertTrue(graph.nodes().isEmpty());
    }
}

