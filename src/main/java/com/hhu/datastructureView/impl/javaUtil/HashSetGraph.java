package com.hhu.datastructureview.impl.javautil;

import static com.hhu.datastructureview.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;

import java.lang.reflect.Field;
import java.util.HashSet;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/** Returns a GraphViz Graph for HashSet */
public class HashSetGraph {

    public static Graph buildGraph(HashSet<?> set) {
        if (set.isEmpty()) {
            return graph("hashSetGraph");
        }

        Graph g = graph("hashSetGraph")
                .nodeAttr().with(Shape.RECTANGLE)
                .graphAttr().with(attr("layout", "neato"));

        Graph elements = graph("cluster_0")
                .graphAttr().with(
                        attr("color", "white"),
                        attr("penwidth", "2"));

        try {
            // 1. Hole das HashMap-Feld aus dem HashSet
            Field mapField = HashSet.class.getDeclaredField("map");
            mapField.setAccessible(true);
            java.util.HashMap<?, ?> map = (java.util.HashMap<?, ?>) mapField.get(set);

            // 2. Hole die Keys aus der internen Map
            java.util.Set<?> keys = map.keySet();

            // 3. Iteriere über die Keys
            for (Object current : keys) {
                if (current != null) {
                    Node currentNode = getNode(current.toString());
                    elements = elements.with(currentNode);
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to reflect HashSet internals: " + e.getMessage());
        }

        return g.with(elements);
    }

}
