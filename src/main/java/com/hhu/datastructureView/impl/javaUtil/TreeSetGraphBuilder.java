package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for TreeSets*/
public class TreeSetGraphBuilder {

    public static Graph buildGraph(TreeSet<?> set) {

        try {
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
                    attr("width", "1"),
                    attr("height", "1"));

            Map<Object, Node> cache = new HashMap<>();

            return buildRecursive(g, root, cache);

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeSet structure", e);
        }
    }

    private static Graph buildRecursive(Graph g, Object entry, Map<Object, Node> cache ) throws Exception {

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
        //left subtree
        Object left = leftField.get(entry);
        //right subtree
        Object right = rightField.get(entry);


        String currentNodeName = String.valueOf(key);
        Node currentNode = cache.computeIfAbsent(
                currentNodeName,
                k -> getNode(String.valueOf(k)));

        g = g.with(currentNode);

        if (left != null) {
            Object leftKey = keyField.get(left);

            Node node = cache.computeIfAbsent(
                    leftKey,
                    k -> getNode(String.valueOf(k)));

            g = g.with(currentNode.link(to(node)));

            g = buildRecursive(g, left, cache);
        } else {
            g = g.with(
                    currentNode.link(to(nullNode(key))));
        }

        if (right != null) {
            Object rightKey = keyField.get(right);

            Node node = cache.computeIfAbsent(
                    rightKey,
                    k -> getNode(String.valueOf(k)));

            g = g.with(currentNode.link(to(node)));

            g = buildRecursive(g, right, cache);
        } else {
            g = g.with(
                    currentNode.link(to(nullNode(key))));
        }

        return g;
    }

    // needs to be unique, since graphviz merges Nodes with the same Name
    private static Node nullNode(Object key) {
        return getNode("")
                .with(attr("width", "0.3"),
                        attr("height", "0.3"),
                        attr("fixedsize", "true"));
    }
}