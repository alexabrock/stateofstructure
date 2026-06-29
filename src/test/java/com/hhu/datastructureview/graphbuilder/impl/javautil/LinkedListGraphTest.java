package com.hhu.datastructureview.graphbuilder.impl.javautil;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;

import com.hhu.datastructureview.graphbuilder.impl.LabelChecker;

//Mutable graph, weil man so schön an die einzelnen Nodes zum testen kommt
import guru.nidi.graphviz.model.MutableGraph;

public class LinkedListGraphTest {

    private LinkedList<String> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(list);
    }

    @Test
    public void graphContainsSingleStringElement() {
        list.add("a");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "a");
    }

    @Test
    public void graphContainsMultipleStringElements() {
        list.add("Lotte");
        list.add("Dieter");
        list.add("Alexa");
        list.add("Holland");

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

        LinkedList<TreeSet<String>> nested = new LinkedList<>();
        nested.add(set);

        MutableGraph graph = (MutableGraph) GraphBuilder.buildGraph(nested);

        LabelChecker.checkContainsLabels(graph.nodes(), set.toString());
    }

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }
}
