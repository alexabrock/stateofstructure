package com.hhu.datastructureview.graphbuilder.impl.progra;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;
import com.hhu.progradatastructures.generic.PrograHashSet;

import com.hhu.datastructureview.graphbuilder.impl.LabelChecker;

import guru.nidi.graphviz.model.MutableGraph;

public class PrograHashSetGraphTest {
    private PrograHashSet<String> set;

    @Before
    public void setUp() {
        set = new PrograHashSet<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(set);
    }

    @Test
    public void graphContainsSingleStringElement() {
        set.insert("a");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "a");
    }

    @Test
    public void graphContainsMultipleStringElements() {
        set.insert("Lotte");
        set.insert("Dieter");
        set.insert("Alexa");
        set.insert("Holland");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "Lotte", "Dieter", "Alexa", "Holland");
    }

    @Test
    public void graphContainsNestedDataStructure() {
        TreeSet<String> set = new TreeSet<>();
        set.add("Lotte");
        set.add("Dieter");
        set.add("Alexa");
        set.add("Holland");

        PrograHashSet<TreeSet<String>> nested = new PrograHashSet<>();
        nested.insert(set);

        MutableGraph graph = (MutableGraph) GraphBuilder.buildGraph(nested);

        LabelChecker.checkContainsLabels(graph.nodes(), set.toString());
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }

}
