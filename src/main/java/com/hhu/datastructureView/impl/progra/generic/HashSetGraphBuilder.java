package com.hhu.datastructureView.impl.progra.generic;

import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import com.hhu.datastructures.prograDatastructures.generic.HashSet;

import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Attributes;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankType;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/* Returns a GraphViz Graph for Progra HashSet  */
public class HashSetGraphBuilder {
    /*
     * Since the Progra HashSet isn't a LinkedHashSet, the LinkedHashSetGraphBuilder
     * can't be reused 
     */
    public static Graph buildGraph(HashSet<?> set) {
        if (set.size() == 0) {
            return graph("hashSetGraph");
        }

        // top level graph
        Graph g = graph("hashSetGraph").directed()
                .graphAttr().with(Rank.dir(TOP_TO_BOTTOM))
                .nodeAttr().with(Shape.RECTANGLE);

        // Graph with just the elements of the HashSet, to get them to be next to each other
        Graph elements = graph("elements").directed()
                .graphAttr().with(Rank.inSubgraph(RankType.SAME))
                .nodeAttr().with(Shape.RECTANGLE);

        // Not visualizing the HashTable Slots in the DataStructure View, since that
        // info is normally hidden from the user
        Node hashTable = node("Hash Table").with(Shape.BOX_3D);

        // Subgraph für HashTable
        Graph top = graph("top")
                .graphAttr().with(Rank.inSubgraph(RankType.MIN))
                .with(hashTable);

        try {
            Field objectsField = HashSet.class.getDeclaredField("objects");
            objectsField.setAccessible(true);
            Object[] objects = (Object[]) objectsField.get(set);

            // Verweis auf HashTable
            for (Object current : objects) {
                if (current != null) {
                    Node currentNode = node(current.toString());
                    elements = elements.with(currentNode);
                    g = g.with(currentNode.link(to(hashTable).with(Arrow.NONE)));
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to reflect HashSet internals for graph visualization. " + e);
        }

        return g.with(top).with(elements);
    }

}
