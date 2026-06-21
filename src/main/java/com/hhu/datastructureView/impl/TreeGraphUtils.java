package com.hhu.datastructureview.impl;

import static com.hhu.datastructureview.NodeBuilder.getNode;
import static com.hhu.datastructureview.NodeBuilder.nullNode;
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
            String... labelFieldNames) throws Exception {

        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        // get Fields
        //the label field is called different things in the datastructure
        Field[] labelFields = getFields(entryClass, labelFieldNames);
        //all trees call this left and right
        Field leftField = getField(entryClass, "left");
        Field rightField = getField(entryClass, "right");

        // left subtree
        Object left = leftField.get(entry);
        // right subtree
        Object right = rightField.get(entry);

        Node currentNode = getCachedNode(cache, entry, labelFields);
        g = g.with(currentNode);

        // linken Knoten erstellen
        if (left != null) {
            g = g.with(currentNode.link(to(getCachedNode(cache, left, labelFields))));
            g = buildRecursive(g, left, cache, labelFieldNames);
        } else {
            g = g.with(currentNode.link(to(nullNode())));
        }

        // rechten Knoten erstellen
        if (right != null) {
            g = g.with(currentNode.link(to(getCachedNode(cache, right, labelFields))));
            g = buildRecursive(g, right, cache, labelFieldNames);
        } else {
            g = g.with(currentNode.link(to(nullNode())));
        }

        return g;
    }

    private static Node getCachedNode(HashMap<Object, Node> cache, Object entry, Field[] labelFields) throws Exception {
        Object cacheKey = labelFields[0].get(entry);
        Node cached = cache.get(cacheKey);
        if (cached == null) {
            cached = getNode(createLabel(entry, labelFields));
            cache.put(cacheKey, cached);
        }
        return cached;
    }

    private static String createLabel(Object entry, Field[] fields) throws Exception {
        StringBuilder label = new StringBuilder(String.valueOf(fields[0].get(entry)));
        for (int i = 1; i < fields.length; i++) {
            label.append(", ").append(fields[i].get(entry));
        }
        return label.toString();
    }

    private static Field[] getFields(Class<?> clazz, String[] names) throws Exception {
        Field[] fields = new Field[names.length];
        for (int i = 0; i < names.length; i++) {
            fields[i] = getField(clazz, names[i]);
        }
        return fields;
    }

    private static Field getField(Class<?> clazz, String name) throws Exception {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}
