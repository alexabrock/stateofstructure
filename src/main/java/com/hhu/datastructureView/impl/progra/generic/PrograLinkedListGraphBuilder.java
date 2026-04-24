package com.hhu.datastructureView.impl.progra.generic;

import java.util.LinkedList;

import com.hhu.datastructureView.impl.javaUtil.LinkedListGraphBuilder;
import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

import java.util.LinkedList;
import java.util.List;

import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

/* Builds a GraphViz Graph for PrograLinkedLists*/
public class PrograLinkedListGraphBuilder{

    /* Returns a GraphViz Graph for PrograLinkedLists */
    public static Graph buildGraph(PrograLinkedList<?> list) {
        /*
         * Since the PrograLinkedList isn't a List, the LinkedListGraphBuilder
         * can't be reused
         */
        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 0; i < list.length(); i++) {
            String nodeName = list.get(i).toString();
            g = g.with(node(nodeName));

            if (i > 0) {
                String prevNodeName = list.get(i - 1).toString();
                // next pointer
                g = g.with(node(prevNodeName).link(node(nodeName)));
                // prev pointer
                g = g.with(node(nodeName).link(node(prevNodeName)));
            }
        }
        return g;
    }
}
    

