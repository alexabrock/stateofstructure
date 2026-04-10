package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.util.LinkedHashSet;

import com.hhu.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

public class LinkedHashSetGraphBuilder implements GraphBuilder {

    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof LinkedHashSet<?>)) {
            throw new IllegalArgumentException("Expected LinkedHashSet");
        }

        LinkedHashSet<?> set = (LinkedHashSet<?>) collection;

        Graph g = graph("listGraph").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Shape.RECTANGLE);

        //Graph with just the elements of the HashSet, to get them to be next to each other
        Graph elements = graph("elements").directed()
                .graphAttr().with(
                        Rank.inSubgraph(RankType.SAME))
                .nodeAttr().with(Shape.RECTANGLE)
                .graphAttr().with();

        // Not visualizing the HashTable Slots in the DataStructure View, since that
        // info is normally hidden from the user
        Node hashTable = node("Hash Table").with(Shape.BOX_3D);

        // Graph for just the hashTable Node, to set it above all the other nodes
        g = g.with(graph().graphAttr().with(Rank.inSubgraph(RankType.SOURCE))
                        .with(hashTable));

        Object prev = null;

        for (Object current : set) {
            String nodename = current.toString();
            Node currentNode = node(nodename);

            g = g.with(currentNode.link(to(hashTable).with(Arrow.NONE)));

            if (prev != null) {
                String prevNodeName = prev.toString();
                Node prevNode = node(prevNodeName);

                // before pointer
                elements = elements.with(
                        prevNode.link(
                        to(currentNode).with(Arrow.VEE)
                    ));
                // after pointer
                elements = elements.with(
                        currentNode.link(
                        to(prevNode).with(Arrow.VEE)));
                }

            prev = current;
        }
        g = g.with(elements);
        return g;
    }
}