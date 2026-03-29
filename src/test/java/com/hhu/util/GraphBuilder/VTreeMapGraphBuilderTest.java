package com.hhu.util.GraphBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VTreeMap;
import com.hhu.util.datastructureView.GraphBuilderFactory;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class VTreeMapGraphBuilderTest {

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
        VTreeMap<String, Integer> tree = new VTreeMap<>();
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
        VTreeMap<String, Integer> tree = new VTreeMap<>();
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
        VTreeMap<String, Integer> tree = new VTreeMap<>();

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(tree).buildGraph(tree);

        assertTrue(graph.nodes().isEmpty());
    }

    @Test
    public void graphHasCorrectEdge() {
        VTreeMap<String, Integer> tree = new VTreeMap<>();
        tree.put("Lotte", 1);
        tree.put("Dieter", 2);
        tree.put("Alexa", 3);
        tree.put("Holland", 4);

        MutableGraph graph = (MutableGraph) GraphBuilderFactory.getBuilder(tree).buildGraph(tree);

        assertTrue(hasEdge(graph, "Alexa", "3"));
        
    }


}
