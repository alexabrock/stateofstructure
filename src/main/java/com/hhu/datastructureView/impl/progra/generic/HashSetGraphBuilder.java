package com.hhu.datastructureView.impl.progra.generic;

import static guru.nidi.graphviz.attribute.Attributes.attr;
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

    public static Graph buildGraph(HashSet<?> set) {
        if (set.size() == 0) {
            return graph("hashSetGraph");
        }

        // top level graph
        Graph g = graph("hashSetGraph")
                .nodeAttr().with(Shape.RECTANGLE)
                //neato = unsortiert, "zufällig" verteilt
                .graphAttr().with(attr("layout", "neato"));
        
        //subgraph with elements,  to get a border around the elements
        Graph elements =graph("cluster_0")
                .graphAttr().with(
                        attr("color", "white"),
                        attr("penwidth", "2"));


        try {
            //Progra HashSet hat keine get() - Methode, deswegen wird hier das object Array gelesen
            Field objectsField = HashSet.class.getDeclaredField("objects");
            objectsField.setAccessible(true);
            Object[] objects = (Object[]) objectsField.get(set);

            // Not visualizing the HashTable Slots in the DataStructure View, since that
            // info is normally hidden from the user
            for (Object current : objects) {
                if (current != null) {
                    Node currentNode = node(current.toString());
                    elements = elements.with(currentNode);
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            throw new RuntimeException("Failed to reflect HashSet internals for graph visualization. " + e);
        }

        return g.with(elements);
    }

}
