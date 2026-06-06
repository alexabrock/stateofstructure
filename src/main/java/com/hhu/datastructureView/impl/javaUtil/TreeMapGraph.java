package com.hhu.datastructureView.impl.javaUtil;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static com.hhu.datastructureView.NodeBuilder.nullNode;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.TreeMap;

import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for TreeMaps */
public class TreeMapGraph {

    public static Graph buildGraph(TreeMap<?, ?> map) {
        if (map.isEmpty()) {
            return graph("treeMapGraph");
        }

        Graph g = graph("treeMapGraph").nodeAttr().with(
                attr("fixedsize", "true"),
                attr("width", "1"),
                attr("height", "1"));

        try {

            Field rootField = TreeMap.class.getDeclaredField("root");
            rootField.setAccessible(true);
            // root is an Entry<K,V>, wich is a private nested class
            Object root = rootField.get(map);

            HashMap<Object, Node> cache = new HashMap<>();

            return buildRecursive(g, root, cache);

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeMap structure", e);
        }
    }

    private static Graph buildRecursive(Graph g, Object entry, HashMap<Object, Node> cache) throws Exception {
        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        // get Fields
        Field keyField = entryClass.getDeclaredField("key");
        Field valueField = entryClass.getDeclaredField("value");
        Field leftField = entryClass.getDeclaredField("left");
        Field rightField = entryClass.getDeclaredField("right");

        keyField.setAccessible(true);
        valueField.setAccessible(true);
        leftField.setAccessible(true);
        rightField.setAccessible(true);

        // get Inhalt der Fields
        Object key = keyField.get(entry);
        Object value = valueField.get(entry);
        // left subtree
        Object left = leftField.get(entry); // Entry<K,V>
        // right subtree
        Object right = rightField.get(entry); // Entry<K,V>

        Node currentNode = cache.computeIfAbsent(key, k -> treeMapNode(key, value));
        g = g.with(currentNode);

        // linken Knoten erstellen
        if (left != null) {
            Object leftKey = keyField.get(left);
            Object leftValue = valueField.get(left);

            Node leftNode = cache.computeIfAbsent(leftKey, k -> treeMapNode(leftKey, leftValue));
            g = g.with(currentNode.link(to(leftNode)));

            g = buildRecursive(g, left, cache);
        } else {
            g = g.with(currentNode.link(to(nullNode())));
        }

        // rechten Knoten erstellen
        if (right != null) {
            Object rightKey = keyField.get(right);
            Object rightValue = valueField.get(right);

            Node rightNode = cache.computeIfAbsent(rightKey, k -> treeMapNode(rightKey, rightValue));
            g = g.with(currentNode.link(to(rightNode)));

            g = buildRecursive(g, right, cache);
        } else {
            g = g.with(currentNode.link(to(nullNode())));
        }

        return g;
    }

    private static Node treeMapNode(Object key, Object value) {
        return getNode(String.valueOf(key) + ", " + String.valueOf(value));
    }
}
