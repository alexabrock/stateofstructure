package com.hhu.datastructureView.impl.javaUtil;



import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import java.util.ArrayList;

import com.hhu.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

/* Builds a GraphViz Graph for ArrayLists */
public class ArrayListGraphBuilder implements GraphBuilder {
    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof ArrayList<?>)) {
            throw new IllegalArgumentException("Expected ArrayList");
        }
        ArrayList<?> list = (ArrayList<?>) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(
                        //damit die einzelnen Nodes von rechts nach links angezeigt werden
                        Rank.dir(TOP_TO_BOTTOM),
                        Attributes.attr("nodesep", "0"), // horizontal spacing
                        Attributes.attr("ranksep", "0") // vertical spacing
                )
                .nodeAttr().with(
                        Shape.RECTANGLE,
                        Attributes.attr("margin", "0") // remove inner padding
                );

        g = addNodesFromList(list, g);
        return g;
    }

    private Graph addNodesFromList(ArrayList<?> list, Graph g) {
        for (int i = 0; i < list.size(); i++) {
            String nodeName = String.valueOf(list.get(i));
            g = g.with(node(nodeName));
        }
        return g;
    }
}
