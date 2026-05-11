package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.util.LinkedHashSet;

import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for LinkedHashSets */
public class LinkedHashSetGraphBuilder {

    // Not using NodeBuilder, since Nodes shouldn't enter the HashSet twice.
    public static Graph buildGraph(LinkedHashSet<?> set) {

        Graph g = graph("linkedHashSetGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        // Graph with just the elements of the HashSet, to get them to be next to each
        // other
        Graph elements = graph("cluster_elements").directed()
                .graphAttr().with(
                        attr("color", "white"),
                        attr("penwidth", "2"),
                        Rank.inSubgraph(RankType.SAME))
                .nodeAttr().with(Shape.RECTANGLE);

        // Not visualizing the HashTable Slots in the DataStructure View, since that
        // info is normally hidden from the user

        // Subgraph for just the hashTable Node, to set it above all the other Nodes

        Object prev = null;

        for (Object current : set) {
            Node currentNode = node(current.toString());
            if (prev == null) {
                elements = elements.with(
                        node("head").link(
                                to(currentNode)));
            } else {
                String prevNodeName = prev.toString();
                Node prevNode = node(prevNodeName);

                // before pointer
                elements = elements.with(
                        prevNode.link(
                                to(currentNode)));
                // after pointer
                elements = elements.with(
                        currentNode.link(
                                to(prevNode)));
            }
            prev = current;
        }
        // After the loop, 'prev' is the last element in the set
        if (prev != null) {
            elements = elements.with(
                    node("tail").link(
                            to(node(prev.toString()))));
        }
        
        g = g.with(elements);
        return g;
    }
}