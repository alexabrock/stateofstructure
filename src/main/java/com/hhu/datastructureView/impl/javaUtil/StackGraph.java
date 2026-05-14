package com.hhu.datastructureView.impl.javaUtil;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Rank.RankDir.BOTTOM_TO_TOP;
import static guru.nidi.graphviz.model.Factory.graph;

import java.util.List;
import java.util.Stack;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for Stacks */
public class StackGraph {
    
    public static Graph buildGraph(Stack<?> stack) {
        
        if (stack.isEmpty()) {
            return graph("stackGraph");
        }

        List<Node> nodes = stack.stream().map(e -> getNode(e.toString())).toList();

        Node topPointer = getNode("top").with(Shape.PLAIN);
        Node bottomPointer = getNode("bottom").with(Shape.PLAIN);

        // rank=same subgraph for top pointer & top element linkage
        Graph topRank = graph("topRank")
                .graphAttr().with(Rank.inSubgraph(RankType.SAME))
                .with(topPointer, nodes.get(stack.size() - 1));
                
        // rank=same subgraph for bottom pointer & bottom element linkage
        Graph bottomRank = graph("bottomRank")
                .graphAttr().with(Rank.inSubgraph(RankType.SAME))
                .with(bottomPointer, nodes.get(0));

        Graph g = graph("stackGraph")
                .graphAttr().with(Rank.dir(BOTTOM_TO_TOP))
                .nodeAttr().with(Shape.RECTANGLE)
                .graphAttr().with(
                        //Attributes.attr("nodesep", "0"), // horizontal spacing
                        Attributes.attr("ranksep", "0") // vertical spacing
                );

        for (int i = 1; i < stack.size(); i++) {
            LinkSource link = nodes.get(i - 1).link(nodes.get(i))
                    .with(Attributes.attr("style", "dashed")); //color not-top stack items in dashed line 
            g = g.with(link);
        }

        g = g.with(
                bottomPointer.link(nodes.get(0)),
                topPointer.link(nodes.get(stack.size() - 1)),
                bottomRank,
                topRank);

        return g;
    }
}