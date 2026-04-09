package com.hhu.util.datastructureView.impl.javaUtil;

import guru.nidi.graphviz.attribute.Rank;

import com.hhu.legacyDatastructures.VLinkedList;
import com.hhu.util.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import java.util.LinkedList;

public class LinkedListGraphBuilder<E> implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof LinkedList<?>)) {
            throw new IllegalArgumentException("Expected LinkedList");
        }
        LinkedList<E> list = (LinkedList<E>) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 0; i < list.size(); i++) {
            String nodeName = list.get(i).toString();
            g = g.with(node(nodeName));

            if (i > 0) {
                String prevNodeName = list.get(i - 1).toString();
                // next pointer
                g = g.with(node(prevNodeName).link(node(nodeName)));
                // prev pointer
                g = g.with(node(nodeName).link(node(prevNodeName)));
            }
        }
        return g;
    }
}
