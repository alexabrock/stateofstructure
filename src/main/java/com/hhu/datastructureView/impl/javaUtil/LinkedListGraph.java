package com.hhu.datastructureView.impl.javaUtil;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.nodeAttrs;
import static guru.nidi.graphviz.model.Factory.to;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for LinkedLists*/
public class LinkedListGraph {
    public static Graph buildGraph(List<?> list) {
        if (list.isEmpty()) {
            return graph("listGraph");
        }

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Shape.RECTANGLE);

        //cashing Nodes for linkage, sind ID broke normal factory pattern
        Map<Integer, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            String nodeName = list.get(i).toString();
            Node currentNode = getNode(nodeName);
            nodeMap.put(i, currentNode);
            g = g.with(currentNode);

            if (i > 0) {
                Node prevNode = nodeMap.get(i - 1);
                // next pointer
                g = g.with(prevNode.link(currentNode));
                // prev pointer
                g = g.with(currentNode.link(prevNode));
            }
        }
        Node head = node("head").with(Shape.PLAIN).link(to(nodeMap.get(0)));
        Node tail = node("tail").with(Shape.PLAIN).link(to(nodeMap.get(list.size()-1)));

        g = g.with(head, tail);
        return g;
    }
}
