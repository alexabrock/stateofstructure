package com.hhu.util.datastructureView.impl;

import guru.nidi.graphviz.attribute.Rank;

import com.hhu.prograDatastructures.PrograList;
import com.hhu.util.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class VPrograListBuilder implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof PrograList)) {
            throw new IllegalArgumentException("Expected PrograList");
        }
        PrograList list = (PrograList) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 0; i < list.size(); i++) {
            String nodeName = String.valueOf(list.at(i));
            g = g.with(node(nodeName));

            if (i > 0) {
                String prevNodeName = String.valueOf(list.at(i - 1));
                // next pointer
                // g = g.with(node(prevNodeName).link(node(nodeName)));
                // prev pointer
                g = g.with(node(nodeName).link(node(prevNodeName)));
            }
        }
        return g;
    }
}
