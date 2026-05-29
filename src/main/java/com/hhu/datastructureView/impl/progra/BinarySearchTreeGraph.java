package com.hhu.datastructureView.impl.progra;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.hhu.datastructureView.impl.TreeGraphUtils;
import com.hhu.prograDatastructures.generic.PrograBinarySearchTree;

import guru.nidi.graphviz.model.Graph;

/* Builds a GraphViz Graph for Progra BinarySearchTrees */
public class BinarySearchTreeGraph {

    public static Graph buildGraph(PrograBinarySearchTree<?> tree) {

        try {
            if (tree.size() == 0) {
                return graph("binarySearchTreeGraph");
            }

            // Zugriff auf root node
            Field rootField = PrograBinarySearchTree.class.getDeclaredField("root");
            rootField.setAccessible(true);
            Object root = rootField.get(tree);

            Graph g = graph("binarySearchTreeGraph").nodeAttr().with(
                    attr("fixedsize", "true"),
                    attr("width", "1"),
                    attr("height", "1"));

            return TreeGraphUtils.buildRecursive(
                    g,
                    root,
                    new HashMap<>(),
                    "element");

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect BinarySearchTree structure", e);
        }
    }
}