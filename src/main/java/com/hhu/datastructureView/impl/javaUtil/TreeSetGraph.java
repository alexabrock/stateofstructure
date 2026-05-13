package com.hhu.datastructureView.impl.javaUtil;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.model.Factory.graph;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import com.hhu.datastructureView.impl.TreeGraphUtils;

import guru.nidi.graphviz.model.Graph;

/* Builds a GraphViz Graph for TreeSets*/
public class TreeSetGraph {

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

            return TreeGraphUtils.buildRecursive(
                    g,
                    root,
                    new HashMap<>(),
                    "key");

        } catch (Exception e) {
            throw new RuntimeException("Could not inspect TreeSet structure", e);
        }
    }
}