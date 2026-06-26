package com.hhu.datastructureview.graphbuilder.impl.javautil;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;

import com.hhu.datastructureview.graphbuilder.impl.LabelChecker;

import guru.nidi.graphviz.model.MutableGraph;

public class HashSetGraphTest {
    private HashSet<String> hashSet;

    @Before
    public void setUp() {
        hashSet = new HashSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(hashSet);
    }

    @Test
    public void graphContainsSingleStringElement() {
        hashSet.add("a");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "a");
    }

    @Test
    public void graphContainsMultipleStringElements() {
        hashSet.add("Lotte");
        hashSet.add("Dieter");
        hashSet.add("Alexa");
        hashSet.add("Holland");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "Lotte", "Dieter", "Alexa", "Holland");
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();
        //only hashtable node
        assertTrue(graph.nodes().isEmpty());
    }
}
