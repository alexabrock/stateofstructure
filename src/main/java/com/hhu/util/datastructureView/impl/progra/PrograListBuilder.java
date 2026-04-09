package com.hhu.util.datastructureView.impl.progra;

import guru.nidi.graphviz.attribute.Rank;

import com.hhu.prograDatastructures.specific.IntList;
import com.hhu.util.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class PrograListBuilder implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof IntList)) {
            throw new IllegalArgumentException("Expected PrograList");
        }
        IntList list = (IntList) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 0; i < list.length(); i++) {
            String nodeName = String.valueOf(list.get(i));
            g = g.with(node(nodeName));

            if (i > 0) {
                String prevNodeName = String.valueOf(list.get(i - 1));
                g = g.with(node(nodeName).link(node(prevNodeName)));
            }
        }
        return g;
    }
}
