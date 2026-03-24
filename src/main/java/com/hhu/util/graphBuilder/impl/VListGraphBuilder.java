package com.hhu.util.graphBuilder.impl;

import guru.nidi.graphviz.attribute.Rank;

import com.hhu.datastructures.VLinkedList;
import com.hhu.util.graphBuilder.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class VListGraphBuilder implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof VLinkedList<?>)) {
                throw new IllegalArgumentException("Expected VLinkedList");
            }

        VLinkedList<?> list = (VLinkedList<?>) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        int index = 0;
        for (Object element : list) {
            String nodeName = element.toString() ;
            g = g.with(node(nodeName));
            if (index > 0) {
                String prevNodeName = list.stream().skip(index - 1).findFirst().orElse(null).toString() ;
                g = g.with(node(prevNodeName).link(node(nodeName)));
                g = g.with(node(nodeName).link(node( prevNodeName)));
            }
            index++;
        }
        return g;
    }
}
