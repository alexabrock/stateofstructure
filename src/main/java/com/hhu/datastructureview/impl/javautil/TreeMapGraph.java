package com.hhu.datastructureview.impl.javautil;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.TreeMap;

import com.hhu.datastructureview.impl.TreeGraphUtils;

import guru.nidi.graphviz.model.Graph;

/*** Builds a GraphViz Graph for TreeMaps */
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
            // root is an Entry<K,V>, which is a private nested class
            Object root = rootField.get(map);

            return TreeGraphUtils.buildRecursive(
                    g,
                    root,
                    new HashMap<>(),
                    "key",
                    "value");

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeMap structure", e);
        }
    }

    private TreeMapGraph() {
        /* This utility class should not be instantiated */
    }
}
