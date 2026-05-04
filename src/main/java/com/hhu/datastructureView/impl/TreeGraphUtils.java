package com.hhu.datastructureView.impl;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static com.hhu.datastructureView.NodeBuilder.nullNode;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.HashMap;

import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

public class TreeGraphUtils {

    public static Graph buildRecursive(
            Graph g,
            Object entry,
            HashMap<Object, Node> cache,
            String valueFieldName) throws Exception {

        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        // get Fields
        //the value field is called different things in the datastructure
        Field valueField = getField(entryClass, valueFieldName);
        //all trees call this left and right
        Field leftField = getField(entryClass, "left");
        Field rightField = getField(entryClass, "right");

        // get Inhalt der Fields
        Object value = valueField.get(entry);
        // left subtree
        Object left = leftField.get(entry);
        // right subtree
        Object right = rightField.get(entry);

        Node currentNode = cache.computeIfAbsent(
                value,
                v -> getNode(String.valueOf(v)));

        g = g.with(currentNode);

        // linken Knoten erstellen
        if (left != null) {
            Object leftValue = valueField.get(left);

            Node leftNode = cache.computeIfAbsent(
                    leftValue,
                    v -> getNode(String.valueOf(v)));

            g = g.with(currentNode.link(to(leftNode)));

            g = buildRecursive(g, left, cache, valueFieldName);
        } else {
            g = g.with(currentNode.link(to(nullNode(value))));
        }

        // rechten Knoten erstellen
        if (right != null) {
            Object rightValue = valueField.get(right);

            Node rightNode = cache.computeIfAbsent(
                    rightValue,
                    v -> getNode(String.valueOf(v)));

            g = g.with(currentNode.link(to(rightNode)));

            g = buildRecursive(g, right, cache, valueFieldName);
        } else {
            g = g.with(currentNode.link(to(nullNode(value))));
        }

        return g;
    }

    private static Field getField(Class<?> clazz, String name) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}