package com.hhu.datastructureView.impl.progra.generic;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;

import java.util.HashMap;
import java.util.Map;

import com.hhu.datastructures.prograDatastructures.generic.PrograLinkedList;

import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for PrograLinkedLists*/
public class PrograLinkedListGraphBuilder{

    /* Returns a GraphViz Graph for PrograLinkedLists */
    public static Graph buildGraph(PrograLinkedList<?> list) {
        /*
         * Since the PrograLinkedList isn't a List, the LinkedListGraphBuilder
         * can't be reused
         */
        Graph g = graph("prograLinkedListGraph").directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT))
                .nodeAttr().with(Shape.RECTANGLE);
                
        // cashing Nodes for linkage, sind ID broke normal factory pattern
        Map<Integer, Node> nodeMap = new HashMap<>();

        for (int i = 0; i < list.length(); i++) {
            String nodeName = list.get(i).toString();
            Node current = getNode(nodeName); 
            nodeMap.put(i, current);
            g = g.with(current);

            if (i > 0) {
                Node prev = nodeMap.get(i - 1);
                // next pointer
                g = g.with(prev.link(current));
            }
        }
        return g;
    }
}
    

