package com.hhu.util.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.attribute.Style.INVIS;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.util.LinkedHashSet;

import com.hhu.util.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

public class LinkedHashSetGraphBuilder implements GraphBuilder {

    @Override
    //TODO add a Visualization that isnt just an array
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof LinkedHashSet<?>)) {
            throw new IllegalArgumentException("Expected LinkedHashSet");
        }

        LinkedHashSet<?> set = (LinkedHashSet<?>) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);
        Node previous = null;

        for (Object element : set) {
            Node current = node(String.valueOf(element));

            g = g.with(current);

            if (previous != null) {
                g = g.with(previous.link(to(current).with(INVIS)));
            }

            previous = current;
        }

        return g;
    }
}