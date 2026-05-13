package com.hhu.datastructureView.impl.javaUtil;



import static com.hhu.datastructureView.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;

import java.util.ArrayList;

import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

/* Builds a GraphViz Graph for ArrayLists */
public class ArrayListGraph {

    public static Graph buildGraph(ArrayList<?> list) {


        if (list.isEmpty()) {
            return graph("arrayListGraph");
        }
        

        Graph g = graph("arrayListGraph").directed()
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

    private static Graph addNodesFromList(ArrayList<?> list, Graph g) {
        for (int i = 0; i < list.size(); i++) {
            String nodeName = String.valueOf(list.get(i));
            g = g.with(getNode(nodeName));
        }
        return g;
    }
}
