package com.hhu.views.datastructure;

import java.util.LinkedList;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class GraphBuilder {
    
        public static <E> Graph buildGraphFromList(LinkedList<E> list) {
        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 0; i < list.size(); i++) {
            String nodeName = (String) list.get(i);
            g = g.with(node(nodeName));
            if (i > 0) {
                g = g.with(node((String) list.get(i - 1)).link(node(nodeName)));
                g = g.with(node(nodeName).link(node((String) list.get(i - 1))));
            }
        }
        return g;
    }

}
