package com.hhu.datastructureview.impl.progra;

import static com.hhu.datastructureview.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;

import java.lang.reflect.Field;

import com.hhu.progradatastructures.generic.PrograHashSet;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/** Returns a GraphViz Graph for Progra HashSet */
public class PrograHashSetGraph {

    public static Graph buildGraph(PrograHashSet<?> set) {
        if (set.size() == 0) {
            return graph("hashSetGraph");
        }

        // top level graph
        Graph g = graph("hashSetGraph")
                .nodeAttr().with(Shape.RECTANGLE)
                // neato = unsortiert, "zufällig" verteilt
                .graphAttr().with(attr("layout", "neato"));

        // subgraph with elements, to get a border around the elements
        Graph elements = graph("cluster_0")
                .graphAttr().with(
                        attr("color", "white"),
                        attr("penwidth", "2"));

        try {
            // Progra HashSet hat keine get() - Methode, deswegen wird hier das object Array
            // gelesen
            Field objectsField = PrograHashSet.class.getDeclaredField("objects");
            objectsField.setAccessible(true);
            Object[] objects = (Object[]) objectsField.get(set);

            // Not visualizing the HashTable Slots in the DataStructure View, since that
            // info is normally hidden from the user
            for (Object current : objects) {
                if (current != null) {
                    Node currentNode = getNode(current.toString());
                    elements = elements.with(currentNode);
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
            throw new IllegalStateException("Failed to reflect HashSet internals for graph visualization. " + e);
        }

        return g.with(elements);
    }

    private PrograHashSetGraph() {
        /* This utility class should not be instantiated */
    }

}
