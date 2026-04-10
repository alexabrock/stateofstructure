package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructureView.api.GraphBuilder;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import guru.nidi.graphviz.model.Node;

public class TreeSetGraphBuilder implements GraphBuilder {

    @Override
    public Graph buildGraph(Object collection) {
        if (!(collection instanceof TreeSet<?>)) {
            throw new IllegalArgumentException("Expected TreeSet");
        }

        try {
            TreeSet<?> set = (TreeSet<?>) collection;

            if (set.isEmpty()) {
                return graph("treeSetGraph");
            }

            // Zugriff auf internes TreeMap
            Field mapField = TreeSet.class.getDeclaredField("m");
            mapField.setAccessible(true);
            TreeMap<?, ?> map = (TreeMap<?, ?>) mapField.get(set);
            

            // Zugriff auf root node
            Field rootField = TreeMap.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(map);

            Graph g = graph("treeSetGraph").nodeAttr().with(
                    attr("fixedsize", "true"),
                    attr("width", "0.6"),
                    attr("height", "0.6"));
            

            return buildRecursive(g, root);

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeSet structure", e);
        }
    }

    private Graph buildRecursive(Graph g, Object entry) throws Exception {

        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        Field keyField = entryClass.getDeclaredField("key");
        Field leftField = entryClass.getDeclaredField("left");
        Field rightField = entryClass.getDeclaredField("right");

        keyField.setAccessible(true);
        leftField.setAccessible(true);
        rightField.setAccessible(true);

        Object key = keyField.get(entry);
        Object left = leftField.get(entry);
        Object right = rightField.get(entry);

        Node currentNode = node(String.valueOf(key)).with(Shape.CIRCLE);

        g = g.with(currentNode);

        if (left != null) {
            Object leftKey = keyField.get(left);

            Node leftNode = node(String.valueOf(leftKey));

            g = g.with(currentNode.link(to(leftNode)));

            g = buildRecursive(g, left);
        }

        if (right != null) {
            Object rightKey = keyField.get(right);

            Node rightNode = node(String.valueOf(rightKey));

            g = g.with(currentNode.link(to(rightNode)));

            g = buildRecursive(g, right);
        }

        return g;
    }
}