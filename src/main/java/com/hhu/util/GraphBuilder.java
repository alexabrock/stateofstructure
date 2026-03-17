package com.hhu.util;

import java.util.Collection;
import java.util.LinkedList;

import com.hhu.datastructures.VLinkedList;
import com.hhu.datastructures.VStack;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class GraphBuilder {
    
/*     public static <E> Graph buildGraph(VLinkedList<String> list) {
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
    
    public static <E> Graph buildGraph(VStack<String> stack) {
        Graph g = graph("stackGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        for (int i = 0; i < stack.size(); i++) {
            String nodeName = (String) stack.get(i);
            g = g.with(node(nodeName));
            if (i > 0) {
                g = g.with(node((String) stack.get(i - 1)).link(node(nodeName)));
            }
        }
        return g;
    } */

    public static <E> Graph buildGraph(Collection<E> collection) {
        Graph g = graph("collectionGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);

        int index = 0;
        for (E element : collection) {
            String nodeName = element.toString() ;
            g = g.with(node(nodeName));
            if (index > 0) {
                String prevNodeName = collection.stream().skip(index - 1).findFirst().orElse(null).toString() ;
                g = g.with(node(prevNodeName).link(node(nodeName)));
            }
            index++;
        }
        return g;
    }


}
