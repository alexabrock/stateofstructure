package com.hhu.datastructureview.impl.progra;

import static com.hhu.datastructureview.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import com.hhu.progradatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/** Builds a GraphViz Graph for PrograLinkedLists */
public class PrograLinkedListGraph {

    public static Graph buildGraph(PrograLinkedList<?> list) {
        if (list.length() == 0) {
            return graph("prograLinkedListGraph");
        }

        Graph g = graph("prograLinkedListGraph").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Shape.RECTANGLE);

        Node firstNode = getNode(list.get(0).toString());
        Node previousNode = firstNode;
        g = g.with(firstNode);

        for (int i = 1; i < list.length(); i++) {
            Node currentNode = getNode(list.get(i).toString());
            // next pointer
            g = g.with(currentNode, previousNode.link(currentNode));
            previousNode = currentNode;
        }

        g = g.with(node("head").with(Shape.PLAIN).link(to(firstNode)));
        return g;
    }
}
