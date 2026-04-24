package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import java.util.List;
import java.util.Stack;


import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for Stacks */
public class StackGraphBuilder {
    
    public static Graph buildGraph(Stack<?> stack) {
        
        if (stack.isEmpty()) {
            return graph("stackGraph");
        }

        List<Node> nodes = stack.stream().map(e -> node(e.toString())).toList();

        Node topPointer = node("top").with(Shape.PLAIN);
        Node bottomPointer = node("bottom").with(Shape.PLAIN);

        // rank=same subgraph for top pointer & top element linkage
        Graph topRank = graph("topRank")
                .graphAttr().with(Rank.inSubgraph(RankType.SAME))
                .with(topPointer, nodes.get(0));
                
        // rank=same subgraph for bottom pointer & bottom element linkage
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