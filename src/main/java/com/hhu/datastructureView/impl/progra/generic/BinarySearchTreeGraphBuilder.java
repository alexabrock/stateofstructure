package com.hhu.datastructureView.impl.progra.generic;

import static com.hhu.datastructureView.NodeBuilder.getNode;
import static com.hhu.datastructureView.NodeBuilder.nullNode;
import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.to;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.hhu.datastructures.prograDatastructures.generic.BinarySearchTree;

import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

public class BinarySearchTreeGraphBuilder {

    public static Graph buildGraph(BinarySearchTree<?> tree) {

        try {
            if (tree.size()==0) {
                return graph("binarySearchTreeGraph");
            }

            // Zugriff auf root node
            Field rootField = BinarySearchTree.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(tree);

            Graph g = graph("binarySearchTreeGraph").nodeAttr().with(
                    attr("fixedsize", "true"),
                    attr("width", "1"),
                    attr("height", "1"));

            Map<Object, Node> cache = new HashMap<>();

            return buildRecursive(g, root, cache);

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect BinarySearchTree structure", e);
        }
    }

    private static Graph buildRecursive(Graph g, Object entry, Map<Object, Node> cache ) throws Exception {

        if (entry == null) {
            return g;
        }

        Class<?> entryClass = entry.getClass();

        Field elementField = entryClass.getDeclaredField("element");
        Field leftField = entryClass.getDeclaredField("left");
        Field rightField = entryClass.getDeclaredField("right");

        elementField.setAccessible(true);
        leftField.setAccessible(true);
        rightField.setAccessible(true);

        //inhalt der current TreeNode
        Object element = elementField.get(entry);
        //left subtree
        Object left = leftField.get(entry);
        //right subtree
        Object right = rightField.get(entry);


        String currentNodeName = String.valueOf(element);
        Node currentNode = cache.computeIfAbsent(
                currentNodeName,
                k -> getNode(String.valueOf(k)));

        g = g.with(currentNode);

        if (left != null) {
            Object leftElement = elementField.get(left);

            Node node = cache.computeIfAbsent(
                    leftElement,
                    k -> getNode(String.valueOf(k)));

            g = g.with(currentNode.link(to(node)));

            g = buildRecursive(g, left, cache);
        } else {
            g = g.with(
                    currentNode.link(to(nullNode(element))));
        }

        if (right != null) {
            Object rightElement = elementField.get(right);

            Node node = cache.computeIfAbsent(
                    rightElement,
                    k -> getNode(String.valueOf(k)));

            g = g.with(currentNode.link(to(node)));

            g = buildRecursive(g, right, cache);
        } else {
            g = g.with(
                    currentNode.link(to(nullNode(element))));
        }

        return g;
    }
    
}
