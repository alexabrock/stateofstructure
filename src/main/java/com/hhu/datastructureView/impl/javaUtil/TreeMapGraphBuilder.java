package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.TreeMap;

import com.hhu.datastructureView.api.GraphBuilder;

import static guru.nidi.graphviz.attribute.Attributes.attr;

import guru.nidi.graphviz.attribute.NodeAttr;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

/* Builds a GraphViz Graph for TreeMaps */
public class TreeMapGraphBuilder implements GraphBuilder {

    @Override
    public Graph buildGraph(Object collection) {

        try {
            TreeMap<?, ?> map = (TreeMap<?, ?>) collection;
            Graph g = graph("treeMapGraph").nodeAttr().with(
                    attr("fixedsize", "true"),
                    attr("width", "1"),
                    attr("height", "1"));

            if (map.isEmpty()) {
                return g;
            }

            Field rootField = TreeMap.class.getDeclaredField("root");
            rootField.setAccessible(true);
            // root is an Entry<K,V>, wich is a private nested class
            Object root = rootField.get(map);

            return buildRecursive(g, root);

        } catch (ClassCastException c) {
            throw new IllegalArgumentException("Expected TreeMap");
        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeSet structure", e);
        }
    }

    private Graph buildRecursive(Graph g, Object entry) throws Exception{
        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        //get Fields
        Field keyField = entryClass.getDeclaredField("key");
        Field valueField = entryClass.getDeclaredField("value");
        Field leftField = entryClass.getDeclaredField("left");
        Field rightField = entryClass.getDeclaredField("right");

        keyField.setAccessible(true);
        valueField.setAccessible(true);
        leftField.setAccessible(true);
        rightField.setAccessible(true);

        //get Inhalt der Fields
        Object key = keyField.get(entry);
        Object value = valueField.get(entry);
        Object left = leftField.get(entry);  // Entry<K,V>
        Object right = rightField.get(entry); // Entry<K,V>

        Node currentNode = treeMapNode(key, value).with(Shape.CIRCLE);
        g = g.with(currentNode);

        //linken Knoten erstellen
        if (left != null) {
            g = getSubTree(g, keyField, valueField, left, currentNode);
        } else {
            g = g.with(currentNode.link(to(invisibleNode(key)).with(Style.INVIS)));
        }

        //rechten Knoten erstellen
        if (right != null) {
            g = getSubTree(g, keyField, valueField, right, currentNode);
        } else {
            g = g.with(currentNode.link(to(invisibleNode(key)).with(Style.INVIS)));
        }

        return g;
    }

    private Graph getSubTree(Graph g, Field keyField, Field valueField, Object left, Node currentNode)
            throws IllegalAccessException, Exception {
        Object leftKey = keyField.get(left);
        Object leftValue = valueField.get(left);

        Node leftNode = treeMapNode(leftKey, leftValue);
        g = g.with(currentNode.link(to(leftNode)));

        g = buildRecursive(g, left);
        return g;
    }

    private Node treeMapNode(Object rightKey, Object rightValue) {
        return node(String.valueOf(rightKey) + ", " + String.valueOf(rightValue));
    }


    // needs to be unique, since graphviz merges Nodes with the same Name
    private Node invisibleNode(Object key) {
        return node("" + System.nanoTime()).with(Style.INVIS);
    }

}
