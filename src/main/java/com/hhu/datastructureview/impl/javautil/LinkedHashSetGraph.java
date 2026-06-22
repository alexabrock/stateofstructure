package com.hhu.datastructureview.impl.javautil;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.util.LinkedHashSet;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/** Builds a GraphViz Graph for LinkedHashSets */
public class LinkedHashSetGraph {

    // Not using NodeBuilder, since Nodes shouldn't enter the HashSet twice.
    public static Graph buildGraph(LinkedHashSet<?> set) {
        if (set.isEmpty()) {
            return graph("linkedHashSetGraph");
        }

        Graph g = graph("linkedHashSetGraph").directed()
                .graphAttr().with(
                        attr("layout", "fdp"),
                        attr("K", "0.7"))
                .nodeAttr().with(Shape.RECTANGLE);

        // Graph with just the elements of the HashSet, to get them to be next to each
        // other
        Graph elements = graph("cluster_elements").directed()

                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .graphAttr().with(
                        attr("color", "white"),
                        attr("penwidth", "2"));

        Node head = node("head").with(Shape.PLAIN);
        Node tail = node("tail").with(Shape.PLAIN);

        // Not visualizing the HashTable Slots in the DataStructure View, since that
        // info is normally hidden from the user

        // Subgraph for just the hashTable Node, to set it above all the other Nodes

        Object prev = null;

        for (Object current : set) {
            Node currentNode = node(current.toString());
            if (prev == null) {
                elements = elements.with(
                        head.link(to(currentNode)));
            } else {
                String prevNodeName = prev.toString();
                Node prevNode = node(prevNodeName);

                // before pointer
                elements = elements.with(
                        prevNode.link(to(currentNode)));
                // after pointer
                elements = elements.with(
                        currentNode.link(to(prevNode)));
            }
            prev = current;
        }
        // After the loop, 'prev' is the last element in the set
        if (prev != null) {
            elements = elements.with(
                    tail.link(to(node(prev.toString()))));
        }

        g = g.with(elements);
        return g;
    }

    private LinkedHashSetGraph() {
        /* This utility class should not be instantiated */
    }
}