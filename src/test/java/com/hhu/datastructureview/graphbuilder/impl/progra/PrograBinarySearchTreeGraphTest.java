package com.hhu.datastructureview.graphbuilder.impl.progra;

import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import com.hhu.datastructureview.GraphBuilder;
import com.hhu.progradatastructures.generic.PrograBinarySearchTree;
import com.hhu.progradatastructures.generic.PrograHashSet;
import com.hhu.datastructureview.graphbuilder.impl.LabelChecker;

import guru.nidi.graphviz.model.MutableGraph;

public class PrograBinarySearchTreeGraphTest {
    private PrograBinarySearchTree<String> tree;

    @Before
    public void setUp() {
        tree = new PrograBinarySearchTree<>();
    }

    private MutableGraph createMutableGraph() {
        return (MutableGraph) GraphBuilder.buildGraph(tree);
    }

    @Test
    public void graphContainsSingleStringElement() {
        tree.insert("a");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "a");
    }

    @Test
    public void graphContainsMultipleStringElements() {
        tree.insert("Lotte");
        tree.insert("Dieter");
        tree.insert("Alexa");
        tree.insert("Holland");

        MutableGraph graph = createMutableGraph();

        LabelChecker.checkContainsLabels(graph.nodes(), "Lotte", "Dieter", "Alexa", "Holland");
    }

    // graphContainsNestedDataStructure() macht hier keinen Sinn, weil
    // datenstrukturen nicht miteinander comparable sind, PrograLinkedList aber nur
    // add(<T extends Comparable<T>> t) kann

    @Test
    public void graphEmpty() {

        MutableGraph graph = createMutableGraph();

        assertTrue(graph.nodes().isEmpty());
    }

}
