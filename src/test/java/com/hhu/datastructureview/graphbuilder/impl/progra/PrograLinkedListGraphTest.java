package com.hhu.datastructureview.graphbuilder.impl.progra;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;
import com.hhu.datastructureview.graphbuilder.impl.LabelChecker;
import com.hhu.progradatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.model.MutableGraph;

public class PrograLinkedListGraphTest {
    private PrograLinkedList<String> list;

    @Before
    public void setUp() {
        list = new PrograLinkedList<>();
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

    // graphContainsNestedDataStructure() macht hier keinen Sinn, weil datenstrukturen nicht miteinander comparable sind, PrograLinkedList aber nur add(<T extends Comparable<T>> t) kann
  
    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }

}
