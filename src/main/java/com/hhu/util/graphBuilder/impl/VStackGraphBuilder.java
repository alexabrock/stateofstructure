package com.hhu.util.graphBuilder.impl;

import com.hhu.datastructures.VStack;
import com.hhu.util.graphBuilder.api.GraphBuilder;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.model.LinkSource;

import java.util.List;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class VStackGraphBuilder<E> implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof VStack<?>)) {
            throw new IllegalArgumentException("Expected VStack");
        }
        VStack<E> stack = (VStack<E>) collection;

        //für stack.get(0), wenn stack leer
        if (stack.isEmpty()) {
            return graph("stackGraph");
        }


        List<Node> nodes = stack.stream().map(e -> node(e.toString())).toList();

        Node topPointer = node("top").with(Shape.PLAIN);
        Node bottomPointer = node("bottom").with(Shape.PLAIN);

        // rank=same subgraphs for top and bottom pointer
        Graph topRank = graph("topRank")
                .graphAttr().with(Rank.inSubgraph(RankType.SAME))
                .with(topPointer, nodes.get(0));

        Graph bottomRank = graph("bottomRank")
                .graphAttr().with(Rank.inSubgraph(RankType.SAME))
                .with(bottomPointer, nodes.get(stack.size() - 1));


        Graph g = graph("stackGraph").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 1; i < stack.size(); i++) {
            g = g.with(nodes.get(i - 1).link(nodes.get(i)));
        }

        g = g.with(
                topPointer.link(nodes.get(0)),
                bottomPointer.link(nodes.get(stack.size() - 1)),
                topRank,
                bottomRank);

        return g;
    }
}