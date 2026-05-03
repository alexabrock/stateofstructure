package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import static com.hhu.datastructureView.NodeBuilder.getNode;

/* Builds a GraphViz Graph for LinkedLists*/
public class LinkedListGraphBuilder {
    public static Graph buildGraph(List<?> list) {

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        //cashing Nodes for linkage, sind ID broke normal factory pattern
        Map<Integer, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            String nodeName = list.get(i).toString();
            Node currentNode = getNode(nodeName);
            nodeMap.put(i, currentNode);
            g = g.with( currentNode);

            if (i > 0) {
                Node prevNode = nodeMap.get(i-1);
                // next pointer
                g = g.with(prevNode.link(currentNode));
                // prev pointer
                g = g.with(currentNode.link(prevNode));
            }
        }
        return g;
    }
}
