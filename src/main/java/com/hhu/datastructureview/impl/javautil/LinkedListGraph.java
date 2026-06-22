package com.hhu.datastructureview.impl.javautil;

import static com.hhu.datastructureview.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.util.List;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/** Builds a GraphViz Graph for LinkedLists */
public class LinkedListGraph {
    public static Graph buildGraph(List<?> list) {
        if (list.isEmpty()) {
            return graph("listGraph");
        }

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Shape.RECTANGLE);

        Node firstNode = getNode(list.getFirst().toString());
        Node previousNode = firstNode;
        g = g.with(firstNode);

        for (Object element : list.subList(1, list.size())) {
            Node currentNode = getNode(element.toString());
            g = g.with(currentNode,
                    // next pointer
                    previousNode.link(currentNode),
                    // prev pointer
                    currentNode.link(previousNode));
            previousNode = currentNode;
        }

        g = g.with(
                node("head").with(Shape.PLAIN).link(to(firstNode)),
                // previousNode is now the last node in the List
                node("tail").with(Shape.PLAIN).link(to(previousNode)));
        return g;
    }

    private LinkedListGraph() {
        /* This utility class should not be instantiated */
    }
}
